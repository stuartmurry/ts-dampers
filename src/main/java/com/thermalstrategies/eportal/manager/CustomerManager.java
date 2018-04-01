/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.CustomerBuildingFloorBean;
import com.thermalstrategies.eportal.controller.CustomerBuildingFloorBean.Building;
import com.thermalstrategies.eportal.controller.CustomerBuildingFloorBean.Buildingfloor;
import com.thermalstrategies.eportal.controller.DamperDBView;
import com.thermalstrategies.eportal.controller.UserCommandBean;
import com.thermalstrategies.eportal.model.Customer;

import com.thermalstrategies.eportal.model.Dampertest;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.sort.CustomerSort;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import com.thermalstrategies.eportal.utils.CustomStringUtils;
import com.thermalstrategies.eportal.utils.SessionUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author smurry
 */
public class CustomerManager {

    private HibernateTemplate tstratDamperTemplate;

    public CustomerManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public void deleteCustomer(Dampertest dampertest) throws DataAccessException {
        tstratDamperTemplate.delete(dampertest);
//        reSynch();
    }

    public Customer getCustomer(Integer id) throws DataAccessException {
        // Make sure customers only see their stuff and not other clients
        if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
            return EPortalSecurityContext.getCustomer();
        } else {
            return (Customer) tstratDamperTemplate.load(Customer.class, id);
        }

    }

    public void saveCustomer(Customer customer) throws DataAccessException {
        tstratDamperTemplate.saveOrUpdate(customer);
//        reSynch();
    }

    public void saveCustomer(UserCommandBean customer) throws DataAccessException {
        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                return null;
            }
        });

    }

    public void deleteCustomer(final Customer customer) throws DataAccessException {

        tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                Customer loadedCustomer = (Customer)session.load(Customer.class, customer.getId());
                // Make sure customers only see their stuff and not other clients
                if ("Customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    throw new SQLException("You don't have permissions to save this damper.  Contact your administrator.");
                }

                String customerName = customer.getCustomerName();
//                    Set<Dampertest> damperSet = loadedCustomer.getDampertests();
                Set<Dampertest> damperSet = customer.getDampertests();
                String msg = "Damper Test Associations: <br><br>";
                int count = 0;
                for (Dampertest dt : damperSet) {
                    ++count;
                    msg = msg + dt.getAliasId() + "<br>";
                }
                if (count > 0) {
                    // This customer has project associations
                    throw new SQLException("Unable to delete customer: " + customerName + " - There are Damper Test(s) referencing this floor.  "
                            + "<br>Remove the dampertests from the floor then delete the floor.<br><br>" + msg);
                } else {
                    session.delete(customer);
                }
                return null;
            }
        });
        tstratDamperTemplate.delete(customer);

//        reSynch();

    }

    public List<Customer> getCustomerListWithNoSecurity() throws DataAccessException {

        return (List<Customer>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getCustomerList()", System.currentTimeMillis());
                List<Customer> customerList = new ArrayList<Customer>();

                customerList = session.createCriteria(Customer.class).list();
                Collections.sort(customerList, new CustomerSort());

                System.out.println(ct.calculateTotalProcessTime());
                return customerList;
            }
        });

    }

    public List<Customer> getCustomerList() throws DataAccessException {

        return (List<Customer>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getCustomerList()", System.currentTimeMillis());
                List<Customer> customerList = new ArrayList<Customer>();
//                System.out.println("Role: " + role);
                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
//                    System.out.println("Equals");
                    customerList.add(EPortalSecurityContext.getCustomer());
                } else {
//                    System.out.println("Not Equals");
                    customerList = session.createCriteria(Customer.class).list();
                    Collections.sort(customerList, new CustomerSort());
                }
                System.out.println(ct.calculateTotalProcessTime());
                return customerList;
            }
        });

    }

    /**
     *
     * @return
     * [CustId] [Customer] [SiteId] [Site] [BuildingId] [Building] [FloorId] [Floor]
     */
