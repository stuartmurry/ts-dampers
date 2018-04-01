/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Stuart
 */
public class EditRepairHistoryFromRepairHistoryList extends AbstractEditRepairHistoryController
{

    private ApplicationManager applicationManager;

    public EditRepairHistoryFromRepairHistoryList(ApplicationManager applicationManager) {
        super(applicationManager);
        this.applicationManager = applicationManager;
    }

    @Override
    protected ModelAndView processFormSubmission(HttpServletRequest request, HttpServletResponse response, Object command, BindException exception) throws Exception {
        RepairHistoryBean bean = (RepairHistoryBean) command;
        Integer damperId = CustomWebUtils.getRequestIntegerParameter(request, "damperId");
        Integer custId = CustomWebUtils.getRequestIntegerParameter(request, "custId");
        Integer buildingId = CustomWebUtils.getRequestIntegerParameter(request, "buildingId");
        Integer floorId = CustomWebUtils.getRequestIntegerParameter(request, "floorId");
        Integer pageNum = CustomWebUtils.getRequestIntegerParameter(request, "pageNum");
        Integer level = CustomWebUtils.getRequestIntegerParameter(request, "level");
//        ModelAndView mv = new ModelAndView("editDamper.htm?id=" + bean.getDamperId());
        ModelAndView mv;
        String back = request.getParameter("back");
        if ("index".equals(back))
        {
            mv = new ModelAndView(new RedirectView("listRepairHistoryFromIndex.htm?damperId=" + damperId));
        } else //"back=ListRepairHistoryFromDamperList"
        {
            mv = new ModelAndView(new RedirectView("listRepairHistoryFromDamperList.htm?damperId=" + damperId + "level=" + level + "&pageNum=" + pageNum + "&custId=" + custId + "&buildingId=" + buildingId + "&floorId=" + floorId));

        }
       
        String save = request.getParameter("Save");
        if (save != null) {

            bean.setDamperId(damperId);
            //<fmt:formatDate pattern="dd-MMM-yyyy" type="date" value="${command.date}" />
            try {
                applicationManager.getRepairHistoryManager().saveRepairHistory(bean);
            } catch(Exception e)
            {
                return CustomWebUtils.getErrorModelAndView("There was an error trying saving this repair history: " + e.getMessage());
            }
        }
        return mv;
    }

}
