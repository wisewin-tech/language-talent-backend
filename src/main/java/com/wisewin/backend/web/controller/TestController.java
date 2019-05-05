package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    ///
    @Resource
    private TestDAO testDAO;

    @RequestMapping("/test")
    public void test(HttpServletResponse response, HttpServletRequest  request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(loginAdmin));
        super.safeJsonPrint(response, json);

    }

    public static void main(String[] args) throws ParseException {
       String startTime = "2018-11-06 11:00:00";
        String endTime = "2018-11-08 11:15:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date d1 = df.parse(startTime);
        Date d2 = df.parse(endTime);
        System.out.println(BigDecimal.valueOf(Math.ceil((((double) d2.getTime() - (double) d1.getTime()) / 3600000)% 24)));
        System.out.println(BigDecimal.valueOf(Math.floor(((double) d2.getTime() - (double) d1.getTime()) / (3600000 * 24))));

        System.out.println(BigDecimal.valueOf(Math.ceil((((double) d2.getTime() - (double) d1.getTime()-(double)900000) / 3600000)% 24)));
        System.out.println(BigDecimal.valueOf(Math.floor(((double) d2.getTime() - (double) d1.getTime()-(double)900000) / (3600000 * 24))));
       /* BigDecimal b=new BigDecimal(45.45);
        int a = b.intValue();
        System.out.print(a);*//*
        BigDecimal timeOut =BigDecimal.valueOf(0);
        BigDecimal price =BigDecimal.valueOf(100);
        BigDecimal hours = BigDecimal.valueOf(2.0);
        timeOut=timeOut.add(price.multiply(hours));
        System.out.print(timeOut);*/
       /* String[] strs = {"aa,bb","bb,bb","cc"};
         //String数组转List
        List<String> strsToList1= Arrays.asList(strs);
        System.out.print(strsToList1);*/
      /*double  time= 1.62113639E8;
        double s =Math.floor(time/(24 * 60 * 60 * 1000));
        System.out.print(s);

         BigDecimal timeOut =BigDecimal.valueOf(0);
        timeOut=timeDay.add(typeBO.getPrice());*/
    }
}
