/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.client;

import com.thermalstrategies.eportal.model.Buildingfloor;

/**
 *
 * @author Stuart
 */
public class FloorCommandBean extends Buildingfloor
{
    private String ced;
    private String action;
    private String actionparam;
    private Integer floor;
    private Integer buildId;

    public String getCed() {
        return ced;
    }

    public void setCed(String ced) {
        this.ced = ced;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setActionparam(String actionparam) {
        this.actionparam = actionparam;
    }

    public String getActionparam() {
        return actionparam;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getBuildId() {
        return buildId;
    }

    public void setBuildId(Integer buildId) {
        this.buildId = buildId;
    }

}
