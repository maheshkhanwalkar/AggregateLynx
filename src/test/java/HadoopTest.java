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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HadoopTest
{
    public static void main(String[] args) throws IOException
    {
        Configuration conf = new Configuration();

        conf.addResource(new Path("/opt/hadoop-2.6.0/etc/hadoop/core-site.xml"));
        conf.addResource(new Path("/opt/hadoop-2.6.0/etc/hadoop/hdfs-site.xml"));

        FileSystem fs = FileSystem.get(conf);
        Path file = new Path("hdfs://localhost:9000/sample.txt");


        if (fs.exists(file))
            fs.delete(file, true);

        FSDataOutputStream fos = fs.create(file);

        fos.writeBytes("Hello world!!!\n");
        fos.flush();

        fos.close();


        FSDataInputStream fis = fs.open(file);
        String tmp;

        while ((tmp = fis.readLine()) != null)
        {
            System.out.println(tmp);
        }

        fis.close();
        fs.close();
    }
}
