/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Site;
import java.util.List;

/**
 *
 * @author Stuart
 */
public class BuildingCommandBean extends Building {

    private String ced;
    private String action;
    private String actionparam;
    
    private int custId;
    private List<Site> siteList;
    private Integer site_id;

   

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getActionparam() {
        return actionparam;
    }

    public void setActionparam(String actionparam) {
        this.actionparam = actionparam;
    }

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }


    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    /**
     * @return the siteList
     */
    public List<Site> getSiteList() {
        return siteList;
    }

    /**
     * @param siteList the siteList to set
     */
    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }

    /**
     * @return the site_id
     */
    public Integer getSite_id() {
        return site_id;
    }

    /**
     * @param site_id the site_id to set
     */
    public void setSite_id(Integer site_id) {
        this.site_id = site_id;
    }

}
