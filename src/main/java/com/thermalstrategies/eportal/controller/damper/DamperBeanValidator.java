/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller.damper;

import org.springframework.validation.BindException;

/**
 *
 * @author Stuart Murry
 */
public class DamperBeanValidator
{
    private boolean hasErrors;
    private BindException errors;
    
    public DamperBeanValidator(BindException errors)
    {
        this.errors = errors;
    }
    
    protected void validateCustomer(int customerId)
    {
                if (customerId == 0)
                {
                    errors.rejectValue("customer_id", "error.dampertest.customer");
                    this.hasErrors = true;
                } 
    }
    
    
    /**
     * 
     * @param errors
     * @param buildingId
     * @param err
     * 
     */
    protected void validateBuilding(int buildingId)
    {
                if (buildingId == 0)
                {
                    errors.rejectValue("building_id", "error.dampertest.building");
                    this.hasErrors = true;
                } 
    }
    
    /**
     * 
     * @param errors
     * @param buildingfloorId
     * @param err
     */
    protected void validateBuildingfloor(int buildingfloorId)
    {
                if (buildingfloorId == 0)
                {
                    errors.rejectValue("buildingfloor_id", "error.dampertest.buildingfloor");
                    this.hasErrors = true;
                } 
    }
    
    
    protected void validateDampertype(int damperTypeId)
    {
                if (damperTypeId == 0)
                {
                    errors.rejectValue("dampertype_id", "error.dampertest.dampertype");
                    this.hasErrors = true;
                } 
    }
    
    /**
     * Concurrent floor login error.
     * @param forceError
     */
    protected void validateDummyLink(boolean forceError)
    {
                if (forceError)
                {
                    errors.rejectValue("dummy", "error.dampertest.concurrentfloorlogin");
                    this.hasErrors = true;
                }
    }
    
    public boolean hasErrors()
    {
        return hasErrors;
    }
    
    
}
