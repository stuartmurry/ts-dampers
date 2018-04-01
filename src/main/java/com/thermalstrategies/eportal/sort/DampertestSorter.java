/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.sort;

import com.thermalstrategies.eportal.controller.DamperListBean;
import com.thermalstrategies.eportal.model.Dampertest;
import java.util.Comparator;

/**
 *
 * @author Stuart Murry
 */
public class DampertestSorter implements Comparator<DamperListBean> {

    public enum SORTBYDAMPER {

        DEFAULT, CUSTOMER, BUILDING, FLOOR
    }

    public DampertestSorter() {
        // Natural Sort Order
    }

    public DampertestSorter(SORTBYDAMPER sort) {
        // Custom Sort Order
    }

    public int compare(DamperListBean o1, DamperListBean o2) {

        String customer1 = o1.getCustomer();
        String customer2 = o2.getCustomer();
        if (customer1.equalsIgnoreCase(customer2)) {
            String building1 = o1.getBuilding();
            String building2 = o2.getBuilding();
            if (building1.equalsIgnoreCase(building2)) {
                String floor1 = o1.getFloor();
                String floor2 = o2.getFloor();
                if (floor1.equalsIgnoreCase(floor2)) {
                    String alias1 = o1.getType();
                    String alias2 = o2.getType();
                    if (alias1.equalsIgnoreCase(alias2)) {
                        Integer damper1 = o1.getNumber();
                        Integer damper2 = o2.getNumber();
                        if (damper1.equals(damper2)) {
                            return o1.getSeries().compareTo(o2.getSeries());
                        }
                        return damper1.compareTo(damper2);
                    } else {
                        return alias1.compareToIgnoreCase(alias2);
                    }
                } else {
                    return compare(floor1, floor2);
                }
            } else {
                return building1.compareToIgnoreCase(building2);
            }
        } else {
            return customer1.compareToIgnoreCase(customer2);
        }
    }

    static char charAt(String s, int i) {
        if (i >= s.length()) {
            return 0;
        } else {
            return s.charAt(i);
        }
    }

    public int compare(String str1, String str2) {
        String a = str1.toLowerCase();
        String b = str2.toLowerCase();

        int ia = 0, ib = 0;
        int nza = 0, nzb = 0;
        char ca, cb;
        int result;

        while (true) {
            // only count the number of zeroes leading the last number compared
            nza = nzb = 0;

            ca = charAt(a, ia);
            cb = charAt(b, ib);

            // skip over leading spaces or zeros
            while (Character.isSpaceChar(ca) || ca == '0') {
                if (ca == '0') {
                    nza++;
                } else {
                    // only count consecutive zeroes
                    nza = 0;
                }

                ca = charAt(a, ++ia);
            }

            while (Character.isSpaceChar(cb) || cb == '0') {
                if (cb == '0') {
                    nzb++;
                } else {
                    // only count consecutive zeroes
                    nzb = 0;
                }

                cb = charAt(b, ++ib);
            }

            // process run of digits
            if (Character.isDigit(ca) && Character.isDigit(cb)) {
                if ((result = compareRight(a.substring(ia), b.substring(ib))) != 0) {
                    return result;
                }
            }

            if (ca == 0 && cb == 0) {
                // The strings compare the same.  Perhaps the caller
                // will want to call strcmp to break the tie.
                return nza - nzb;
            }

            if (ca < cb) {
                return -1;
            } else if (ca > cb) {
                return +1;
            }

            ++ia;
            ++ib;
        }
    }

    int compareRight(String a, String b) {
        int bias = 0;
        int ia = 0;
        int ib = 0;

        // The longest run of digits wins.  That aside, the greatest
        // value wins, but we can't know that it will until we've scanned
        // both numbers to know that they have the same magnitude, so we
        // remember it in BIAS.
        for (;; ia++, ib++) {
            char ca = charAt(a, ia);
            char cb = charAt(b, ib);

            if (!Character.isDigit(ca) && !Character.isDigit(cb)) {
                return bias;
            } else if (!Character.isDigit(ca)) {
                return -1;
            } else if (!Character.isDigit(cb)) {
                return +1;
            } else if (ca < cb) {
                if (bias == 0) {
                    bias = -1;
                }
            } else if (ca > cb) {
                if (bias == 0) {
                    bias = +1;
                }
            } else if (ca == 0 && cb == 0) {
                return bias;
            }
        }
    }
}
