/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.utils;

import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author smurry
 */
public class CustomWebUtils {

    public static void setRoleInsideModel(ModelMap mv) {
        // Make sure if its a customer then turn off all admin features
        mv.addAttribute("role", EPortalSecurityContext.getRole());
        mv.addAttribute("user", EPortalSecurityContext.getUser());
    }

    public static void setRoleInsideModel(ModelAndView mv) {
        // Make sure if its a customer then turn off all admin features
        mv.addObject("role", EPortalSecurityContext.getRole());
        mv.addObject("user", EPortalSecurityContext.getUser());
//        String url = "http://dampers.thermalstrategies.com/aspx/reports.aspx";
//        try {
//            User user = EPortalSecurityContext.getUser();
//            url += "?id=" + user.getCustomer().getId();
//        } catch(Exception e) {
//        }
//        mv.addObject("reportsurl", url);
    }

    public static String getURL(HttpServletRequest request) {
        String uri = request.getRequestURI().split("/")[2];
        return uri + "?" + request.getQueryString();
    }

    public static ModelAndView getErrorModelAndView(String errorMsg) {
        System.out.println(errorMsg);
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("error", errorMsg);
        return mv;
    }

    public static Integer getRequestIntegerParameter(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);

        try {
            System.out.println("The parameter for " + paramName + " is " + param);
            if (param == null || "".equals(param.trim()) || "null".equals(param)) {
                // Let check the session for the same param
                return null;
            } else {
                Integer i = null;
                try {
                    i = Integer.parseInt(param);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return i;
            }
        } catch (Exception e) {
            //Handling undefined Strings
            return null;
        }
    }

    public static Integer getRequestIntegerParameterWithSessionChecking(HttpServletRequest request, String paramName) {
        String param = request.getParameter(paramName);

        try {
//        System.out.println("The parameter for " + paramName + " is " + param);
            if (param == null || "".equals(param)) {
                // Let check the session for the same param
                return (Integer) request.getSession().getAttribute(paramName);
            } else {
                return Integer.parseInt(param);
            }
        } catch (Exception e) {
            return null;
        }


    }

    public static Integer getRowCount(Criteria criteria) {

        criteria.setProjection(Projections.rowCount());
        List result = criteria.list();
        Integer rowCount = 0;
        if (!result.isEmpty()) {
            rowCount = (Integer) result.get(0);
//            System.out.println("Total row counts: " + rowCount);
        }

        return rowCount;
    }
}
