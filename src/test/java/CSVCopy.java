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

import com.inixsoftware.haggregate.data.AggregationResult;
import com.inixsoftware.haggregate.service.AggregaterService;

public class CSVCopy
{
    public static void main(String[] args)
    {
        AggregaterService service = new AggregaterService("/opt/hadoop-2.6.0/etc/hadoop/core-site.xml", "/opt/hadoop-2.6.0/etc/hadoop/hdfs-site.xml",
                "gender", "age");

        AggregationResult[] res = service.aggregateFiles(new String[]{"test.csv", "test2.csv"}, new String[]{"hdfs://localhost:9000/test.csv",
                "hdfs://localhost:9000/test2.csv"});

        for (AggregationResult re : res)
        {
            System.out.println("\n\nNEW FILE: \n");
            System.out.println(re);
        }


    }
}
