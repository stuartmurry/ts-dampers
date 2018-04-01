/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thermalstrategies.eportal.manager;

import com.thermalstrategies.eportal.controller.RepairHistoryBean;
import com.thermalstrategies.eportal.model.Dampertest;
import com.thermalstrategies.eportal.model.Repairhistory;
import com.thermalstrategies.eportal.security.EPortalSecurityContext;
import com.thermalstrategies.eportal.utils.CustTimer;
import com.thermalstrategies.eportal.utils.CustomDateUtils;
import com.thermalstrategies.eportal.utils.CustomNumberUtils;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
public class RepairHistoryManager {

    private HibernateTemplate tstratDamperTemplate;

    public RepairHistoryManager(HibernateTemplate tstratDamperTemplate) throws DataAccessException {
        this.tstratDamperTemplate = tstratDamperTemplate;
    }

    public void deleteRepairHistory(Integer id) {
        Repairhistory h = (Repairhistory) tstratDamperTemplate.load(Repairhistory.class, id);
        tstratDamperTemplate.delete(h);
    }

    public void saveRepairHistory(RepairHistoryBean bean) {
        System.out.println(bean.toString());
        Repairhistory h;
        Integer id = bean.getId();
        if (id != null && id > 0) {
            System.out.println("repairHistoryId is not null");
            h = (Repairhistory) tstratDamperTemplate.load(Repairhistory.class, id);
        } else {
            System.out.println("repairHistoryId is null");
            h = new Repairhistory();
            Dampertest dampertest = (Dampertest) tstratDamperTemplate.load(Dampertest.class, bean.getDamperId());
            h.setDampertest(dampertest);
        }

        h.setDescription(bean.getDescription());

        Date date = CustomDateUtils.convertJasonMoonsStringToDate(bean.getDate());
        h.setRepairDate(date);


        tstratDamperTemplate.saveOrUpdate(h);


    }

