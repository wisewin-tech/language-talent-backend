package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.StatisticsBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.DataTableService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/dataTable")
public class DataTableController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(DataTableController.class);

    @Resource
    private DataTableService  dataTableService;



    @RequestMapping("/dataTable")
    public void test1(HttpServletResponse response, HttpServletRequest  request, Date time) {
        log.info(request.getRequestURI());
        log.info("param:{}", JsonUtils.getJsonString4JavaPOJO(request.getParameterMap()));
        AdminBO loginAdmin = super.getLoginAdmin(request);
        log.info("user{}", loginAdmin);
        if (loginAdmin == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeJsonPrint(response, result);
            log.info("result{}", result);
            return;
        }

        List<StatisticsBO> data = dataTableService.getData(loginAdmin.getRoleId(), time);
        String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(data));
        super.safeHtmlPrint(response,languagejson);
        log.info("result:{}", languagejson);


    }



}
