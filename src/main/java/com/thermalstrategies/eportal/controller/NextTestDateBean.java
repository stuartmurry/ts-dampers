/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.thermalstrategies.eportal.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stuart
 */
public class NextTestDateBean {

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NextTestDateBean other = (NextTestDateBean) obj;
        if (this.custId != other.custId && (this.custId == null || !this.custId.equals(other.custId))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.custId != null ? this.custId.hashCode() : 0);
        return hash;
    }

    private Integer custId;
    private String customerName;
    private List<NextTestBuildingBean> nextTestBuildingBeanList = new ArrayList<NextTestBuildingBean>();
    private Integer overallSum;
    private Integer overallSumPlus1;
    private Integer overallSumPlus2;
    private Integer overallSumPlus3;
    private Integer overallSumPlus4;
    private Integer overallSumPlus5;
    private Integer overallSumPlus6;

    private Integer baseYear;


    public List<NextTestBuildingBean> getNextTestBuildingBeanList() {
        return nextTestBuildingBeanList;
    }

    /**
     * @param nextTestBuildingBeanList the nextTestBuildingBeanList to set
     */
    public void setNextTestBuildingBeanList(List<NextTestBuildingBean> nextTestBuildingBeanList) {
        this.nextTestBuildingBeanList = nextTestBuildingBeanList;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the custId
     */
    public Integer getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    /**
     * @return the overallSum
     */
    public Integer getOverallSum() {
        int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearSum();
        }
        return i;
    }

    /**
     * @return the overallSumPlus1
     */
    public Integer getOverallSumPlus1() {
       int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearplus1Sum();
        }
        return i;
    }

    /**
     * @return the overallSumPlus2
     */
    public Integer getOverallSumPlus2() {
        int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearplus2Sum();
        }
        return i;
    }

    /**
     * @return the overallSumPlus3
     */
    public Integer getOverallSumPlus3() {
        int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearplus3Sum();
        }
        return i;
    }

    /**
     * @return the overallSumPlus4
     */
    public Integer getOverallSumPlus4() {
        int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearplus4Sum();
        }
        return i;
    }

    /**
     * @return the overallSumPlus5
     */
    public Integer getOverallSumPlus5() {
        int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearplus5Sum();
        }
        return i;
    }

    /**
     * @return the overallSumPlus6
     */
    public Integer getOverallSumPlus6() {
        int i = 0;
        for (NextTestBuildingBean bean : getNextTestBuildingBeanList())
        {
            i = i + bean.getYearplus6Sum();
        }
        return i;
    }

    /**
     * @return the baseYear
     */
    public Integer getBaseYear() {
        return baseYear;
    }

    /**
     * @param baseYear the baseYear to set
     */
    public void setBaseYear(Integer baseYear) {
        this.baseYear = baseYear;
    }

    

}