    public RepairHistoryBean getRepairHistory(Integer id) {
        try {
            Repairhistory h = (Repairhistory) tstratDamperTemplate.load(Repairhistory.class, id);
            if (h != null) {
                RepairHistoryBean repairHistoryBean = new RepairHistoryBean();
                //<fmt:formatDate pattern="dd-MMM-yyyy" type="date" value="${command.date}" />
                repairHistoryBean.setDate(CustomDateUtils.convertJasonMoonsDateToString(h.getRepairDate()));
                repairHistoryBean.setDescription(h.getDescription());
                repairHistoryBean.setId(h.getId());

                System.out.println("Getting repair history -> " + repairHistoryBean.toString());
                return repairHistoryBean;
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            return null;
        }


    }

    public List<RepairHistoryBean> getRepairHistoryList(final Integer damperId) throws DataAccessException {
        return getRepairHistoryList(null, damperId, null, null);
    }

    /**
     * Needs changing...
     * @param custId
     * @param damperId
     * @param buildingId
     * @return
     * @throws DataAccessException
     */
    public List<RepairHistoryBean> getRepairHistoryList(Integer custId, Integer damperId, Integer buildingId) throws DataAccessException {
            return getRepairHistoryList(custId, damperId, buildingId, null);
//        return (List<RepairHistoryBean>) tstratDamperTemplate.execute(new HibernateCallback() {
//
//            public Object doInHibernate(Session session) throws HibernateException, SQLException {
//
//                CustTimer ct = new CustTimer("getRepairHistoryList()", System.currentTimeMillis());
//
//                List<RepairHistoryBean> list = new ArrayList<RepairHistoryBean>();
//                StringBuffer sql = new StringBuffer();
//                sql.append("select r.id, r.repairDate, r.description, dt.id, dt.aliasId, ds.substatus, dt.comments, b.buildingName, dt.dateTestedTs  \n ");
//                sql.append(" from Repairhistory r \n");
//                sql.append(" inner join r.dampertest as dt\n");
//                sql.append(" left join dt.damperstatus as ds\n");
//                sql.append(" inner join dt.building as b\n");
////                sql.append(" inner join dt.customer as c\n");
//
//                List<String> whereClause = new ArrayList<String>();
//
//                if (damperId != null && damperId > 0) {
//                    whereClause.add("dt.id=" + damperId);
//                }
//                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
//                    whereClause.add("dt.customer.id=" + EPortalSecurityContext.getCustomer().getId());
//                } else {
//                    if (custId != null && custId > 0) {
//                        whereClause.add("dt.customer.id=" + custId);
//                    }
//                }
//
//                if (buildingId != null && buildingId > 0) {
//                    whereClause.add("dt.building.id=" + buildingId);
//                }
//
//                if (whereClause.size() > 0) {
//                    int count = 0;
//                    for (String c : whereClause) {
//                        if (count++ == 0) {
//                            sql.append(" where " + c);
//                        } else {
//                            sql.append(" and " + c);
//                        }
//                    }
//                }
//
//                sql.append(" order by r.repairDate,  b.buildingName");
//
////                System.out.println(sql.toString());
//
//                Query query = session.createQuery(sql.toString());
//                for (Iterator it = query.iterate(); it.hasNext();) {
//                    Object[] row = (Object[]) it.next();
//                    RepairHistoryBean rs = new RepairHistoryBean();
//                    rs.setId(Integer.parseInt("" + row[0]));
////                        rs.setDate(CustomDateUtils.convertJasonMoonsDateToString((Date) row[1]));
//                    if (row[1] != null) {
//                        rs.setDate(CustomDateUtils.repairHistoryDateformatter((Date) row[1]));
//                    }
//
//                    rs.setDescription("" + row[2]);
//                    rs.setDamperId(Integer.parseInt("" + row[3]));
//                    rs.setAliasId((String) row[4]);
//
//
//                    String status = (String) row[5];
//                    String nStatus = "<Undefined>";
//
//                    if (StringUtils.contains(status, "FAIL")) {
//                        nStatus = "<span style=\"color:red\">" + status + "</span>";
//                    } else if (StringUtils.contains(status, "INACCESS")) {
//                        nStatus = "<span style=\"color:blue\">" + status + "</span>";
//                    } else if (StringUtils.contains(status, "PASS")) {
//                        nStatus = "<span style=\"color:green\">" + status + "</span>";
//                    }
//                    rs.setStatus(nStatus);
//
////                        rs.setComments(DamperUtils.parseComments((byte[]) row[6]));
//                    rs.setComments((String) row[6]);
//                    rs.setBuilding("" + row[7]);
//                    rs.setDateTestedTs(CustomDateUtils.repairHistoryDateformatter((Date) row[8]));
//
//                    list.add(rs);
//                }
//
//                System.out.println("List Size: " + list.size());
//
//
//                System.out.println(ct.calculateTotalProcessTime());
//
//                return list;
//            }
//        });
    }

    public List<RepairHistoryBean> getRepairHistoryList(final Integer custId, final Integer damperId, final Integer buildingId, final Integer floorId) throws DataAccessException {

        return (List<RepairHistoryBean>) tstratDamperTemplate.execute(new HibernateCallback() {

            public Object doInHibernate(Session session) throws HibernateException, SQLException {

                CustTimer ct = new CustTimer("getRepairHistoryList()", System.currentTimeMillis());

                List<RepairHistoryBean> list = new ArrayList<RepairHistoryBean>();
                StringBuffer sql = new StringBuffer();
                sql.append("select r.id, r.repairDate, r.description, dt.id, dt.aliasId, ds.substatus, dt.comments, b.buildingName, dt.dateTestedTs, c.id, b.id, bf.id \n ");
                sql.append(" from Repairhistory r \n");
                sql.append(" inner join r.dampertest as dt\n");
                sql.append(" left join dt.damperstatus as ds\n");
                sql.append(" inner join dt.building as b\n");
                sql.append(" inner join dt.buildingfloor as bf\n");
                sql.append(" inner join dt.customer as c\n");
//                sql.append(" inner join dt.customer as c\n");

                List<String> whereClause = new ArrayList<String>();

                if (damperId != null && damperId > 0) {
                    whereClause.add("dt.id=" + damperId);
                }
                if ("customer".equalsIgnoreCase(EPortalSecurityContext.getRole().getRole())) {
                    whereClause.add("dt.customer.id=" + EPortalSecurityContext.getCustomer().getId());
                } else {
                    if (custId != null && custId > 0) {
                        whereClause.add("dt.customer.id=" + custId);
                    }
                }

                if (buildingId != null && buildingId > 0) {
                    whereClause.add("b.id=" + buildingId);
                }

                if (floorId != null && floorId > 0) {
                    whereClause.add("bf.id=" + floorId);
                }

                if (whereClause.size() > 0) {
                    int count = 0;
                    for (String c : whereClause) {
                        if (count++ == 0) {
                            sql.append(" where " + c);
                        } else {
                            sql.append(" and " + c);
                        }
                    }
                }

                sql.append(" order by r.repairDate desc,  b.buildingName");

                System.out.println(sql.toString());

                try {
                    Query query = session.createQuery(sql.toString());
                    for (Iterator it = query.iterate(); it.hasNext();) {
                        Object[] row = (Object[]) it.next();
                        RepairHistoryBean rs = new RepairHistoryBean();
                        rs.setId(Integer.parseInt("" + row[0]));
//                        rs.setDate(CustomDateUtils.convertJasonMoonsDateToString((Date) row[1]));
                        if (row[1] != null) {
                            rs.setDate(CustomDateUtils.repairHistoryDateformatter((Date) row[1]));
                        }

                        rs.setDescription("" + row[2]);
                        rs.setDamperId(Integer.parseInt("" + row[3]));
                        rs.setAliasId((String) row[4]);
                        String status = (String) row[5];
                        String nStatus = "<Undefined>";

                        if (StringUtils.contains(status, "FAIL")) {
                            nStatus = "<span style=\"color:red\">" + status + "</span>";
                        } else if (StringUtils.contains(status, "INACCESS")) {
                            nStatus = "<span style=\"color:blue\">" + status + "</span>";
                        } else if (StringUtils.contains(status, "PASS")) {
                            nStatus = "<span style=\"color:green\">" + status + "</span>";
                        }
                        rs.setStatus(nStatus);
//                        rs.setComments(DamperUtils.parseComments((byte[]) row[6]));
                        rs.setComments((String) row[6]);
                        rs.setBuilding("" + row[7]);
                        rs.setDateTestedTs(CustomDateUtils.repairHistoryDateformatter((Date) row[8]));

                        rs.setCustId(CustomNumberUtils.parseIntNullAsZero("" + row[8]));
                        rs.setBuildingId(CustomNumberUtils.parseIntNullAsZero("" + row[9]));
                        rs.setFloorId(CustomNumberUtils.parseIntNullAsZero("" + row[10]));
                        list.add(rs);
                    }
                } catch (Exception e) {
                    // We really don't need to see why this happens
                }
                System.out.println(ct.calculateTotalProcessTime());

                return list;
            }
        });
    }
}
