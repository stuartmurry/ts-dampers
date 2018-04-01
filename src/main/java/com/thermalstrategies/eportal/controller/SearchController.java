/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.manager.ApplicationManager;
import com.thermalstrategies.eportal.search.Search;
import com.thermalstrategies.eportal.search.Search.Results;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustomWebUtils;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 *
 * @author Stuart
 */
public class SearchController implements Controller {

    private ApplicationManager applicationManager;
    private Search search;

    public SearchController(ApplicationManager applicationManager, Search search) {
        this.applicationManager = applicationManager;
        this.search = search;
    }

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("searchresults");
        String query = request.getParameter("query");
        mv.addObject("query", query);
        Integer currentPage = CustomWebUtils.getRequestIntegerParameter(request, "page");
        if (currentPage == null) {
            currentPage=0;
        }
        try {
            Integer customerId = 0;
            if (EPortalSecurityContext.getUser().getCustomer() != null) {
                customerId = EPortalSecurityContext.getUser().getCustomer().getId();
            }
            List<Results> resultList = search.search(query, 100, customerId, currentPage);
//            System.out.println("Result List Size->" + resultList.size());
            mv.addObject("resultList", resultList);

            for (Results r : resultList) {
                mv.addObject("showing", r.getVisibleHits());
                mv.addObject("hits", r.getTotalHits());
                mv.addObject("totalPageList", r.getTotalPageList());
                mv.addObject("currentPage", r.getCurrentPage());
                mv.addObject("pageSize", r.getPageSize());

                mv.addObject("queryUrl", URLEncoder.encode(query));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        CustomWebUtils.setRoleInsideModel(mv);

        return mv;
    }
}
