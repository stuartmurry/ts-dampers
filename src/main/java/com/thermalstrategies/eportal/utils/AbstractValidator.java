/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.utils;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Modernizes the spring framework to handle generic type inputs
 * @author Stuart
 */
public abstract class AbstractValidator<E> implements Validator{

    public boolean supports(Class arg0) {
        return true;
    }

    public abstract void doValidate(E e, Errors errors);

    public void validate(Object o, Errors errors) {
        doValidate((E) o, errors);
    }

}
