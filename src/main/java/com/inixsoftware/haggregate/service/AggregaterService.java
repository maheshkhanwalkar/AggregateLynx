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

package com.inixsoftware.haggregate.service;

import com.inixsoftware.haggregate.data.AggregationResult;
import com.inixsoftware.haggregate.hadoop.HadoopWriter;
import com.inixsoftware.haggregate.reader.CSVReader;
import org.apache.log4j.BasicConfigurator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AggregaterService
{
    private ArrayList<String> aggregates = new ArrayList<String>();
    private String coreSite, hdfsSite;

    private ArrayList<HashMap<String, Integer>> list;// = new ArrayList<HashMap<String, Integer>>();

    /**
     *
     * @param hadoopCoreSite - path to core-site.xml
     * @param hadoopHdfsSite - path to hdfs-site.xml
     */
    public AggregaterService(String hadoopCoreSite, String hadoopHdfsSite, String... whatToAggregate)
    {
     //   BasicConfigurator.configure();

        coreSite = hadoopCoreSite;
        hdfsSite = hadoopHdfsSite;

        Collections.addAll(aggregates, whatToAggregate);
    }

    public void clear()
    {
        list = new ArrayList<HashMap<String, Integer>>();

        for (String s: aggregates)
        {
            list.add(new HashMap<String, Integer>());
        }
    }

    /* Reads file, aggregates against fields in ArrayList<>, writes file to Hadoop */

    public AggregationResult aggregateFile(String fileName, String hadoopURL)
    {
        clear();

        CSVReader reader = new CSVReader(new File(fileName));
        HadoopWriter writer = new HadoopWriter(hadoopURL, coreSite, hdfsSite);

        String[] data = null;
        String[] headers = reader.getHeaderNames();

        int[] pos = new int[headers.length];

        for (int i = 0; i < headers.length; i++)
        {
            String h1 = headers[i];
            int index = aggregates.indexOf(h1);

            pos[i] = index;
        }

        while ((data = reader.readLine()) != null)
        {
            for (int i = 0; i < pos.length; i++)
            {
                if (pos[i] != -1)
                {
                    process(pos[i], data[i]);
                }
            }

            writer.writeLine(reader.getCurrentLine());
        }

        return new AggregationResult(list, aggregates, fileName);
    }

    public AggregationResult[] aggregateFiles(String[] fileNames, String[] hadoopURLs)
    {
        AggregationResult[] results = new AggregationResult[fileNames.length];

        for (int i = 0; i < fileNames.length; i++)
        {
            results[i] = aggregateFile(fileNames[i], hadoopURLs[i]);
        }

        return results;
    }

    private void process(int loc, String key)
    {
        HashMap<String, Integer> map = list.get(loc);
        Integer value = map.get(key);

        if (value == null)
        {
            map.put(key, 1);
        }
        else
        {
            map.put(key, value + 1);
        }
    }
}
