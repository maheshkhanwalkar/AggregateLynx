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

package com.inixsoftware.haggregate.data;

import java.util.*;

public class AggregationResult
{
   private ArrayList<HashMap<String, Integer>> list;
   private ArrayList<String> aggregates;

   public AggregationResult(ArrayList<HashMap<String, Integer>> list, ArrayList<String> aggregates)
   {
      this.list = list;
      this.aggregates = aggregates;
   }

   @Override
   public String toString()
   {
      String result = "";

      for (int i = 0; i < aggregates.size(); i++)
      {
         result += aggregates.get(i) + "\n======================================\n";

         HashMap<String, Integer> map = list.get(i);
         Set<Map.Entry<String, Integer>> entries = map.entrySet();

         for (Map.Entry<String, Integer> entry : entries)
         {
            result += entry.getKey() + "   -   " + entry.getValue() + "\n";
         }

         result += "\n";
      }

      return result;
   }

   //TODO
}
