/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.TestCycleCommandBean;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Testcycle;
import com.thermalstrategies.eportal.utils.CustomDateUtils;
import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class TestCycleManager {
    private HibernateTemplate tstratDamperTemplate;

    public TestCycleManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }
    
    public void confirmTestCycle(Integer id) {
        Testcycle tc = (Testcycle)tstratDamperTemplate.get(Testcycle.class, id);
        tc.setComplete(true);
        tc.setFinishdate(new Date());
        tstratDamperTemplate.saveOrUpdate(tc);
    }

    public TestCycleCommandBean getTestCycleCommand(Integer id) {
        TestCycleCommandBean command = new TestCycleCommandBean();
        Testcycle tc = (Testcycle)tstratDamperTemplate.get(Testcycle.class, id);
        command.setsDate(CustomDateUtils.convertJasonMoonsDateToString(tc.getStartdate()));
        command.setfDate(CustomDateUtils.convertJasonMoonsDateToString(tc.getFinishdate()));

        BeanUtils.copyProperties(tc, command);

        return command;
    }

    public void saveTestCycleCommand(TestCycleCommandBean command) {
        Integer id = command.getId();

        if (id != null) {

            // existing test cycle
            Testcycle tc = (Testcycle)tstratDamperTemplate.get(Testcycle.class, id);
            tc.setDescription(command.getDescription());
            tc.setComplete(command.getComplete());
            tc.setStartdate(CustomDateUtils.convertJasonMoonsStringToDate(command.getsDate()));
            tc.setFinishdate(CustomDateUtils.convertJasonMoonsStringToDate(command.getfDate()));
            tstratDamperTemplate.save(tc);
        } else {
            // new TestCycle
            Testcycle tc = new Testcycle();
            Customer customer = (Customer)tstratDamperTemplate.load(Customer.class, command.getCustId());
            Building building = (Building)tstratDamperTemplate.load(Building.class, command.getBuildingId());
            tc.setCustomer(customer);
            tc.setBuilding(building);
            tc.setDescription(command.getDescription());
            tc.setComplete(command.getComplete());
            tc.setStartdate(CustomDateUtils.convertJasonMoonsStringToDate(command.getsDate()));
            tc.setFinishdate(CustomDateUtils.convertJasonMoonsStringToDate(command.getfDate()));
            tstratDamperTemplate.save(tc);

        }
    }

}
