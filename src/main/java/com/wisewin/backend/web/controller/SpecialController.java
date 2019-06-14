package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.SpecialBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.SpecialService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 专题分类
 *
 */
@Controller
@RequestMapping("/Special")
public class SpecialController extends BaseCotroller {

    @Resource
    SpecialService specialService;
    @Resource
    private LogService logService;

    /**
     * 按展示或者为展示也就是yes no 展示 专题分类
     * */
    @RequestMapping("/selectSpecialBO")
    public void selectSpecialBO(HttpServletRequest request, HttpServletResponse response,String status,Integer classId,Integer pageNo,Integer pageSize){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,status,classId,pageNo,pageSize);
        Map<String,Object> map=new HashMap<String, Object>();
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        if(queryInfo != null) {
            logService.call("specialService.selectSpecialBO",status,classId,queryInfo.getPageOffset(),queryInfo.getPageSize());
            List<SpecialBO> specialBOList=specialService.selectSpecialBO(status,classId,queryInfo.getPageOffset(),queryInfo.getPageSize());
            logService.result(specialBOList);
            logService.call("specialService.selectSpecialBOCount",status,classId);
            Integer count=specialService.selectSpecialBOCount(status,classId);
            logService.result(count);
            map.put("count",count);
            map.put("specialBOList",specialBOList);
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            logService.end("Special/selectSpecialBO",json);
            super.safeJsonPrint(response,json);
        }




    }

    /**
     * 修改一条或者多条专题分类状态
     * */
    @RequestMapping("/delSpecialById")
    public void delSpecialById(HttpServletRequest request, HttpServletResponse response,String idArr,String status){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,idArr,status);
        if(idArr==null||idArr.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Special/delSpecialById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }


        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            logService.end("Special/delSpecialById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();


        String[] ids=idArr.split(",");
        logService.call("specialService.delSpecialById",ids,status,id);
        boolean b=specialService.delSpecialById(ids,status,id);
        logService.result(b);
        if(b){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            logService.end("Special/delSpecialById",result);
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            logService.end("Special/delSpecialById",result);
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 修改一条专题分类
     * */
    @RequestMapping("/updateSpecialById")
    public void updateSpecialById(HttpServletRequest request, HttpServletResponse response,SpecialBO specialBO){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,specialBO);
        if(specialBO==null||specialBO.getId()==null||specialBO.getId()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Special/updateSpecialById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            logService.end("Special/updateSpecialById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        specialBO.setUpdateId(id);
        logService.call("specialService.updateSpecialById",specialBO);
        boolean t = specialService.updateSpecialById(specialBO);
        logService.result(t);
        if(t){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            logService.end("Special/updateSpecialById",result);
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            logService.end("Special/updateSpecialById",result);
            super.safeJsonPrint(response , result);
        }
    }


    /**
     * 增加一条专题
     * */
    @RequestMapping("/addSpecial")
    public void addSpecial(HttpServletRequest request, HttpServletResponse response,SpecialBO specialBO) throws IOException {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,specialBO);
        if(specialBO==null||specialBO.getTitle()==null||specialBO.getTitle().equals("")||specialBO.getContent()==null||specialBO.getContent().equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Special/addSpecial",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            logService.end("Special/addSpecial",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        specialBO.setCreateId(id);
        logService.call("specialService.addSpecial",specialBO);
        boolean t = specialService.addSpecial(specialBO);
        logService.result(t);
        if(t){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            logService.end("Special/addSpecial",result);
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1111111" , "添加失敗")) ;
            logService.end("Special/addSpecial",result);
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 查看单个专题
     * */
    @RequestMapping("/selectSpecialBOById")
    public void selectSpecialBOById(HttpServletRequest request, HttpServletResponse response,Integer id){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        if(id==null||id.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Special/selectSpecialBOById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        logService.call("specialService.selectSpecialBOById",id);
        SpecialBO specialBO=specialService.selectSpecialBOById(id);
        logService.result(specialBO);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(specialBO));
        logService.end("Special/selectSpecialBOById",json);
        super.safeJsonPrint(response,json);
    }


}
