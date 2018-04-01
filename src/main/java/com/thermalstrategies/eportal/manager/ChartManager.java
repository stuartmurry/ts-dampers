/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import java.io.File;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public class ChartManager {

    private HibernateTemplate tstratDamperTemplate;

    public ChartManager(HibernateTemplate tstratDamperTemplate) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public void getPassFailChart(File file) {


        String curDir = file.getAbsolutePath();
        System.out.println(curDir);

    }
}
