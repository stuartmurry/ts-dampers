/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart Murry
 */
public class ListUserController implements Controller
{
    public static final String CREATE = "create";
    public static final String ACTION = "action";
    public static final String DELETE = "create";
    public static final String CANCEL = "cancel";
    public static final String ID = "id";
    
    private ApplicationManager applicationManager;
    public ListUserController(ApplicationManager applicationManager)
    {
        this.applicationManager = applicationManager;
    }
 
    public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
        ModelAndView mv = new ModelAndView("user/selectuser");
        List<UserCommandBean> userCommandBeanList = applicationManager.getUserManager().getUserList();
        mv.addObject("userCommandBeanList", userCommandBeanList);
        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }

}
