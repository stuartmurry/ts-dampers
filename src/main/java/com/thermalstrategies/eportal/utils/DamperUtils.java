/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 *
 * @author Stuart
 */
public class DamperUtils {

    public enum OCCUPANCY {

        HEALTH_CARE, BUSINESS, NEW_CONSTRUCTION
    }

    /**
     * Calculate next test date.  Business = 6 years + date tested
     * @param occ
     * @return
     */
    public static Date predictNextTestDate(Date dateTested, OCCUPANCY occ) {
        Calendar c = Calendar.getInstance();
        c.setTime(dateTested);
        // include 6 years
        if (occ == occ.BUSINESS) {
            c.add(Calendar.YEAR, 6);
            return c.getTime();
        } else if (occ == occ.NEW_CONSTRUCTION) {
            c.add(Calendar.YEAR, 1);
            return c.getTime();
        } // include only 4 years
        else {
            c.add(Calendar.YEAR, 4);
            return c.getTime();
        }
    }

    public static String parseComments(byte[] comments) {
        if (comments != null) {
            return new String(comments);
        } else {
            return "";
        }
    }

    public static Integer getNextInSequence(String commaDelimetedSequence) {

        String[] splits = commaDelimetedSequence.split(",");

        List<Integer> sequenceList = new ArrayList<Integer>();
        for (String s : splits) {
            sequenceList.add(Integer.parseInt(s.trim()));
        }
        Collections.sort(sequenceList);

        // Find the break...
        Integer z_minus_1 = 0;
        for (Integer z : sequenceList) {
            if (!((z - z_minus_1 == 0) || (z - z_minus_1 == 1))) {
                break;
            }
            z_minus_1 = z;

        }
        return z_minus_1 + 1;
    }

    public static Integer getDamperNumberFromAliasId(String damperAliasId) {
        Integer damperNumber = 0;
        try {
            damperNumber = Integer.parseInt(damperAliasId.split("-")[2].split("[a-zA-Z]")[0]);
        } catch (Exception e) {
        }
        return damperNumber;
    }
    private static final String[] LETTERS = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public static Integer letterToNumber(String letter) {

        try {
            for (int i = 0; i < LETTERS.length; i++) {
                // increment operator not working
                String le = LETTERS[i];
                if (le.equalsIgnoreCase(letter)) {
                    return i;
                }
            }
        } catch (Exception e) {
        }
        // End of list reached. Just return zero
        return 0;

    }

    public static String getDamperLetterFromAliasId(String damperAliasId) {

        String letter = "";
        try {
            String[] splits = damperAliasId.split("-");
            String damperNumber = splits[2];
            Pattern p = Pattern.compile("[a-zA-Z]");
            String[] words = new String[]{""};
            words = damperNumber.split("[\\d]");
            for (int i = 0; i < words.length; i++) {
                System.out.print(words[i]);
                if (p.matcher(words[i]).matches()) {
                    letter = words[i];
                }
            }
        } catch (Exception e) {
        }
        return letter;
    }

    public static void main(String[] args) {
//        System.out.println(predictNextTestDate(new Date(), OCCUPANCY.BUSINESS));
        System.out.println(getDamperLetterFromAliasId("RL-FD-005D-S"));
    }
}
