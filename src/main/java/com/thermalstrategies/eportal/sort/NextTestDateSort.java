/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.sort;

import com.thermalstrategies.eportal.controller.NextTestDateBean;
import java.util.Comparator;

/**
 *
 * @author smurry
 */
public class NextTestDateSort implements Comparator<NextTestDateBean> {

    @Override
    public int compare(NextTestDateBean o1, NextTestDateBean o2) {
        try {
//           DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String customer1 = o1.getCustomerName();
            String customer2 = o2.getCustomerName();
           
            return customer1.compareTo(customer2);


        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
