package com.wisewin.backend.util.dates;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 转换时间公用类
 */
public class DateUtil {

    /**
     * String转换为date类型
     * yyyy-MM-dd HH:mm:ss
     */
    public static Date gainDate(String date){
        Date  thisDate=null;
        try {
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取String类型的时间
            thisDate = sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return thisDate;
    }


    /**
     * String转换为date类型
     * yyyy-MM-dd
     */
    public  static Date getDate(String date){
        Date thisDate=null;
        try {
            //设置要获取到什么样的时间
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            //获取String类型的时间
            thisDate = sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        return  thisDate;

    }
    /**
     * String转换为String类型
     * yyyy-MM-dd
     */
    public  static String getStr(String date) {
        if(date==null || "".equals(date)){
            return null;
        }
        Date thisDate=null;
        //设置要获取到什么样的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //获取Date类型的时间
            thisDate = sdf.parse(date);
        }catch (Exception e){
            e.printStackTrace();
            return  null;
        }
        String str=sdf.format(thisDate);
        return str;

    }
}
