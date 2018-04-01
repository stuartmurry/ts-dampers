/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.BuildingUnderTestBean;
import com.thermalstrategies.eportal.FloorUnderTestBean;
import com.thermalstrategies.eportal.controller.PassFailBean;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import com.thermalstrategies.eportal.utils.CustomStringUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public class StatisticsManager {

    private HibernateTemplate tstratDamperTemplate;

    public StatisticsManager(HibernateTemplate tstratDamperTemplate) {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public List<BuildingUnderTestBean> getBuildingUnderTestList() {
        return (List<BuildingUnderTestBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getBuildingUnderTestList()", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();

                sql.append(" select \n");
                sql.append(" count(*), \n");
                sql.append(" sum(case when dt.damperstatus.id is null then 1 else 0 end), \n");
                sql.append(" max(b.id), \n");
                sql.append(" max(b.buildingName), \n");
                sql.append(" max(c.id), \n");
                sql.append(" max(c.customerName), \n");
                sql.append(" max(tc.startdate), \n");
                sql.append(" max(tc.id) \n");

                sql.append(" from Dampertest dt \n");
                sql.append(" inner join dt.building as b  \n");
                sql.append(" inner join dt.customer as c  \n");
                sql.append(" , Testcycle tc \n");
                sql.append(" inner join tc.customer as ct  \n");
                sql.append(" inner join tc.building as cb  \n");
                sql.append(" where ct.id=c.id and cb.id=b.id and tc.complete = 0 \n");

                User user = EPortalSecurityContext.getUser();
                Customer customer = EPortalSecurityContext.getCustomer();
                String role = EPortalSecurityContext.getRole().getRole();
                if ("customer".equalsIgnoreCase(role)) {
                    sql.append(" and c.id=" + customer.getId());
                } else if ("employee".equalsIgnoreCase(role)) {
                    String customers = user.getCustomers();
                    sql.append(" and c.id in (" + customers + ")");
                }

                sql.append(" group by b.id ");

                System.out.println(sql.toString());

                Query query = session.createQuery(sql.toString());
                List<BuildingUnderTestBean> rsList = new ArrayList<BuildingUnderTestBean>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();

                    double overallTotal = CustomNumberUtils.parseIntNullAsZero(row[0]).doubleValue();
                    double underTestTotal = CustomNumberUtils.parseIntNullAsZero(row[1]).doubleValue();

                    // lets catch the divide by zero here
                    Double testPercent = 0d;
                    if (overallTotal != 0) {
                        testPercent = ((overallTotal - underTestTotal) / overallTotal) * 100;
                    }
                    BuildingUnderTestBean bean = new BuildingUnderTestBean();
                    bean.setPercentComplete(testPercent.intValue());

                    bean.setBuildingId(CustomNumberUtils.parseInt("" + row[2]));
                    bean.setBuilding("" + row[3]);
                    bean.setCustomerId(CustomNumberUtils.parseInt("" + row[4]));
                    bean.setCustomer("" + row[5]);
                    bean.setStartDate("" + row[6]);
                    bean.setTestCycleId(CustomNumberUtils.parseInt("" + row[7]));

                    rsList.add(bean);
                }

                System.out.println(ct.calculateTotalProcessTime());
                return rsList;
            }
        });

    }

    // Spring Security is only available during a web session.
    public void bypassUnitTest(StringBuffer sql) {
        boolean unitTest = true;

        if (!unitTest) {
            User user = EPortalSecurityContext.getUser();
            Customer customer = EPortalSecurityContext.getCustomer();
            String role = EPortalSecurityContext.getRole().getRole();
            if ("customer".equalsIgnoreCase(role)) {
                sql.append(" and c.id=" + customer.getId());
            } else if ("employee".equalsIgnoreCase(role)) {
                String customers = user.getCustomers();
                sql.append(" and c.id in (" + customers + ")");
            }
        }
    }

    public List<FloorUnderTestBean> getFloorUnderTestList(final Integer buildingId) {
        return (List<FloorUnderTestBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getBuildingUnderTestList(Integer floorId)", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();

                sql.append(" select \n");
                sql.append(" count(*), \n");
                sql.append(" sum(case when dt.damperstatus.id is null then 1 else 0 end), \n");
                sql.append(" max(b.id), \n");
                sql.append(" max(b.buildingName), \n");
                sql.append(" max(c.id), \n");
                sql.append(" max(c.customerName), \n");
                sql.append(" max(tc.startdate), \n");
                sql.append(" max(bf.id), \n");
                sql.append(" max(bf.floorName) \n");

                sql.append(" from Dampertest dt \n");
                sql.append(" inner join dt.building as b  \n");
                sql.append(" inner join dt.buildingfloor as bf  \n");
                sql.append(" inner join dt.customer as c  \n");
                sql.append(" , Testcycle tc \n");
                sql.append(" inner join tc.customer as ct  \n");
                sql.append(" inner join tc.building as cb  \n");
                sql.append(" where ct.id=c.id and cb.id=b.id and tc.complete is not null \n");
                sql.append(" and b.id=" + buildingId + "\n");

                bypassUnitTest(sql);

                sql.append(" group by bf.id ");

                System.out.println(sql.toString());

                Query query = session.createQuery(sql.toString());
                List<FloorUnderTestBean> rsList = new ArrayList<FloorUnderTestBean>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();

                    double overallTotal = CustomNumberUtils.parseIntNullAsZero(row[0]).doubleValue();
                    double underTestTotal = CustomNumberUtils.parseIntNullAsZero(row[1]).doubleValue();

                    // lets catch the divide by zero here
                    Double testPercent = 0d;
                    if (overallTotal != 0) {
                        testPercent = ((overallTotal - underTestTotal) / overallTotal) * 100;
                    }
                    FloorUnderTestBean bean = new FloorUnderTestBean();
                    bean.setPercentComplete(testPercent.intValue());

                    bean.setBuildingId(CustomNumberUtils.parseInt("" + row[2]));
                    bean.setBuilding("" + row[3]);
                    bean.setCustomerId(CustomNumberUtils.parseInt("" + row[4]));
                    bean.setCustomer("" + row[5]);
                    bean.setStartDate("" + row[6]);
                    bean.setFloorId(CustomNumberUtils.parseInt("" + row[7]));
                    bean.setFloor("" + row[8]);

                    rsList.add(bean);
                }

                System.out.println(ct.calculateTotalProcessTime());
                return rsList;
            }
        });

    }

    public List<PassFailBean> getCustomerPassFail() {

        return (List<PassFailBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getCustomerPassFail()", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();

                sql.append(" SELECT c.id, max(c.customerName), ");
                sql.append(" sum(case ds.id when 1 then 1 END), ");
                sql.append(" sum(case ds.id when 2 then 1 END), ");
                sql.append(" sum(case ds.id when 3 then 1 END), ");
                sql.append(" sum(case ds.id when 4 then 1 END), ");
                sql.append(" sum(case ds.id when 5 then 1 END), ");
                sql.append(" sum(case ds.id when 7 then 1 END) ");
                sql.append(" FROM Dampertest dt ");
                sql.append(" inner join dt.damperstatus as ds ");
                sql.append(" inner join dt.customer as c ");

                Customer customer = EPortalSecurityContext.getCustomer();
                String role = EPortalSecurityContext.getRole().getRole();
                if ("customer".equalsIgnoreCase(role)) {
                    sql.append(" where c.id=" + customer.getId());
                } else if ("employee".equalsIgnoreCase(role)) {
                    throw new SQLException("You don't have the proper permissions to view this content.  Either your security has changed or you are not authorized.  Please contact a customer representative or supervisor to resolve this issue.");
                }

                sql.append(" group by c.id");
                sql.append(" order by c.id");

                System.out.println(sql.toString());

                Query query = session.createQuery(sql.toString());
                List<PassFailBean> rsList = new ArrayList<PassFailBean>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    PassFailBean bean = new PassFailBean();
                    bean.setCustId(Integer.parseInt("" + row[0]));
                    bean.setCustomerName("" + row[1]);
                    bean.setPass(CustomNumberUtils.parseIntNullAsZero(row[2]));
                    bean.setFail(CustomNumberUtils.parseIntNullAsZero(row[3]));
                    bean.setInaccessible(CustomNumberUtils.parseIntNullAsZero(row[4]));
                    bean.setFailedRepaired(CustomNumberUtils.parseIntNullAsZero(row[5]));
                    bean.setInaccessibleRepaired(CustomNumberUtils.parseIntNullAsZero(row[6]));
                    bean.setPending(CustomNumberUtils.parseIntNullAsZero(row[7]));
                    rsList.add(bean);
                }

                System.out.println(ct.calculateTotalProcessTime());
                return rsList;
            }
        });
    }

    public List<PassFailBean> getCustomPassFailByBuildingIds(final int[] buildingIds) {

        return (List<PassFailBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getCustomerPassFail()", System.currentTimeMillis());
                StringBuilder sql = new StringBuilder();

                sql.append(" SELECT c.id, max(c.customerName), ");
                sql.append(" sum(case ds.id when 1 then 1 END), ");
                sql.append(" sum(case ds.id when 2 then 1 END), ");
                sql.append(" sum(case ds.id when 3 then 1 END), ");
                sql.append(" sum(case ds.id when 4 then 1 END), ");
                sql.append(" sum(case ds.id when 5 then 1 END), ");
                sql.append(" sum(case ds.id when 7 then 1 END), ");
                
                sql.append(" max(b.buildingName), ");
                sql.append(" max(b.id) ");

                sql.append(" FROM Dampertest dt ");
                sql.append(" inner join dt.damperstatus as ds ");
                sql.append(" inner join dt.building as b ");
                sql.append(" inner join dt.customer as c ");

                sql.append(String.format(" where b.id in (%s)", CustomStringUtils.intArrayToCommaSeparatedString(buildingIds)));

                sql.append(" group by b.id");
                sql.append(" order by b.id");

                System.out.println(sql.toString());

                Query query = session.createQuery(sql.toString());
                List<PassFailBean> rsList = new ArrayList<PassFailBean>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    PassFailBean bean = new PassFailBean();
                    bean.setCustId(Integer.parseInt("" + row[0]));
                    bean.setCustomerName("" + row[1]);
                    bean.setPass(CustomNumberUtils.parseIntNullAsZero(row[2]));
                    bean.setFail(CustomNumberUtils.parseIntNullAsZero(row[3]));
                    bean.setInaccessible(CustomNumberUtils.parseIntNullAsZero(row[4]));
                    bean.setFailedRepaired(CustomNumberUtils.parseIntNullAsZero(row[5]));
                    bean.setInaccessibleRepaired(CustomNumberUtils.parseIntNullAsZero(row[6]));
                    bean.setPending(CustomNumberUtils.parseIntNullAsZero(row[7]));
                    bean.setBuildingName("" + row[8]);
                    bean.setBuildingId(CustomNumberUtils.parseIntNullAsZero(row[9]));
                    rsList.add(bean);
                }

                System.out.println(ct.calculateTotalProcessTime());
                return rsList;
            }
        });
    }

    public List<PassFailBean> getCustomerOverallPassFail() {

        return (List<PassFailBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getCustomerOverallPassFail()", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();

//                sql.append("SELECT c.id, max(c.customerName), \nb.id, \nmax(b.buildingName), \nds.status, \ncount(dt.id) ");
//                sql.append(" FROM Dampertest dt  \n ");
//                sql.append("    inner join dt.damperstatus ds \n");
//                sql.append("    inner join dt.customer as c \n");
//                sql.append("    inner join dt.building as b \n");
//                sql.append("    group by ds.status, c.id, b.id \n");
//                sql.append("    order by c.id, b.id, ds.status ");

                sql.append(" SELECT c.id, max(c.customerName), b.id, max(b.buildingName), ");
                sql.append(" sum(case ds.id when 1 then 1 END), ");
                sql.append(" sum(case ds.id when 2 then 1 END), ");
                sql.append(" sum(case ds.id when 3 then 1 END), ");
                sql.append(" sum(case ds.id when 4 then 1 END), ");
                sql.append(" sum(case ds.id when 5 then 1 END), ");

                sql.append(" sum(case dt.occupancy when 'NEW_CONSTRUCTION' then 1 END), ");

                sql.append(" sum(case ds.id when 7 then 1 END), ");
                sql.append(" sum(case ds.id when 8 then 1 END) ");

                sql.append(" FROM Dampertest dt ");
                sql.append(" inner join dt.damperstatus as ds ");
                sql.append(" inner join dt.customer as c ");
                sql.append(" inner join dt.building as b ");

                Customer customer = EPortalSecurityContext.getCustomer();
                String role = EPortalSecurityContext.getRole().getRole();
                if ("customer".equalsIgnoreCase(role)) {
                    sql.append(" where c.id=" + customer.getId());
                } else if ("employee".equalsIgnoreCase(role)) {
                    throw new SQLException("You don't have the proper permissions to view this content.  Either your security has changed or you are not authorized.  Please contact a customer representative or supervisor to resolve this issue.");
                }

                sql.append(" group by c.id, b.id ");
                sql.append(" order by c.id, b.id ");

                System.out.println(sql.toString());

                Query query = session.createQuery(sql.toString());
                List<PassFailBean> rsList = new ArrayList<PassFailBean>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    PassFailBean bean = new PassFailBean();
                    bean.setCustId(Integer.parseInt("" + row[0]));
                    bean.setCustomerName("" + row[1]);
                    bean.setBuildingId(Integer.parseInt("" + row[2]));
                    bean.setBuildingName("" + row[3]);
                    bean.setPass(CustomNumberUtils.parseIntNullAsZero(row[4]));
                    bean.setFail(CustomNumberUtils.parseIntNullAsZero(row[5]));
                    bean.setInaccessible(CustomNumberUtils.parseIntNullAsZero(row[6]));
                    bean.setFailedRepaired(CustomNumberUtils.parseIntNullAsZero(row[7]));
                    bean.setInaccessibleRepaired(CustomNumberUtils.parseIntNullAsZero(row[8]));
                    bean.setNewConstruction(CustomNumberUtils.parseIntNullAsZero(row[9]));
                    bean.setPending(CustomNumberUtils.parseIntNullAsZero(row[10]));
                    bean.setFailInaccessible(CustomNumberUtils.parseIntNullAsZero(row[11]));
                    rsList.add(bean);
                }

                System.out.println(ct.calculateTotalProcessTime());
                return rsList;
            }
        });
    }

    public List<PassFailBean> getBuildingOverallPassFail(final Integer buildingId) {

        return (List<PassFailBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getCustomerOverallPassFail()", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();

                sql.append(" SELECT b.id, max(b.buildingName), bf.id, max(bf.floorName), \n");
                sql.append(" sum(case ds.id when 1 then 1 END), \n");
                sql.append(" sum(case ds.id when 2 then 1 END), \n");
                sql.append(" sum(case ds.id when 3 then 1 END), \n");
                sql.append(" sum(case ds.id when 4 then 1 END), \n");
                sql.append(" sum(case ds.id when 5 then 1 END), \n");

                sql.append(" sum(case dt.occupancy when 'NEW_CONSTRUCTION' then 1 END), \n");

                sql.append(" sum(case ds.id when 7 then 1 END), \n");
                sql.append(" sum(case ds.id when 8 then 1 END) \n");


                sql.append(" FROM Dampertest dt \n");
                sql.append(" inner join dt.damperstatus as ds \n");
                sql.append(" inner join dt.customer as c \n");
                sql.append(" inner join dt.building as b \n");
                sql.append(" inner join dt.buildingfloor as bf \n");

                addWhereClause(sql, buildingId);

                sql.append(" group by bf.id ");
                sql.append(" order by bf.sequenceNum ");

                System.out.println(sql.toString());

                Query query = session.createQuery(sql.toString());
                List<PassFailBean> rsList = new ArrayList<PassFailBean>();
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();
                    PassFailBean bean = new PassFailBean();
                    bean.setBuildingId(Integer.parseInt("" + row[0]));
                    bean.setBuildingName("" + row[1]);
                    bean.setFloorId(Integer.parseInt("" + row[2]));
                    bean.setFloorName("" + row[3]);
                    bean.setPass(CustomNumberUtils.parseIntNullAsZero(row[4]));
                    bean.setFail(CustomNumberUtils.parseIntNullAsZero(row[5]));
                    bean.setInaccessible(CustomNumberUtils.parseIntNullAsZero(row[6]));
                    bean.setFailedRepaired(CustomNumberUtils.parseIntNullAsZero(row[7]));
                    bean.setInaccessibleRepaired(CustomNumberUtils.parseIntNullAsZero(row[8]));
                    bean.setNewConstruction(CustomNumberUtils.parseIntNullAsZero(row[9]));
                    bean.setPending(CustomNumberUtils.parseIntNullAsZero(row[10]));
                    bean.setFailInaccessible(CustomNumberUtils.parseIntNullAsZero(row[11]));
                    rsList.add(bean);
                }

                System.out.println(ct.calculateTotalProcessTime());
                return rsList;
            }
        });
    }

    public void addWhereClause(StringBuffer sql, Integer buildingId) throws SQLException {
        boolean isUnitTest = false;
        if (isUnitTest) {
            sql.append(" where b.id=" + buildingId);
        } else {
            Customer customer = EPortalSecurityContext.getCustomer();
            String role = EPortalSecurityContext.getRole().getRole();
            if ("customer".equalsIgnoreCase(role)) {
                sql.append(" where c.id=" + customer.getId() + " and b.id=" + buildingId);
            } else if ("employee".equalsIgnoreCase(role)) {
                throw new SQLException("You don't have the proper permissions to view this content.  Either your security has changed or you are not authorized.  Please contact a customer representative or supervisor to resolve this issue.");
            } else {
                sql.append(" where b.id=" + buildingId);
            }
        }
    }
}
