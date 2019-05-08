package com.wisewin.backend.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestDAO testDAO;


    @RequestMapping("/test")
    public void test(HttpServletResponse response, HttpServletRequest  request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(loginAdmin));
        super.safeJsonPrint(response, json);

    }

    @RequestMapping("/test1")
    public void test1(HttpServletResponse response, HttpServletRequest  request) {

        AdminBO loginAdmin = super.getLoginAdmin(request);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(loginAdmin));
        super.safeJsonPrint(response, json);
    }


    public static void main(String[] args) throws ParseException {
      String strArr="[\"a\",\"b\",\"c\",\"d\"]";

        List<String> lists = JSON.parseArray(strArr, String.class);

        System.out.println(lists);
    }
}
