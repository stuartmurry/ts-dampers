/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import com.thermalstrategies.eportal.controller.damper.DamperTypeCommandBean;
import com.thermalstrategies.eportal.utils.AbstractValidator;
import org.springframework.validation.Errors;

/**
 *
 * @author Stuart
 */
public class SelectDamperTypeValidator extends AbstractValidator<DamperTypeCommandBean>{

    @Override
    public void doValidate(DamperTypeCommandBean e, Errors errors) {
        if (e.getDampertype_id() == 0)
        {
            errors.rejectValue("dampertype_id", "error.dampertest.dampertype");
        }
    }

}
