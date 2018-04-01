/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.sort;

import com.thermalstrategies.eportal.controller.CustomerBuildingFloorBean;
import java.util.Comparator;

/**
 *
 * @author smurry
 */
public class CustomerSortCBF implements Comparator<CustomerBuildingFloorBean>{

    @Override
    public int compare(CustomerBuildingFloorBean o1, CustomerBuildingFloorBean o2)
    {
        try
        {
//           DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
           return o1.getCustomerName().compareTo(o2.getCustomerName());
        }
        catch(Exception e)
        {
            return 0;
        }

    }



}
