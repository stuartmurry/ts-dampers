/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import org.springframework.validation.BindException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 *
 * @author Stuart
 */
public class EditSiteController extends SimpleFormController {

    private ApplicationManager applicationManager;
    private static final String[] LETTERS = {"", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    public EditSiteController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
        setCommandClass(SiteCommandBean.class);
    }

    @Override
    protected ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException bindException) throws Exception {
        System.out.println("show form...");
        ModelAndView mv = super.showForm(request, response, bindException);
        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");

        Boolean b = (Boolean) request.getSession().getAttribute("error_site");
        if (b != null && b==true) {
            request.getSession().setAttribute("error_site", false);
            return new ModelAndView("expired");
        }

        SiteCommandBean command = new SiteCommandBean();
        if (id != null){
            command = applicationManager.getSiteManager().getSite(id);
        }
        
        mv.setViewName("client/editsite");
        CustomWebUtils.setRoleInsideModel(mv);
        mv.addObject("command", command);
        return mv;

    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object o, BindException errors) throws Exception {
        //<a href="listDampers.htm?custId=${cust.id}&buildingId=${building.id}&floorId=${floor.id}" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${floor.floorName}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
        System.out.println("Form Submission");

        Integer id = CustomWebUtils.getRequestIntegerParameter(request, "id");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");

        ModelAndView mv = new ModelAndView("success");
//        ModelAndView mv = new ModelAndView(new RedirectView("listRepairHistory.htm?damperId=" + bean.getDamperId()));
        String save = request.getParameter("Save");
        if (save != null) {
            try {
                SiteCommandBean command = (SiteCommandBean)o;
                command.setId(id);

                applicationManager.getSiteManager().saveSite(command, custId);
                mv.addObject("message", "Site successfully saved.");
            } catch (Exception e) {
                return CustomWebUtils.getErrorModelAndView("Error: Something happened when we were trying to save this site: : " + e.getMessage());
            }
        } else {
            mv.addObject("message", "Invalid Submit");
        }
        
        CustomWebUtils.setRoleInsideModel(mv);

        return mv;

    }





}
