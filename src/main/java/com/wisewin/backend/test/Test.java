package com.wisewin.backend.test;


import com.wisewin.backend.common.constants.DiscoverConstants;
import com.wisewin.backend.common.constants.LanguageConstants;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Shibo on 17/1/5.
 */
public class Test {


    public static void main111(String[] args) {

        new Test().test();

      /*  Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1=new SimpleDateFormat("mm");
        String a=sdf.format(date);
        String b=sdf.format(date);
        System.out.println(b);
        System.out.println(a);*/
//        Calendar c = Calendar.getInstance();
//        int hour=c.get(Calendar.HOUR_OF_DAY);
//        Date date=new Date();
//        if (hour < 6) {
//            //开始时间-1天 加上 06:00:00
//            date = getDayBefore(date, 1);
//        }
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//            String s1 = sdf.format(date);
//            String s2 = s1 + " 06:00:00";
//
//            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date1=null;
//            try {
//                date1 = sdf1.parse(s2);
//
//
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//
//
//        System.out.println(date1);
//



 /* *//*      double f = 111231.4585;
        BigDecimal bg = new BigDecimal(f);
        double f1 = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);*//*
        DecimalFormat df = new DecimalFormat("0.00%");
        System.out.println(df.format(0.1234));
      int a=1;
      int b=22;
      double c=(double)a/b;
        BigDecimal bg = new BigDecimal(c);
        double f1 = bg.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(f1);
        System.out.println(df.format(f1));
    }*/
      /*  JSONObject result=new JSONObject();
        result.put("1",null);
        result.put("2",2);
        result.put("3",3);
        System.out.println(result.toString());
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        SimpleDateFormat sdf1=new SimpleDateFormat("dd");
        String s1=sdf1.format(date);
        String s=sdf.format(date);
        System.out.println(s+"月"+s1+"日");*/



    }
    public static Date getDayBefore(Date date, int i) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.add(c.DATE,-i); //把日期往后增加i天,整数  往后推,负数往前移动
        return c.getTime();
    }

    public void test () {

        List<String> list = new ArrayList<String>();
        list.add("aaaaaaa");
        list.add("bbbbbbb");
        list.add("bbbbbbb");
        list.add("ccccccc");
        list.add("ddddddd");
        list.add("eeeeeee");
        list.add("fffffff");

        for (int i = 0 ; i < list.size() ; i++ ) {
//            System.out.println(list.get(i));
            if (list.get(i).startsWith("b")){
                list.remove(list.get(i));
            }
        }

        System.out.println(">>>>>>>>>>>>>");

        for (String s : list) {
            System.out.println(s);
        }

    }

    public void testForDeleteList () {
        List<String > list = new ArrayList<String>();
        list.add("a");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
    }

    public void aaa (){

    }

    public static void main(String[] args) throws FileNotFoundException {
        OutputStream os = new FileOutputStream(new File(""));
    }
}
