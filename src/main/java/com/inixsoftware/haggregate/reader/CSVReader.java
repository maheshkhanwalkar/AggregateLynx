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

package com.inixsoftware.haggregate.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader
{
    private File file;

    private String[] headerNames;
    private BufferedReader br;

    public CSVReader(File toRead)
    {
        file = toRead;

        try
        {
            br = new BufferedReader(new FileReader(file));
            init();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    private void init()
    {
        try
        {
            String line = "";
            headerNames = br.readLine().split(",");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public String readLine()
    {
        try
        {
            return br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public String[] getHeaderNames()
    {
        return headerNames;
    }

}
