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

import java.util.Random;

public class ValueGen
{
    private static final Random r = new Random();
    private static final String letters = "abcdefghijklmnopqrstuvwxyz";

    private static final String[] states = new String[]
    {
            "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA",
            "HI", "ID", "IL", "IN", "IA", "KS", "KY", "LA", "ME", "MD",
            "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
            "NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC",
            "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV", "WI", "WY"
    };

    public static String createFirstName()
    {
        int len = r.nextInt(11) + 5; //5 to 15

        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++)
        {
            builder.append(letters.charAt(r.nextInt(26)));
        }

        return builder.toString();
    }

    public static String createLastName()
    {
        int len = r.nextInt(26) + 5; //5 to 25

        StringBuilder builder = new StringBuilder(len);
        for (int i = 0; i < len; i++)
        {
            builder.append(letters.charAt(r.nextInt(26)));
        }

        return builder.toString();
    }

    public static int createAge()
    {
        return r.nextInt(100) + 1;
    }

    public static char createGender()
    {
        return r.nextBoolean() ? 'M' : 'F';
    }

    public static int createIncome()
    {
        return r.nextInt(1000000) + 50000;
    }

    public static String createState()
    {
        return states[r.nextInt(50)];
    }

    public static String createZipCode()
    {
        StringBuilder zip = new StringBuilder(5);
        for (int i = 0; i < 5; i++)
        {
            zip.append(Integer.toString(r.nextInt(10)));
        }

        return zip.toString();
    }

}
