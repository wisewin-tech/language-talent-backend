package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.SpecialClassDAO;
import com.wisewin.backend.entity.bo.SpecialClassBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.SpecialClassService;
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

/**
 * 专题分类
 *
 */
@Controller
@RequestMapping("/SpecialClass")
public class SpecialClassController extends BaseCotroller {

    @Resource
    SpecialClassService specialClassService;

    /**
     * 按展示或者为展示也就是yes no 还有数量 展示 专题分类
     * */
    @RequestMapping("selectSpecialClassBO")
    public void selectSpecialClassBO(HttpServletRequest request, HttpServletResponse response,String status,Integer pageNo,Integer pageSize){
        if(pageNo==null||pageNo==0||pageSize==null||pageSize==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Map<String,Object> map=new HashMap<String, Object>();
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        if(queryInfo != null){
            List<SpecialClassBO> specialClassBOList=specialClassService.selectSpecialClassBO(status,queryInfo.getPageOffset(),queryInfo.getPageSize());
            Integer count=specialClassService.selectSpecialClassBOCount(status);
            map.put("count",count);
            map.put("specialClassBOList",specialClassBOList);
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            super.safeJsonPrint(response,json);
        }else{
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

    }

    /**
     * 修改一条或者多条专题分类状态
     * */
    @RequestMapping("delSpecialClassById")
    public void delSpecialClassById(HttpServletRequest request, HttpServletResponse response,String idArrJSON,String status){
        if(idArrJSON==null||idArrJSON.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        Integer[] idArr=JsonUtils.getIntegerArray4Json(idArrJSON);
        boolean b=specialClassService.delSpecialClassById(idArr,status);
        if(b){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 修改一条专题分类
     * */
    @RequestMapping("updateSpecialClassById")
    public void updateSpecialClassById(HttpServletRequest request, HttpServletResponse response,SpecialClassBO specialClassBO){
        if(specialClassBO==null||specialClassBO.getId()==null||specialClassBO.getId()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        if(specialClassService.updateSpecialClassById(specialClassBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 增加一条专题分类
     * */
    @RequestMapping("addSpecialClass")
    public void addSpecialClass(HttpServletRequest request, HttpServletResponse response,SpecialClassBO specialClassBO){
        if(specialClassBO==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        if(specialClassService.addSpecialClass(specialClassBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "添加失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }

}