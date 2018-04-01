/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.sort;

import com.thermalstrategies.eportal.model.Dampertest;
import java.util.Comparator;

/**
 *
 * @author Stuart Murry
 */
public class DampertestSortByHighestSeries implements Comparator {

    /***
     * Sorts the damper entity by damper number then by damper series
     * @param o1
     * @param o2
     * @return
     */
    public int compare(Object o1, Object o2) {
        Dampertest dampertest1 = (Dampertest) o1;
        Dampertest dampertest2 = (Dampertest) o2;

        return dampertest1.getSeries() - dampertest2.getSeries();
    }
}
