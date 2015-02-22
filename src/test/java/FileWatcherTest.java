/*
   Copyright 2015 Mahesh Khanwalkar

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */


import java.nio.file.*;

public class FileWatcherTest
{
    public static void main(String[] args)
    {
        Path path = Paths.get("").toAbsolutePath().normalize();

        try
        {
            WatchService service = FileSystems.getDefault().newWatchService();
            path.register(service, StandardWatchEventKinds.ENTRY_CREATE);

            while(true)
            {
                WatchKey key = service.take();
                for (WatchEvent<?> event : key.pollEvents())
                {
                    WatchEvent.Kind<?> kind = event.kind();
                    if (kind == StandardWatchEventKinds.OVERFLOW)
                    {
                        continue;
                    }

                    WatchEvent<Path> ev = (WatchEvent<Path>)event;
                    Path file = ev.context();

                    Path child = path.resolve(file);
                    System.out.println(child);
                }

                if (!key.reset())
                    break;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
