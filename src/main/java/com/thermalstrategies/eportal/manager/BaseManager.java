/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.manager;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public abstract class BaseManager
{
    private HibernateTemplate template;
    protected BaseManager(HibernateTemplate template)
    {
        this.template = template;
    }
    
    public void save(Object o) throws DataAccessException
    {
        template.saveOrUpdate(o);
    }

}
