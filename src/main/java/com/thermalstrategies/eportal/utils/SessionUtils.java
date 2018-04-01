/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import com.thermalstrategies.eportal.model.Role;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stuart
 */
public class SessionUtils {

    public static void addRestriction(Integer custId, Integer buildingId, Integer floorId, StringBuffer sql) {
        addRestriction(null, null, null, custId, buildingId, floorId, sql);
    }

    public static void addRestriction(String occupancy, Integer status, Integer year, Integer custId, Integer buildingId, Integer floorId, StringBuffer sql) {
        List<String> restrictions = new ArrayList<String>();
        // Add security measure here
        // Make sure data is restricted to their own dataset if they're a customer

        // Make sure not to show decommissioned dampers.
        //restrictions.add(" ds.id != 6");
        
        if (status != null && !status.equals(0)) {
            restrictions.add(" ds.id=" + status);
        }

        if (year != null && !year.equals(0)) {
            restrictions.add(" year(dt.nextTestDate)=" + year);
        }

//        System.out.println("add restriction role: " + EPortalSecurityContext.getRole().getRole());
        String role = EPortalSecurityContext.getRole().getRole();
        if ("Customer".equalsIgnoreCase(role)) {
            restrictions.add(" c.id=" + EPortalSecurityContext.getCustomer().getId());
        } else if ("Employee".equalsIgnoreCase(role)) {
            restrictions.add(" c.id=" + custId);
            String customerIds = EPortalSecurityContext.getUser().getCustomers();
            String sqlInString = CustomStringUtils.commaDelimetedStringToSqlInString(customerIds);
            String[] strs = CustomStringUtils.commaDelimetedStringToArray(sqlInString);
            String finalString = "";
            if (!"".equals(sqlInString)) {
                finalString = "(";
                int count = 0;
                for (String s : strs) {
                    if (++count == 1) {
                        finalString = finalString + "c.id=" + s;
                    } else {
                        finalString = finalString + " or c.id=" + s;
                    }
                }
                finalString = finalString + ")";
            }
            
            restrictions.add(finalString);

        } else {
            restrictions.add(" c.id=" + custId);
        }

        if (buildingId != null && !buildingId.equals(0)) {
            restrictions.add(" b.id=" + buildingId);
        }
        if (floorId != null && !floorId.equals(0)) {
            restrictions.add(" bf.id=" + floorId);
        }

        if (occupancy != null && !"".equals(occupancy.trim())) {
            restrictions.add(" dt.occupancy='" + occupancy + "'");
        }

        if (restrictions.size() > 0) {
            sql.append(" where ");
        }
        int count = 0;
        for (String restriction : restrictions) {
            if (count++ > 0) {
                sql.append(" and " + restriction);
            } else {
                sql.append(restriction);
            }

        }
    }
}
