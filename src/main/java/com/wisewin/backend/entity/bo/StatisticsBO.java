package com.wisewin.backend.entity.bo;

import com.wisewin.backend.common.base.BaseModel;

import java.math.BigDecimal;

public class StatisticsBO extends BaseModel {

    private String name; //语言名称
    private BigDecimal thisMonth;//本月
    private BigDecimal thisYear;//本年
    private BigDecimal lastMonthSyn;//上月同期
    private String lastMonthSynBasis;//同比上月同期
    private BigDecimal lastMonth;//上月
    private String  linkLastMonth;//环比上月
    private BigDecimal lastYearThis;//上年同期
    private String  lastYearThisBasis;//同比上年同期
    private BigDecimal lastYearThisSum;//上年同期累计
    private String  lastYearSumBais;//同比上年同期累计
    private BigDecimal lastYear;//去年
    private String linkLastYear;//环比去年


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(BigDecimal thisMonth) {
        if(thisMonth==null){
            this.thisMonth = new BigDecimal("0");
            return;
        }
        this.thisMonth = thisMonth;
    }

    public BigDecimal getThisYear() {
        return thisYear;
    }

    public void setThisYear(BigDecimal thisYear) {
        if(thisYear==null){
            this.thisYear = new BigDecimal("0");
            return;
        }
        this.thisYear = thisYear;
    }

    public BigDecimal getLastMonthSyn() {
        return lastMonthSyn;
    }

    public void setLastMonthSyn(BigDecimal lastMonthSyn) {
        if(lastMonthSyn==null){
            this.lastMonthSyn = lastMonthSyn;
            return;
        }
        this.lastMonthSyn = lastMonthSyn;
    }

    public String getLastMonthSynBasis() {
        return lastMonthSynBasis;
    }

    public void setLastMonthSynBasis(String lastMonthSynBasis) {

        this.lastMonthSynBasis = lastMonthSynBasis;
    }

    public BigDecimal getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(BigDecimal lastMonth) {
        if(lastMonth==null){
            this.lastMonth = new BigDecimal("0");
            return;
        }
        this.lastMonth = lastMonth;
    }

    public String getLinkLastMonth() {
        return linkLastMonth;
    }

    public void setLinkLastMonth(String linkLastMonth) {
        this.linkLastMonth = linkLastMonth;
    }

    public BigDecimal getLastYearThis() {
        return lastYearThis;
    }

    public void setLastYearThis(BigDecimal lastYearThis) {


        
        this.lastYearThis = lastYearThis;
    }

    public String getLastYearThisBasis() {
        return lastYearThisBasis;
    }

    public void setLastYearThisBasis(String lastYearThisBasis) {
        this.lastYearThisBasis = lastYearThisBasis;
    }

    public BigDecimal getLastYearThisSum() {
        return lastYearThisSum;
    }

    public void setLastYearThisSum(BigDecimal lastYearThisSum) {
        this.lastYearThisSum = lastYearThisSum;
    }

    public String getLastYearSumBais() {
        return lastYearSumBais;
    }

    public void setLastYearSumBais(String lastYearSumBais) {
        this.lastYearSumBais = lastYearSumBais;
    }

    public BigDecimal getLastYear() {
        return lastYear;
    }

    public void setLastYear(BigDecimal lastYear) {
        this.lastYear = lastYear;
    }

    public String getLinkLastYear() {
        return linkLastYear;
    }

    public void setLinkLastYear(String linkLastYear) {
        this.linkLastYear = linkLastYear;
    }
}
