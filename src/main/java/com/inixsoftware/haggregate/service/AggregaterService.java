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

import java.io.File;
import java.util.ArrayList;

public class AggregaterService
{
    private ArrayList<String> aggregates = new ArrayList<String>();
    private String coreSite, hdfsSite;

    /**
     *
     * @param hadoopCoreSite - path to core-site.xml
     * @param hadoopHdfsSite - path to hdfs-site.xml
     */
    public AggregaterService(String hadoopCoreSite, String hadoopHdfsSite)
    {
        coreSite = hadoopCoreSite;
        hdfsSite = hadoopHdfsSite;
    }

    public void addAggregate(String category)
    {
        aggregates.add(category);
    }

    /* Reads file, aggregates against fields in ArrayList<>, writes file to Hadoop */

    public AggregationResult aggregateFile(String fileName, String hadoopURL)
    {
        CSVReader reader = new CSVReader(new File(fileName));
        HadoopWriter writer = new HadoopWriter(hadoopURL, coreSite, hdfsSite);

        String line;

        while ((line = reader.readLine()) != null)
        {
            writer.writeLine(line);
        }

        return null;
    }
}
