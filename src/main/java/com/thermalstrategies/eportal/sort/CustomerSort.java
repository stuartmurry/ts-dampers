/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.sort;

import com.thermalstrategies.eportal.model.Customer;
import java.util.Comparator;

/**
 *
 * @author smurry
 */
public class CustomerSort implements Comparator<Customer>{

    @Override
    public int compare(Customer o1, Customer o2)
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
