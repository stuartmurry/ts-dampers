/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.client.BuildingCommandBean;
import com.thermalstrategies.eportal.model.Building;
import com.thermalstrategies.eportal.model.Buildingfloor;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.Dampertest;
import com.thermalstrategies.eportal.model.Site;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class BuildingManager {

    private HibernateTemplate tstratDamperTemplate;
    private CustomerManager customerManager;

    public BuildingManager(HibernateTemplate tstratDamperTemplate, CustomerManager customerManager) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
        this.customerManager = customerManager;
    }

    public void deleteBuilding(Building building) throws DataAccessException {
        tstratDamperTemplate.delete(building);
//        customerManager.reSynch();
    }

    public Building getBuilding(Integer id) throws DataAccessException {
        return (Building) tstratDamperTemplate.load(Building.class, id);
    }

    public void saveBuilding(final BuildingCommandBean command, final Integer custId) throws DataAccessException {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Building building;
                if (command.getId() != null && command.getId() > 0) {
                    building = (Building) session.load(Building.class, command.getId());
                } else {
                    building = new Building();
                }

                BeanUtils.copyProperties(command, building);

                Integer siteId = command.getSite_id();
                if (siteId >= 0) {
                    Site site = (Site) session.load(Site.class, siteId);
                    building.setSite(site);
                }

                if (custId != null) {
                    Customer customer = (Customer) session.load(Customer.class, custId);
                    building.setCustomer(customer);
                }

                session.saveOrUpdate(building);

                return null;
            }
        });

    }

    public List<Building> getBuildingList() throws DataAccessException {
        return tstratDamperTemplate.loadAll(Building.class);
    }

    public List<Building> getBuildingListFromCustomerId(Integer customerId) throws DataAccessException {
        System.out.println("getBuildingListFromCustomerId() ->" + customerId);
        List<Building> buildingList = new ArrayList<Building>();

        if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
            customerId = EPortalSecurityContext.getCustomer().getId();
        }
        List list = tstratDamperTemplate.find("from Building b inner join b.customer as c where c.id=" + customerId);

        for (Object o : list) {
            Object[] oo = (Object[]) o;
//            System.out.println("o->" + oo[0]);
            buildingList.add((Building) oo[0]);
//            System.out.println("o->" + oo[1]);
        }

//        System.out.println("building count: " + list.size());

        return buildingList;
    }


    public Buildingfloor getFloor(Integer id) {
        return (Buildingfloor) tstratDamperTemplate.load(Buildingfloor.class, id);
    }

    public void saveBuildingFloor(Buildingfloor floor) {
        tstratDamperTemplate.saveOrUpdate(floor);
//        customerManager.reSynch();
    }

    public void saveFloor(final Integer buildId, final Buildingfloor floor) throws DataAccessException {

        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                int floorId = floor.getId() == null ? 0 : floor.getId();
                Buildingfloor buildingfloor;
                if (floorId > 0) {
                    buildingfloor = (Buildingfloor) session.load(Buildingfloor.class, floorId);

                    BeanUtils.copyProperties(buildingfloor, floor);
                    Building building = (Building) session.load(Building.class, buildId);
                    buildingfloor.setBuilding(building);
                    session.update(buildingfloor);
                } else {
                    buildingfloor = new Buildingfloor();
                    BeanUtils.copyProperties(buildingfloor, floor);
                    Building building = (Building) session.load(Building.class, buildId);
                    buildingfloor.setBuilding(building);
                    session.save(buildingfloor);
                }
                return null;
            }
        });
//        customerManager.reSynch();

    }

    public void deleteFloor(Buildingfloor floor) {
        tstratDamperTemplate.delete(floor);
//        customerManager.reSynch();
    }

    public void deleteFloor(final Integer buildingfloorId) throws DataAccessException {
        if (buildingfloorId > 0) {
            tstratDamperTemplate.execute(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Buildingfloor b = (Buildingfloor) session.load(Buildingfloor.class, buildingfloorId);

                    String floorName = b.getFloorName();
                    Set<Dampertest> floorSet = b.getDampertests();
                    String msg = "Damper Test Associations: <br><br>";
                    int count = 0;
                    for (Dampertest dt : floorSet) {
                        ++count;
                        msg = msg + dt.getAliasId() + "<br>";
                    }
                    if (count > 0) {
                        // This customer has project associations
                        throw new SQLException("Unable to delete floor: " + floorName + " - There are Damper Test(s) referencing this floor.  "
                                + "<br>Remove the dampertests from the floor then delete the floor.<br><br>" + msg);
                    } else {
                        session.delete(b);
                    }
                    return null;
                }
            });
        }
//        customerManager.reSynch();
    }

    public List<Buildingfloor> getFloorList(Integer buildingId) throws DataAccessException {
        return tstratDamperTemplate.loadAll(Buildingfloor.class);
    }

    public List<Integer> getFloorIdListByBuildingId(final Integer buildingId) throws DataAccessException {
        return (List<Integer>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Building b = (Building) session.load(Building.class, buildingId);

                if (b == null) {
                    throw new SQLException("Unable to locate the building with id=" + buildingId);
                }

                StringBuffer sql = new StringBuffer();

                sql.append(" SELECT \n");
                sql.append(" bf.id \n");
                sql.append(" from \n");
                sql.append(" Buildingfloor bf \n");
                sql.append(" inner join bf.building as b \n");
                sql.append(" where b.id=" + buildingId);

                List<Integer> idList = new ArrayList<Integer>();
                Query query = session.createQuery(sql.toString());
                Object[] row = (Object[]) query.uniqueResult();
                if (row != null) {

                    idList.add(CustomNumberUtils.parseInt(row[0]));
                }

                return idList;
            }
        });
    }

    public void deleteBuilding(final Integer buildingId) throws DataAccessException {
        if (buildingId > 0) {
            tstratDamperTemplate.execute(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Building b = (Building) session.load(Building.class, buildingId);

                    if (b == null) {
                        throw new SQLException("Unable to locate the building with id=" + buildingId);
                    }
                    String buildingName = b.getBuildingName();
                    Set<Buildingfloor> floorSet = b.getBuildingfloors();
                    String msg = "Floor Associations: <br><br>";
                    int count = 0;
                    for (Buildingfloor floor : floorSet) {
                        ++count;
                        msg = msg + floor.getFloorName();
                    }
                    if (count > 0) {
                        // This customer has project associations
                        throw new SQLException("Unable to delete building: " + buildingName + " - There are floor(s) referencing this building.  "
                                + "<br>Remove the floors from the building then delete the building.<br><br>" + msg);
                    } else {
                        session.delete(b);
                    }
                    return null;
                }
            });
//            customerManager.reSynch();
        }

    }
}
