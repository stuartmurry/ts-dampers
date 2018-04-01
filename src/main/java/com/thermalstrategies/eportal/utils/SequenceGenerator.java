/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 *
 * @author smurry
 */
public class SequenceGenerator {

    private static final String[] LETTERS = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    // Create new List for every dampertype:
    List<String> sdList = new ArrayList();
    List<String> fdList = new ArrayList();
    List<String> csfdList = new ArrayList();

    public enum DAMPER_TYPE {

        SD, FD, CFSD
    }

    public SequenceGenerator(List<String> aliasIdList) {
        init(aliasIdList);
    }

    private void init(List<String> aliasIdList) {
        // divide lists by dampertype: SD,FD, CSFD  Then find the next number
        String SD = DAMPER_TYPE.SD.toString();
        String FD = DAMPER_TYPE.FD.toString();
        String CFSD = DAMPER_TYPE.CFSD.toString();
        for (String s : aliasIdList) {
            if (s != null) {
                String[] splits = s.split("-");
                String damperType = splits[1];

                if (SD.equalsIgnoreCase(damperType)) {
                    sdList.add(s);
                } else if (FD.equalsIgnoreCase(damperType)) {
                    fdList.add(s);
                } else if (CFSD.equalsIgnoreCase(damperType)) {
                    csfdList.add(s);
                }
            }
        }

    }

    private List<MyKey> splitList(String damperAliasId) {

        List<String> damperList;
        if ("SD".equals(damperAliasId)) {
            damperList = sdList;
        } else if ("FD".equals(damperAliasId)) {
            damperList = fdList;
        } else if ("CFSD".equals(damperAliasId)) {
            damperList = csfdList;
        } else {
            damperList = new ArrayList<String>();
        }

        List<MyKey> numberLetterList = new ArrayList<MyKey>();
        for (String sd : damperList) {

            String[] splits = sd.split("-");
            String damperNumber = splits[2];
            Pattern p = Pattern.compile("[a-zA-Z]");
            String letter = "";
            String[] words = new String[]{""};
            
            try {
                words = damperNumber.split("[\\d]");
                for (int i = 0; i < words.length; i++) {
                    if (p.matcher(words[i]).matches()) {
                        letter = words[i];
                    }
                }
            } catch (Exception e) {
            }

            try {
                words = damperNumber.split("[a-zA-Z]");
            } catch (Exception e) {
            }

            MyKey key = new MyKey(0);
            key.setKey(Integer.parseInt(words[0]));
            key.setLetter(letter);
            numberLetterList.add(key);
        }

        Collections.sort(numberLetterList);

        return numberLetterList;

    }

    private int getNewDamperNumber(String damperAliasId) {
        List<MyKey> keyList = splitList(damperAliasId);
        if (!keyList.isEmpty()) {
            int keyMinus1 = 0;
            for (MyKey key : keyList) {
                int k = key.getKey();
//                System.out.println("Key: " + k);
                // The difference should always be one.  If not then there is a break in the sequence and we should filll in the gaps
                int diff = k - keyMinus1;
                if (diff != 1) {
                    if (diff != 0) {
//                        System.out.println("break... difference->" + diff);
                        break;
                    }
                }
                keyMinus1 = k;
            }
            return keyMinus1 + 1;
        } else {
            return 1;
        }
    }

    /**
     * There are three fire damper types: SD, FD, and CSFD
     * @param aliasIdList
     * @return
     */
    public String getNewSingleUnitDamperNumber(String damperAliasId, String buildingAlias, String floorName) {

        int newDamperNumber = getNewDamperNumber(damperAliasId);
        String aliasId =  (buildingAlias + "-" + damperAliasId + "-" + String.format("%03d", newDamperNumber) + "-" + floorName).toUpperCase();
        return aliasId;
    }

    public String getNewMultiUnitDamperNumber(String damperAliasId, String buildingAlias, String floorName) {
        return getNewMultiUnitDamperNumber(damperAliasId, buildingAlias, floorName, 0);
    }

    public String getNewMultiUnitDamperNumber(String damperAliasId, String buildingAlias, String floorName, int damperNumber) {
        int newDamperNumber;
        String damperLetter;
        if (damperNumber == 0) {
            newDamperNumber = getNewDamperNumber(damperAliasId);
            damperLetter = "A";
        } else {
            newDamperNumber = damperNumber;
            List<MyKey> keyList = splitList(damperAliasId);

            if (!keyList.isEmpty()) {
                int keyMinus1 = 0;
                for (MyKey key : keyList) {
                    String l = key.getLetter().toUpperCase();
//                System.out.println("Key: " + k);
                    // The difference should always be one.  If not then there is a break in the sequence and we should filll in the gaps
                    int k = 0;
                    for (int i = 0; i < 26; i++) {
                        if (l.equals(LETTERS[i])) {
                            k = i;
                        }
                    }
                    int diff = k - keyMinus1;
                    if (diff != 1) {
                        if (diff != 0) {
//                        System.out.println("break... difference->" + diff);
                            break;
                        }
                    }
                    keyMinus1 = k;
                }
                damperLetter = LETTERS[keyMinus1 + 1];
            } else {
                damperLetter = "A";
            }

        }
        String aliasId = (buildingAlias + "-" + damperAliasId + "-" + String.format("%03d", newDamperNumber) + damperLetter + "-" + floorName).toUpperCase();

        return aliasId;

    }

    public class MyKey implements Comparable<MyKey> {

        private Integer key;
        private String letter;

        MyKey(Integer key) {
            this.key = key;
        }

        /**
         * @return the key
         */
        public Integer getKey() {
            return key;
        }

        /**
         * @param key the key to set
         */
        public void setKey(Integer key) {
            this.key = key;
        }

        public int compareTo(MyKey o) {
            if (this.key == o.getKey()) {
                return this.letter.compareTo(o.getLetter());
            }
            return this.key.compareTo(o.getKey());
        }

        /**
         * @return the letter
         */
        public String getLetter() {
            return letter;
        }

        /**
         * @param letter the letter to set
         */
        public void setLetter(String letter) {
            this.letter = letter;
        }
    }
}
