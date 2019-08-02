package com.wisewin.backend.entity.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

    private Time thisMonth;//本月  本月1日至当日销售额
    private Time thisYear;//本年   本年1月1日至当日销售额
    private Time lastMonthSyn;//上月同期  上月1日至上月当日销售额
    private Time lastMonth;//上月   整月销售额
    private Time lastYearSyn; // 上年同期  上年本月整月销售额
    private Time lastYearSynTotal;//上年同期累计 上年1月1日至上年本月月底销售额
    private Time lastYear;// 去年整年


    public DateTime(){
        this(new Date());
    }

    public DateTime(Date startTime){
        if(startTime==null){
            startTime=new Date();
        }

        Calendar thisMonth=Calendar.getInstance();
        thisMonth.setTime(startTime);
        thisMonth.set(Calendar.DATE,1);
        thisMonth.set(Calendar.HOUR_OF_DAY,0);
        thisMonth.set(Calendar.MINUTE,0);
        thisMonth.set(Calendar.SECOND,0);
        this.thisMonth=new Time(thisMonth.getTime(),startTime); //本月1号 0点0分0秒


        Calendar calendar1=Calendar.getInstance();
        calendar1.setTime(startTime);
        calendar1.set(Calendar.DATE,1);
        calendar1.set(Calendar.HOUR_OF_DAY,23);
        calendar1.set(Calendar.MINUTE,59);
        calendar1.set(Calendar.SECOND,59);
        calendar1.add(Calendar.DATE,-1);//日期减一  上月末时间
        Time  lastMonth =new Time();
        lastMonth.setEndTime(calendar1.getTime());
        calendar1.set(Calendar.DATE,1);
        calendar1.set(Calendar.HOUR_OF_DAY,0);
        calendar1.set(Calendar.MINUTE,0);
        calendar1.set(Calendar.SECOND,0);
        lastMonth.setStartTime(calendar1.getTime());  //上月整月时间
        this.lastMonth=lastMonth;


        //上月同期
        Calendar calendar2=Calendar.getInstance();
        calendar2.setTime(startTime);
        calendar2.add(Calendar.MONDAY,-1);
        Time  lastMonthSyn =new Time();
        lastMonthSyn.setEndTime(calendar2.getTime());
        calendar2.set(Calendar.DATE,1);
        calendar2.set(Calendar.HOUR_OF_DAY,0);
        calendar2.set(Calendar.MINUTE,0);
        calendar2.set(Calendar.SECOND,0);
        lastMonthSyn.setStartTime(calendar2.getTime());
        this.lastMonthSyn=lastMonthSyn;


        //本年
        Calendar calendar3=Calendar.getInstance();
        calendar3.setTime(startTime);
        calendar3.set(Calendar.MONDAY,0);
        calendar3.set(Calendar.DATE,1);
        calendar3.set(Calendar.HOUR_OF_DAY,0);
        calendar3.set(Calendar.MINUTE,0);
        calendar3.set(Calendar.SECOND,0);
        this.thisYear=new Time(calendar3.getTime(),startTime);


        //上年同期
        Calendar calendar4=Calendar.getInstance();
        calendar4.setTime(startTime);
        calendar4.add(Calendar.YEAR,-1);
        calendar4.set(Calendar.DATE,1);
        calendar4.set(Calendar.HOUR_OF_DAY,0);
        calendar4.set(Calendar.MINUTE,0);
        calendar4.set(Calendar.SECOND,0);  //去年本月初
        Time lastYearSyn = new Time();
        lastYearSyn.setStartTime(calendar4.getTime());
        calendar4.add(Calendar.MONDAY,1);//去年下月1号
        calendar4.add(Calendar.DATE,-1);//去年本月最后一天
        calendar4.set(Calendar.HOUR_OF_DAY,23);
        calendar4.set(Calendar.MINUTE,59);
        calendar4.set(Calendar.SECOND,59);
        lastYearSyn.setEndTime(calendar4.getTime());
        this.lastYearSyn=lastYearSyn;


        //上年同期累计 上年1月1日至上年本月月底销售额
        Calendar calendar5=Calendar.getInstance();
        calendar5.setTime(startTime);
        calendar5.add(Calendar.YEAR,-1);
        calendar5.set(Calendar.DATE,1);//去年本月最后一天
        calendar5.add(Calendar.MONDAY,1);//去年下月1号
        calendar5.add(Calendar.DATE,-1);//去年本月最后一天
        calendar5.set(Calendar.HOUR_OF_DAY,23);
        calendar5.set(Calendar.MINUTE,59);
        calendar5.set(Calendar.SECOND,59);
        Time lastYearSynTotal = new Time();
        lastYearSynTotal.setEndTime(calendar5.getTime());
        calendar5.set(Calendar.MONDAY,0);
        calendar5.set(Calendar.DATE,1);
        calendar5.set(Calendar.HOUR_OF_DAY,0);
        calendar5.set(Calendar.MINUTE,0);
        calendar5.set(Calendar.SECOND,0);
        lastYearSynTotal.setStartTime(calendar5.getTime());
        this.lastYearSynTotal=lastYearSynTotal;


        //去年整年
        Calendar calendar6=Calendar.getInstance();
        calendar6.setTime(startTime);
        calendar6.set(Calendar.MONDAY,0);
        calendar6.set(Calendar.DATE,1);
        calendar6.set(Calendar.HOUR_OF_DAY,23);
        calendar6.set(Calendar.MINUTE,59);
        calendar6.set(Calendar.SECOND,59);
        calendar6.add(Calendar.DATE,-1);//去年最后一天
        Time lastYear=new Time();
        lastYear.setEndTime(calendar6.getTime());
        calendar6.set(Calendar.MONDAY,0);
        calendar6.set(Calendar.DATE,1);
        calendar6.set(Calendar.HOUR_OF_DAY,0);
        calendar6.set(Calendar.MINUTE,0);
        calendar6.set(Calendar.SECOND,0);
        lastYear.setStartTime(calendar6.getTime());
        this.lastYear=lastYear;

    }


    public Time getThisMonth() {
        return thisMonth;
    }

    public void setThisMonth(Time thisMonth) {
        this.thisMonth = thisMonth;
    }

    public Time getThisYear() {
        return thisYear;
    }

    public void setThisYear(Time thisYear) {
        this.thisYear = thisYear;
    }

    public Time getLastMonthSyn() {
        return lastMonthSyn;
    }

    public void setLastMonthSyn(Time lastMonthSyn) {
        this.lastMonthSyn = lastMonthSyn;
    }

    public Time getLastMonth() {
        return lastMonth;
    }

    public void setLastMonth(Time lastMonth) {
        this.lastMonth = lastMonth;
    }

    public Time getLastYearSyn() {
        return lastYearSyn;
    }

    public void setLastYearSyn(Time lastYearSyn) {
        this.lastYearSyn = lastYearSyn;
    }

    public Time getLastYearSynTotal() {
        return lastYearSynTotal;
    }

    public void setLastYearSynTotal(Time lastYearSynTotal) {
        this.lastYearSynTotal = lastYearSynTotal;
    }

    public Time getLastYear() {
        return lastYear;
    }

    public void setLastYear(Time lastYear) {
        this.lastYear = lastYear;
    }




    public class Time{

        private Date startTime;
        private Date endTime;

        public Time() {}

        public Time(Date startTime, Date endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public Date getStartTime() {
            return startTime;
        }

        public void setStartTime(Date startTime) {
            this.startTime = startTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            SimpleDateFormat  simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return "Time{" +
                    "startTime=" + simpleDateFormat.format(startTime) +
                    ", endTime=" + simpleDateFormat.format(endTime)  +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "DateTime{" +
                "本月=" + thisMonth +
                ", 本年=" + thisYear +
                ", 上月同期=" + lastMonthSyn +
                ", 上月=" + lastMonth +
                ", 上年同期=" + lastYearSyn +
                ", 上年同期累计=" + lastYearSynTotal +
                ", 去年整年=" + lastYear +
                '}';
    }


    public static void main(String[] args){
        DateTime dateTime=new DateTime();

        System.out.println(dateTime);
    }
}
