package com.wisewin.backend.service;

import com.wisewin.backend.dao.DataTableDAO;
import com.wisewin.backend.entity.bo.DateTime;
import com.wisewin.backend.entity.bo.DictionaryBO;
import com.wisewin.backend.entity.bo.MoneyBO;
import com.wisewin.backend.entity.bo.StatisticsBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;


@Service("DataTableService")
@Transactional
public class DataTableService {
    @Resource
    private DataTableDAO dataTableDAO;

    public  List<StatisticsBO>  getData(Integer roleId, Date date){
        List<StatisticsBO>  list=new ArrayList<StatisticsBO>();
        DateTime  dateTime=new DateTime(date);

        List<DictionaryBO> dictionaryBOS = dataTableDAO.queryLanguage(roleId);

        if(dictionaryBOS!=null && dictionaryBOS.size()>0){
             for(int i=0;i<dictionaryBOS.size();i++) {
                 StatisticsBO statisticsBO = new StatisticsBO();
                 DictionaryBO dictionaryBO = dictionaryBOS.get(i);
                 statisticsBO.setName(dictionaryBO.getName()); //语言名称

                 Map<String, Object> paramMap = new HashMap<String, Object>();
                 paramMap.put("id", dictionaryBO.getId());
                 //本月
                 paramMap.put("startTime", dateTime.getThisMonth().getStartTime());
                 paramMap.put("endTime", dateTime.getThisMonth().getEndTime());
                 MoneyBO thisMonth = dataTableDAO.queryData(paramMap);
                 statisticsBO.setThisMonth(thisMonth.getMoney());

                 //thisYear 本年
                 paramMap.put("startTime", dateTime.getThisYear().getStartTime());
                 paramMap.put("endTime", dateTime.getThisYear().getEndTime());
                 MoneyBO  thisYear = dataTableDAO.queryData(paramMap);
                 statisticsBO.setThisYear(thisYear.getMoney());

                 //上月同期 lastMonthSyn
                 paramMap.put("startTime", dateTime.getLastMonthSyn().getStartTime());
                 paramMap.put("endTime", dateTime.getLastMonthSyn().getEndTime());
                 MoneyBO  lastMonthSyn = dataTableDAO.queryData(paramMap);
                 statisticsBO.setLastMonthSyn(lastMonthSyn.getMoney());

                 //lastMonth 上月
                 paramMap.put("startTime", dateTime.getLastMonth().getStartTime());
                 paramMap.put("endTime", dateTime.getLastMonth().getEndTime());
                 MoneyBO  lastMonth = dataTableDAO.queryData(paramMap);
                 statisticsBO.setLastMonth(lastMonth.getMoney());

                 //lastYearThis 上年同期
                 paramMap.put("startTime", dateTime.getLastYearSyn().getStartTime());
                 paramMap.put("endTime", dateTime.getLastYearSyn().getEndTime());
                 MoneyBO  lastYearSyn = dataTableDAO.queryData(paramMap);
                 statisticsBO.setLastYearThis(lastYearSyn.getMoney());

                 //lastYearThisSum 上年同期累计
                 paramMap.put("startTime", dateTime.getLastYearSynTotal().getStartTime());
                 paramMap.put("endTime", dateTime.getLastYearSynTotal().getEndTime());
                 MoneyBO  lastYearSynTotal = dataTableDAO.queryData(paramMap);
                 statisticsBO.setLastYearThisSum(lastYearSynTotal.getMoney());

                 //lastYear 去年
                 paramMap.put("startTime", dateTime.getLastYear().getStartTime());
                 paramMap.put("endTime", dateTime.getLastYear().getEndTime());
                 MoneyBO  lastYear = dataTableDAO.queryData(paramMap);
                 statisticsBO.setLastYear(lastYear.getMoney());

                 //同比上月同期 本月销售额/上月同期
                 statisticsBO.setLastMonthSynBasis(this.calculate(statisticsBO.getThisMonth(),statisticsBO.getLastMonthSyn()));
                 //环比上月 ：本月销售额/上月
                 statisticsBO.setLinkLastMonth(this.calculate(statisticsBO.getThisMonth(),statisticsBO.getLastMonth()));
                 //同比上年同期  本月销售额/上年同期
                 statisticsBO.setLastYearThisBasis(this.calculate(statisticsBO.getThisMonth(),statisticsBO.getLastYearThis()));
                 //同比上年同期累计  本年销售额/上年同期累计
                 statisticsBO.setLastYearSumBais(this.calculate(statisticsBO.getThisYear(),statisticsBO.getLastYearThis()));
                 //环比去年 本年销售额/去年
                 statisticsBO.setLinkLastYear(this.calculate(statisticsBO.getThisYear(),statisticsBO.getLastYear()));

                 list.add(statisticsBO);
             }
        }

        return  list;
    }


    private   String  calculate(BigDecimal  num1,BigDecimal num2){
        try {
            NumberFormat nf = NumberFormat.getPercentInstance();
            nf.setMinimumFractionDigits(2);
            if (num1.compareTo(new BigDecimal(0)) == 0) {
                return "0%";
            }
            return nf.format(num2.divide(num1));
        }catch (Exception e){
            return "0%";
        }
    }


}
