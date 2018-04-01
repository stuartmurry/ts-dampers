/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.NextTestBuildingBean;
import com.thermalstrategies.eportal.controller.NextTestDateBean;
import com.thermalstrategies.eportal.model.Customer;
import com.thermalstrategies.eportal.model.User;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.sort.NextTestBuildingSort;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

/**
 *
 * @author Stuart
 */
public class NextTestDateManager {

    private HibernateTemplate tstratDamperTemplate;

    public NextTestDateManager(HibernateTemplate tstratDamperTemplate) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public List<NextTestDateBean> getNextTestDateBeanList() {
        return (List<NextTestDateBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                CustTimer ct = new CustTimer("getNextTestDateBeanList()", System.currentTimeMillis());
                StringBuffer sql = new StringBuffer();

                sql.append(" SELECT c.id, \n");
                sql.append(" max(c.customerName), \n");
                sql.append(" b.id, \n");
                sql.append(" max(b.buildingName), \n");
                sql.append(" max(year(current_date())), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date())) then 1 END), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date()) + 1) then 1 END), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date()) + 2) then 1 END), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date()) + 3) then 1 END), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date()) + 4) then 1 END), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date()) + 5) then 1 END), \n");
                sql.append(" sum(case year(d.nextTestDate) when (year(current_date()) + 6) then 1 END) \n");
                sql.append(" FROM Dampertest d \n");
                sql.append(" inner join d.building as b \n");
                sql.append(" inner join d.customer as c \n");
                sql.append(" inner join d.damperstatus as ds \n");

                Customer customer = EPortalSecurityContext.getCustomer();
                String role = EPortalSecurityContext.getRole().getRole();
                if ("customer".equalsIgnoreCase(role)) {
                    sql.append(" where ds.id !=6 and c.id=" + customer.getId()); // Don't count decommisissioned dampers
                } else if ("employee".equalsIgnoreCase(role)){
                     throw new SQLException("You don't have the proper permissions to view this content.  Either your security has changed or you are not authorized.  Please contact a customer representative or supervisor to resolve this issue.");
                } else {
                    sql.append(" where ds.id !=6");
                }

                sql.append(" group by c.id, b.id ");

                Query query = session.createQuery(sql.toString());
                List<NextTestDateBean> nextTestDateBeanList = new ArrayList<NextTestDateBean>();
                NextTestDateBean nextTestDateBean = null;
                List<NextTestBuildingBean> list = null;
                Integer custIdHolder = -1;
                for (Iterator it = query.iterate(); it.hasNext();) {
                    Object[] row = (Object[]) it.next();

                    Integer custId = CustomNumberUtils.parseInt(row[0]);
                    String customerName = "" + row[1];
                    if(!custIdHolder.equals(custId))
                    {
                        nextTestDateBean = new NextTestDateBean();
                        nextTestDateBean.setCustId(custId);
                        nextTestDateBean.setCustomerName(customerName);
                        list = new ArrayList<NextTestBuildingBean>();
                        nextTestDateBean.setNextTestBuildingBeanList(list);
                        nextTestDateBeanList.add(nextTestDateBean);
                        custIdHolder = custId;
                    } 

                    NextTestBuildingBean bean = new NextTestBuildingBean();
                    bean.setBuildingId(CustomNumberUtils.parseInt(row[2]));
                    bean.setBuildingName("" + row[3]);
                    Integer baseYear = CustomNumberUtils.parseIntNullAsZero(row[4]);
                    bean.setBaseYear(baseYear);
                    nextTestDateBean.setBaseYear(baseYear);
                    bean.setYearSum(CustomNumberUtils.parseIntNullAsZero(row[5]));
                    bean.setYearplus1Sum(CustomNumberUtils.parseIntNullAsZero(row[6]));
                    bean.setYearplus2Sum(CustomNumberUtils.parseIntNullAsZero(row[7]));
                    bean.setYearplus3Sum(CustomNumberUtils.parseIntNullAsZero(row[8]));
                    bean.setYearplus4Sum(CustomNumberUtils.parseIntNullAsZero(row[9]));
                    bean.setYearplus5Sum(CustomNumberUtils.parseIntNullAsZero(row[10]));
                    bean.setYearplus6Sum(CustomNumberUtils.parseIntNullAsZero(row[11]));
                    list.add(bean);

                }

                Collections.sort(list, new NextTestBuildingSort());

                System.out.println(ct.calculateTotalProcessTime());
                return nextTestDateBeanList;
            }
        });

    }

}
