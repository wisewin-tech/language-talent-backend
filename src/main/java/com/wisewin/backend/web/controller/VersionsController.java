package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.VersionsBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.VersionsService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/Versions")
public class VersionsController  extends BaseCotroller{


    @Resource
    private VersionsService versionsService;
    @Resource
    private LogService logService;

    /**
     * 添加版本
     */
    @RequestMapping("/addVersions")
    public void addVersions(HttpServletRequest request, HttpServletResponse response,VersionsBO versionsBO){
        //创建获取管理员id
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,versionsBO);
        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            logService.end("Versions/addVersions",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        if (id==null ||versionsBO==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Versions/addVersions",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        versionsBO.setAdminId(id);
        logService.call("versionsService.getaddVersions",versionsBO);
        if (versionsService.getaddVersions(versionsBO)){
            String result=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            logService.end("Versions/addVersions",request);
            super.safeJsonPrint(response,result);
        }else{
            String result= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1111111"));
            logService.end("Versions/addVersions",request);
            super.safeHtmlPrint(response,result);
        }

    }


    /**
     * 通过版本查询
     */
    @RequestMapping("/queryVersions")
    public void queryVersions(HttpServletRequest request,HttpServletResponse response,Integer pageNo,Integer pageSize){

        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,pageNo,pageSize);
        //根据版本号来查询
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        if(queryInfo != null){
            logService.call("versionsService.getqueryVersions()",queryInfo.getPageOffset(),queryInfo.getPageSize());
            List<VersionsBO> queryVersionsjson=versionsService.getqueryVersions(queryInfo.getPageOffset(),queryInfo.getPageSize());
            logService.result(queryVersionsjson);
            Integer count=versionsService.selectVersionBOCount();
            Map<String,Object> map=new HashMap<>();
            map.put("queryVersionsjson",queryVersionsjson);
            map.put("count",count);
            String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            logService.end("VersionsController/queryVersions",json);
            super.safeJsonPrint(response,json);
            return;
        }
    }


    /**
     * 删除
     */
    @RequestMapping("/deleteVersions")
    public void deleteVersions(HttpServletRequest request,HttpServletResponse response,Integer vid){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,vid);
        if (vid==null||vid.equals("")){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Versions/deleteVersions",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        logService.call("versionsService.getdeleteVersions",vid);
        boolean deleteVersionsjson=versionsService.getdeleteVersions(vid);
        logService.result(deleteVersionsjson);
        if (deleteVersionsjson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            logService.end("Versions/deleteVersions",languagejson);
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1111111"));
        logService.end("Versions/deleteVersions",languagejson);
        super.safeHtmlPrint(response,languagejson);
        return;
    }
}
