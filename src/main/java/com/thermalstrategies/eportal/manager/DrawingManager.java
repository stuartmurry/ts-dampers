/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.DrawingCommandBean;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Drawing;
import org.springframework.beans.BeanUtils;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class DrawingManager {
    private HibernateTemplate tstratDamperTemplate;

    public DrawingManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public DrawingCommandBean getDrawingCommand(Integer id, Integer custId, Integer buildingId, Integer floorId) {
        DrawingCommandBean command = new DrawingCommandBean();
        if (id == null) {
            Customer customer = (Customer)tstratDamperTemplate.load(Customer.class, custId);
            Building building = (Building)tstratDamperTemplate.load(Building.class, buildingId);
            Buildingfloor bf = (Buildingfloor)tstratDamperTemplate.load(Buildingfloor.class, floorId);

            command.setCustomerId(custId);
            command.setCustomerName(customer.getCustomerName());
            command.setBuildingId(buildingId);
            command.setBuildingName(building.getBuildingName());
            command.setFloorId(floorId);
            command.setFloorName(bf.getFloorName());

            return command;
        }
        Drawing d = (Drawing)tstratDamperTemplate.get(Drawing.class, id);

        BeanUtils.copyProperties(d, command);

        return command;
    }


    public DrawingCommandBean getDrawingCommand(Integer id) {
        DrawingCommandBean command = new DrawingCommandBean();
        if (id == null) {
            return command;
        }
        Drawing d = (Drawing)tstratDamperTemplate.get(Drawing.class, id);

        BeanUtils.copyProperties(d, command);

        return command;
    }

    public void saveDrawingCommand(DrawingCommandBean command) throws Exception{

        Drawing d;
        int id = command.getId();
        if (id > 0) {
            d = (Drawing)tstratDamperTemplate.load(Drawing.class, id);
        } else {
            d = new Drawing();
        }
        
        BeanUtils.copyProperties(command, d);
        if (!command.getFile().isEmpty()) {
            d.setDrawing(command.getFile().getBytes());
        }
        tstratDamperTemplate.save(d);
    }

}
