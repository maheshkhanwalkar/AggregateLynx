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

package com.inixsoftware.haggregate.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HadoopWriter
{
    private String hadoopURL, coreSite, hdfsSite;

    private Configuration config;
    private FileSystem fs;

    private Path file;
    private FSDataOutputStream fos;

    public HadoopWriter(String url, String coreSite, String hdfsSite)
    {
        hadoopURL = url;
        this.coreSite = coreSite;

        this.hdfsSite = hdfsSite;
        init();

    }

    /* Creates the file in the HDFS, deletes if it exists already */
    private void init()
    {
        try
        {
            config = new Configuration();

            config.addResource(new Path(coreSite));
            config.addResource(new Path(hdfsSite));

            fs = FileSystem.get(config);
            file = new Path(hadoopURL);

            if (fs.exists(file))
                fs.delete(file, true);

            fos = fs.create(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void writeLine(String line)
    {
        try
        {
            fos.writeBytes(line);
            fos.writeBytes("\n");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void close()
    {
        try
        {
            fos.close();
            fs.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
