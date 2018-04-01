/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 * @author Administrator
 */
public class HinchcliffeController implements Controller {

    private ApplicationManager applicationManager;

    public HinchcliffeController(ApplicationManager applicationManager) {
        this.applicationManager = applicationManager;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ModelAndView mv = new ModelAndView("hinchcliffe");

        // Main Campus
        List<PassFailBean> winthropMainCampus = applicationManager.getStatisticsManager().getCustomPassFailByBuildingIds(new int[]{27, 31, 32, 33, 34, 35, 36, 44});
        PassFailBean mainCampusTotal = new PassFailBean();
        mainCampusTotal.setPass(0);
        mainCampusTotal.setFail(0);
        mainCampusTotal.setInaccessible(0);
        mainCampusTotal.setPending(0);
        mainCampusTotal.setCustomerName("");
        for (PassFailBean p : winthropMainCampus) {
            mainCampusTotal.setPass(mainCampusTotal.getPass() + p.getPass());
            mainCampusTotal.setFail(mainCampusTotal.getFail() + p.getFail());
            mainCampusTotal.setInaccessible(mainCampusTotal.getInaccessible() + p.getInaccessible());
            mainCampusTotal.setPending(mainCampusTotal.getPending() + p.getPending());
        }
        
        mv.addObject("winthropMainCampus", winthropMainCampus);
        mv.addObject("mainCampusTotal", mainCampusTotal);
        
        // Offsites
        List<PassFailBean> winthropOffsites = applicationManager.getStatisticsManager().getCustomPassFailByBuildingIds(new int[]{40,41,42,43,45});
        PassFailBean offSitesTotal = new PassFailBean();
        offSitesTotal.setPass(0);
        offSitesTotal.setFail(0);
        offSitesTotal.setInaccessible(0);
        offSitesTotal.setPending(0);
        offSitesTotal.setCustomerName("");
        for (PassFailBean p : winthropOffsites) {
            offSitesTotal.setPass(offSitesTotal.getPass() + p.getPass());
            offSitesTotal.setFail(offSitesTotal.getFail() + p.getFail());
            offSitesTotal.setInaccessible(offSitesTotal.getInaccessible() + p.getInaccessible());
            offSitesTotal.setPending(offSitesTotal.getPending() + p.getPending());
        }
        
        mv.addObject("winthropOffsites", winthropOffsites);
        mv.addObject("offSitesTotal", offSitesTotal);
        
        // Research and academic center
        List<PassFailBean> winthropResearch = applicationManager.getStatisticsManager().getCustomPassFailByBuildingIds(new int[]{51});
        PassFailBean researchTotal = new PassFailBean();
        researchTotal.setPass(0);
        researchTotal.setFail(0);
        researchTotal.setInaccessible(0);
        researchTotal.setPending(0);
        researchTotal.setCustomerName("");
        for (PassFailBean p : winthropResearch) {
            researchTotal.setPass(researchTotal.getPass() + p.getPass());
            researchTotal.setFail(researchTotal.getFail() + p.getFail());
            researchTotal.setInaccessible(researchTotal.getInaccessible() + p.getInaccessible());
            researchTotal.setPending(researchTotal.getPending() + p.getPending());
        }
        
        mv.addObject("winthropResearch", winthropResearch);
        mv.addObject("researchTotal", researchTotal);
        

        CustomWebUtils.setRoleInsideModel(mv);
        return mv;
    }
}
