package com.wisewin.backend.test;

import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.SystemoperationService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/SystemoperationTest")
public class SystemoperationTest  extends BaseCotroller{

    @Resource
    private SystemoperationService systemoperationService;


    /**
     * 添加
     *  Integer adminId; //后台id
     * String content; //内容
     * String contenttype; //内容类型
     * String operationtype; //操作类型(增删改查)
     */
    @RequestMapping("/addSystemoperation")
    public  void addSystemoperation(HttpServletRequest request, HttpServletResponse response,Integer adminId,String content,String contenttype,String operationtype){

        boolean add=systemoperationService.getaddSystemoperation(adminId, content, contenttype, operationtype);
        if (add){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }




}
