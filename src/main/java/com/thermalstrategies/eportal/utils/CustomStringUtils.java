package com.thermalstrategies.eportal.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

;

public class CustomStringUtils {

    public static void main(String[] args) {
        String includedBuildingIds = "";

        Set<Integer> intSet = new HashSet<Integer>();
        if (!includedBuildingIds.trim().isEmpty()) {
            String[] splits = includedBuildingIds.split(",");
            for (String s : splits) {

                try {
                    int a = Integer.parseInt(s);
                    intSet.add(a);
                } catch (Exception e) {
                }

            }
        }

        for (int s : intSet) {
            System.out.println(s);
        }
    }

    public static String intArrayToCommaSeparatedString(int[] buildingIds) {

        StringBuilder nameBuilder = new StringBuilder();

        for (int n : buildingIds) {
            nameBuilder.append(String.format("%s,", n));
        }

        nameBuilder.deleteCharAt(nameBuilder.length() - 1); // Remove trailing comma

        return nameBuilder.toString();
    }

    public static Set<Integer> getIntSet(String commaDelimitedString) {
        Set<Integer> intSet = new HashSet<Integer>();
        if (!commaDelimitedString.trim().isEmpty()) {
            String[] splits = commaDelimitedString.split(",");
            for (String s : splits) {

                try {
                    int a = Integer.parseInt(s);
                    intSet.add(a);
                } catch (Exception e) {
                }

            }
        }
        return intSet;
    }

    /**
     * for example<br>
     * String s = format("Hola my friend ?", "Juan"); <br>
     * output for s will be "Hola my friend Juan"
     *
     * @param s
     * @param args
     * @return
     */
    public static String format(String s, Object... args) {
        String formattedString = "";
        String[] splits = s.split("\\?");
        int i = 0;
        for (String splitted : splits) {
            formattedString = formattedString + splitted + args[i++];
        }
        return formattedString;

    }

    public static String commaDelimetedStringToSqlInString(String commaSeparatedString) {
        if (commaSeparatedString != null) {
            String[] splits = commaSeparatedString.split(",");
            List<String> strList = new ArrayList<String>();
            String outputString = "";
            for (String s : splits) {
                Integer i;
                try {
                    i = Integer.parseInt(s.trim());
                    strList.add("" + i);
                } catch (Exception e) {
                    i = null;
                }
            }
            int count = 0;
            int listSize = strList.size();
            for (String s : strList) {
                if (++count != listSize) {
                    outputString = outputString + s + ",";
                } else {
                    outputString = outputString + s;
                }
            }

            return outputString;
        } else {
            return "";
        }

    }

    public static String[] commaDelimetedStringToArray(String commaSeparatedString) {
        return commaDelimetedStringToSqlInString(commaSeparatedString).split(",");
    }
}