//    public List<DamperDBView> getCustomerSiteBuildingFloors() {
//        return (List<DamperDBView>) tstratDamperTemplate.execute(new HibernateCallback() {
//
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//                CustTimer ct = new CustTimer("getCustomerSiteBuildingFloors()", System.currentTimeMillis());
//                StringBuffer sql = new StringBuffer();
//
//                sql.append(" select c.id, c.customerName, s.id, s.name, b.id, b.buildingName, bf.id, bf.floorName ");
//                sql.append(" from Buildingfloor bf ");
//                sql.append(" inner join bf.building as b ");
//                sql.append(" inner join b.customer as c");
//                sql.append(" left outer join b.site as s ");
//                sql.append(" order by c.customerName, s.name, b.buildingName, bf.sequenceNum ");
//
//                SessionUtils.addRestriction(sql);
//
//
//
//
//                System.out.println(sql.toString());
//                Query query = session.createQuery(sql.toString());
//
//                List<DamperDBView> damperViewList = new ArrayList<DamperDBView>();
//                for (Iterator it = query.iterate(); it.hasNext();) {
//                    Object[] row = (Object[]) it.next();
//
//                    DamperDBView dbView = new DamperDBView();
//                    dbView.setCustomerId(CustomNumberUtils.parseInt(row[0]));
//                    dbView.setCustomerName("" + row[1]);
//                    dbView.setSiteId(CustomNumberUtils.parseInt(row[2]));
//                    dbView.setSiteName("" + row[3]);
//                    dbView.setBuildingId(CustomNumberUtils.parseInt(row[4]));
//                    dbView.setBuildingName("" + row[5]);
//                    dbView.setFloorId(CustomNumberUtils.parseInt(row[6]));
//                    dbView.setFloorName("" + row[7]);
//
//                    damperViewList.add(dbView);
//
//                }
//
//                return damperViewList;
//
//            }
//        });
//    }
    public List<CustomerBuildingFloorBean> getCustomerBuildingFloorList() {

        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList =
                (List<CustomerBuildingFloorBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getCustomerBuildingFloorList()", System.currentTimeMillis());

                StringBuffer sql = new StringBuffer();

                sql.append(" select c.id, max(c.customerName), b.id, max(b.buildingName), s.id, max(s.name),  bf.id, max(bf.floorName), max(bf.sequenceNum) ");
                sql.append("     from Buildingfloor bf ");
                sql.append("     right outer join bf.building as b ");
                sql.append("     right outer join b.customer as c ");
                sql.append("     left outer join b.site as s");

                String role = EPortalSecurityContext.getRole().getRole();
                if ("Customer".equalsIgnoreCase(role)) {
                    sql.append(" where c.id=" + EPortalSecurityContext.getCustomer().getId());
                } else if ("Employee".equalsIgnoreCase(role)) {
                    String customerIds = EPortalSecurityContext.getUser().getCustomers();
                    String sqlInString = CustomStringUtils.commaDelimetedStringToSqlInString(customerIds);
                    sql.append(" where c.id in (" + sqlInString + ")");
                }

                sql.append("     group by c.id, b.id, bf.id ");
                sql.append("     order by max(c.customerName), c.id,  max(b.buildingName), b.id,  max(bf.sequenceNum) ");

                System.out.println(sql.toString());

                List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = new ArrayList<CustomerBuildingFloorBean>();
                Query query = session.createQuery(sql.toString());

                List<Building> buildingList = new ArrayList<Building>();
                List<Buildingfloor> buildingFloorList = new ArrayList<Buildingfloor>();
                CustomerBuildingFloorBean cbfb = null;
                Building building = null;
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();

                    CustomerBuildingFloorBean newCustomerBuildingFloorBean = new CustomerBuildingFloorBean();
                    newCustomerBuildingFloorBean.setId(CustomNumberUtils.parseInt(row[0])); //customer.id
                    newCustomerBuildingFloorBean.setCustomerName("" + row[1]); // customer.customerName

                    if (cbfb != null && cbfb.equals(newCustomerBuildingFloorBean)) {
                        Building newBuilding = cbfb.new Building();
                        newBuilding.setId(CustomNumberUtils.parseInt(row[2]));
                        newBuilding.setBuildingName("" + row[3]);
                        newBuilding.setSiteId(CustomNumberUtils.parseInt(row[4]));
                        newBuilding.setSiteName("" + row[5]);

                        Buildingfloor newBuildingFloor = cbfb.new Buildingfloor();
                        newBuildingFloor.setId(CustomNumberUtils.parseInt(row[6]));
                        newBuildingFloor.setFloorName("" + row[7]);
                        newBuildingFloor.setSequenceNum(CustomNumberUtils.parseInt(row[8]));

                        if (building != null && newBuilding.equals(building)) {
//                            System.out.println("Adding a floor: " + newBuildingFloor.getFloorName());
                            // Existing building...
                            buildingFloorList.add(newBuildingFloor);

                        } else {
                            // New building...
                            building = newBuilding;
//                            System.out.println("Adding a buiding: " + building.getBuildingName() + " : floor : " + newBuildingFloor.getFloorName());
                            buildingFloorList = new ArrayList<Buildingfloor>();
                            buildingFloorList.add(newBuildingFloor);
                            building.setBuildingFloorList(buildingFloorList);
                            buildingList.add(building);
                        }
                    } else {
                        // New Customer
                        cbfb = newCustomerBuildingFloorBean;
                        Integer buildingId = CustomNumberUtils.parseInt(row[2]);
                        if (buildingId != null) {
                            Building newBuilding = cbfb.new Building();
                            newBuilding.setId(buildingId);
                            newBuilding.setBuildingName("" + row[3]);
                            newBuilding.setSiteId(CustomNumberUtils.parseInt(row[4]));
                            newBuilding.setSiteName("" + row[5]);

                            building = newBuilding;

                            Integer floorId = CustomNumberUtils.parseInt(row[6]);

                            if (floorId != null) {
                                Buildingfloor newBuildingFloor = cbfb.new Buildingfloor();
                                newBuildingFloor.setId(floorId);
                                newBuildingFloor.setFloorName("" + row[7]);
                                newBuildingFloor.setSequenceNum(CustomNumberUtils.parseInt(row[8]));

                                buildingFloorList = new ArrayList<Buildingfloor>();
                                buildingFloorList.add(newBuildingFloor);
                            } else {
                                buildingFloorList = new ArrayList<Buildingfloor>();
                            }
                            buildingList = new ArrayList<Building>();
                            buildingList.add(building);
                        } else {
                            buildingList = new ArrayList<Building>();
                        }

                        try {
                            building.setBuildingFloorList(buildingFloorList);
                        } catch (Exception e) {
                        }
                        try {
                            cbfb.setBuildingList(buildingList);
                        } catch (Exception e) {
                        }
                        try {
                            if (!"ALL".equals(cbfb.getCustomerName())) {
                                customerBuildingFloorBeanList.add(cbfb);
                            }

                        } catch (Exception e) {
                        }
//                        System.out.println("Adding a customer: " + cbfb.getCustomerName() + ", building: " + building.getBuildingName() + " and floor: " + newBuildingFloor.getFloorName());
                    }

                }

                System.out.println(ct.calculateTotalProcessTime());
                return customerBuildingFloorBeanList;
            }
        });




        return customerBuildingFloorBeanList;
    }

    public List<CustomerBuildingFloorBean> getCustomerBuildingFloorList(final Integer customerId) {

        List<CustomerBuildingFloorBean> customerBuildingFloorBeanList =
                (List<CustomerBuildingFloorBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getCustomerBuildingFloorList()", System.currentTimeMillis());

                StringBuffer sql = new StringBuffer();

                sql.append(" select c.id, max(c.customerName), b.id, max(b.buildingName), s.id, max(s.name),  bf.id, max(bf.floorName), max(bf.sequenceNum) ");
                sql.append("     from Buildingfloor bf ");
                sql.append("     right outer join bf.building as b ");
                sql.append("     right outer join b.customer as c ");
                sql.append("     left outer join b.site as s");

//                SessionUtils.addRestriction(sql);
                String role = EPortalSecurityContext.getRole().getRole();
                if ("Customer".equalsIgnoreCase(role)) {
                    sql.append(" where c.id=" + EPortalSecurityContext.getCustomer().getId());
                } else if ("Employee".equalsIgnoreCase(role)) {
                    String customerIds = EPortalSecurityContext.getUser().getCustomers();
                    String sqlInString = CustomStringUtils.commaDelimetedStringToSqlInString(customerIds);

                    if (!StringUtils.contains(sqlInString, customerIds)) {
                        throw new SQLException("You are not not authorized to view this customer's information.  You're security may have changed.  Please contact your supervisor.");
                    }

                }


                sql.append("     group by c.id, b.id, bf.id ");
                sql.append("     order by max(c.customerName), c.id,  max(b.buildingName), b.id,  max(bf.sequenceNum) ");

                System.out.println(sql.toString());

                List<CustomerBuildingFloorBean> customerBuildingFloorBeanList = new ArrayList<CustomerBuildingFloorBean>();
                Query query = session.createQuery(sql.toString());

                List<Building> buildingList = new ArrayList<Building>();
                List<Buildingfloor> buildingFloorList = new ArrayList<Buildingfloor>();
                CustomerBuildingFloorBean cbfb = null;
                Building building = null;
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
//                    for (Object o : row)
//                    {
//                        System.out.print("\"" + o + "\", ");
//                    }
//                    System.out.println("");

                    CustomerBuildingFloorBean newCustomerBuildingFloorBean = new CustomerBuildingFloorBean();
                    newCustomerBuildingFloorBean.setId(CustomNumberUtils.parseInt(row[0])); //customer.id
                    newCustomerBuildingFloorBean.setCustomerName("" + row[1]); // customer.customerName

                    if (cbfb != null && cbfb.equals(newCustomerBuildingFloorBean)) {
                        Building newBuilding = cbfb.new Building();
                        newBuilding.setId(CustomNumberUtils.parseInt(row[2]));
                        newBuilding.setBuildingName("" + row[3]);
                        newBuilding.setSiteId(CustomNumberUtils.parseInt(row[4]));
                        newBuilding.setSiteName("" + row[5]);

                        Buildingfloor newBuildingFloor = cbfb.new Buildingfloor();
                        newBuildingFloor.setId(CustomNumberUtils.parseInt(row[6]));
                        newBuildingFloor.setFloorName("" + row[7]);
                        newBuildingFloor.setSequenceNum(CustomNumberUtils.parseInt(row[8]));

                        if (building != null && newBuilding.equals(building)) {
//                            System.out.println("Adding a floor: " + newBuildingFloor.getFloorName());
                            // Existing building...
                            buildingFloorList.add(newBuildingFloor);

                        } else {
                            // New building...
                            building = newBuilding;
//                            System.out.println("Adding a buiding: " + building.getBuildingName() + " : floor : " + newBuildingFloor.getFloorName());
                            buildingFloorList = new ArrayList<Buildingfloor>();
                            buildingFloorList.add(newBuildingFloor);
                            building.setBuildingFloorList(buildingFloorList);
                            buildingList.add(building);
                        }
                    } else {
                        // New Customer
                        cbfb = newCustomerBuildingFloorBean;
                        Integer buildingId = CustomNumberUtils.parseInt(row[2]);
                        if (buildingId != null) {
                            Building newBuilding = cbfb.new Building();
                            newBuilding.setId(buildingId);
                            newBuilding.setBuildingName("" + row[3]);
                            newBuilding.setSiteId(CustomNumberUtils.parseInt(row[4]));
                            newBuilding.setSiteName("" + row[5]);

                            building = newBuilding;

                            Integer floorId = CustomNumberUtils.parseInt(row[6]);

                            if (floorId != null) {
                                Buildingfloor newBuildingFloor = cbfb.new Buildingfloor();
                                newBuildingFloor.setId(floorId);
                                newBuildingFloor.setFloorName("" + row[7]);
                                newBuildingFloor.setSequenceNum(CustomNumberUtils.parseInt(row[8]));

                                buildingFloorList = new ArrayList<Buildingfloor>();
                                buildingFloorList.add(newBuildingFloor);
                            } else {
                                buildingFloorList = new ArrayList<Buildingfloor>();
                            }
                            buildingList = new ArrayList<Building>();
                            buildingList.add(building);
                        } else {
                            buildingList = new ArrayList<Building>();
                        }

                        building.setBuildingFloorList(buildingFloorList);
                        cbfb.setBuildingList(buildingList);
                        customerBuildingFloorBeanList.add(cbfb);
//                        System.out.println("Adding a customer: " + cbfb.getCustomerName() + ", building: " + building.getBuildingName() + " and floor: " + newBuildingFloor.getFloorName());
                    }

                }

                System.out.println(ct.calculateTotalProcessTime());
                return customerBuildingFloorBeanList;
            }
        });

        return customerBuildingFloorBeanList;
    }
}