package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.bo.VersionsBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.VersionsParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.VersionsService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/Versions")
public class VersionsController  extends BaseCotroller{


    @Resource
    private VersionsService versionsService;

    /**
     * 添加版本
     *   Integer versionsnum; //发版次数
     * String model; //版本号
     * String content; //内容
     * Integer adminId; //后台管理员id
     * Integer updateadminId; //后台管理员修改id
     * String compatibility; //兼容版本
     */
    @RequestMapping("/addVersions")
    public void addVersions(HttpServletRequest request, HttpServletResponse response, VersionsParam param){
            //创建获取管理员id
        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();


        if (id==null && param.getModel().equals("") && param.getCompatibility().equals("")){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        boolean addVersionsjson=versionsService.getaddVersions(param.getVersionsnum(),param.getModel(),param.getContent(),id,id,param.getCompatibility());
        if (addVersionsjson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }


    /**
     * 通过版本号查询
     * @param request
     * @param response
     * @param param
     *       String model   //版本号
     *      Integer pageNo; //页数
    *      Integer pageSize; //条数
     */
    @RequestMapping("/queryVersions")
    public void queryVersions(HttpServletRequest request,HttpServletResponse response,VersionsParam param){

        QueryInfo queryInfo=getQueryInfo(param.getPageNo(),param.getPageSize());
        if (queryInfo!=null){
            param.setPageNo(queryInfo.getPageOffset());
            param.setPageSize(queryInfo.getPageSize());
        }
        List<VersionsParam> queryVersionsjson=versionsService.getqueryVersions(param);
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(queryVersionsjson));
        super.safeJsonPrint(response,json);
        return;
    }

    /**
     * 批量删除
     */
    @RequestMapping("/deleteVersions")
    public void deleteVersions(HttpServletRequest request,HttpServletResponse response,String vid){

        if (vid.equals("")){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        //批量删除，分割
        String[] id=vid.split(",");

        boolean deleteVersionsjson=versionsService.getdeleteVersions(id);
        if (deleteVersionsjson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }
    /**
     * 修改
     */
     @RequestMapping(value = "/updateVersions",method  = RequestMethod.POST)
    public void updateVersions(HttpServletRequest request,HttpServletResponse response, VersionsParam param){
         AdminBO loginAdmin = super.getLoginAdmin(request);

         if(loginAdmin==null){
             String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
             super.safeHtmlPrint(response,languagejson);
             return;
         }
         Integer id = loginAdmin.getId();
        if (param.getId()==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        boolean updateVersionsjson=versionsService.getupdateVersions(param.getId(),param.getVersionsnum(),param.getModel(),param.getContent(),
                id,param.getCompatibility());
        if (updateVersionsjson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }else {
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

    }
}
