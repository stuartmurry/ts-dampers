/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.sort;

import com.thermalstrategies.eportal.controller.NextTestBuildingBean;
import com.thermalstrategies.eportal.controller.NextTestDateBean;
import java.util.Comparator;

/**
 *
 * @author smurry
 */
public class NextTestBuildingSort implements Comparator<NextTestBuildingBean> {

    @Override
    public int compare(NextTestBuildingBean o1, NextTestBuildingBean o2) {
        try {
//           DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            String customer1 = o1.getBuildingName();
            String customer2 = o2.getBuildingName();
           
            return customer1.compareTo(customer2);


        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
}
