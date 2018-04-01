/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.SiteCommandBean;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Site;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class SiteManager {

    private HibernateTemplate tstratDamperTemplate;

    public SiteManager(HibernateTemplate tstratDamperTemplate) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public SiteCommandBean getSite(final Integer id) {
        return (SiteCommandBean) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Site site = (Site) session.load(Site.class, id);

                SiteCommandBean command = new SiteCommandBean();
                BeanUtils.copyProperties(site, command);
                System.out.println("Site -> " + command.getName());
                return command;
            }
        });
    }

    public List getSiteList(Integer custId) {
        List list = tstratDamperTemplate.find("from Site s inner join s.customer as c where c.id=" + custId);
        List<Site> siteList = new ArrayList<Site>();
        for (Object o : list) {
            Object[] arry = (Object[]) o;
            Site site = (Site) arry[0];
            siteList.add(site);
        }
        return siteList;
    }

    public List<Site> getSiteList() {
        return tstratDamperTemplate.find("from Site s");
    }

    public void saveSite(final SiteCommandBean command, final Integer custId) {

        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                Integer id = command.getId();
                Site site;
                if (id != null) {
                    site = (Site) session.load(Site.class, id);
                } else {
                    site = new Site();
                }
                
                if (custId > 0) {
                    Customer customer = (Customer)session.load(Customer.class, custId);
                    command.setCustomer(customer);
                }

                BeanUtils.copyProperties(command, site);
                tstratDamperTemplate.saveOrUpdate(site);

                return null;
            }
        });

    }

    public void deleteSite(final Integer siteId) throws DataAccessException {

        if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
            throw new HibernateException("You don't have permissions to delete this damper.  Contact your administrator.");
        }

        if (siteId > 0) {
            tstratDamperTemplate.execute(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Site s = (Site) session.load(Site.class, siteId);

                    if (s == null) {
                        throw new SQLException("Unable to locate site with id=" + siteId);
                    }

                    session.delete(s);

                    return null;
                }
            });
//            customerManager.reSynch();
        }

    }
}
