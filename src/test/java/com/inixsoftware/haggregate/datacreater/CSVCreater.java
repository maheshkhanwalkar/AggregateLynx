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

package com.inixsoftware.haggregate.datacreater;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVCreater
{
    public static void main(String[] args) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("test.csv"));
        bw.write("fname,lname,age,gender,income,state,zip\n");

        for (int i = 0; i < 10; i++)
        {
            StringBuilder str = new StringBuilder();

            str.append(ValueGen.createFirstName());
            str.append(",");

            str.append(ValueGen.createLastName());
            str.append(",");

            str.append(ValueGen.createAge());
            str.append(",");

            str.append(ValueGen.createGender());
            str.append(",");

            str.append(ValueGen.createIncome());
            str.append(",");

            str.append(ValueGen.createState());
            str.append(",");

            str.append(ValueGen.createZipCode());

            bw.write(str.toString());
            bw.write("\n");
        }

        bw.flush();
        bw.close();

    }
}
