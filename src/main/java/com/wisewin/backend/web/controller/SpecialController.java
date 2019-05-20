package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.SpecialBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.SpecialService;
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

    /**
     * 按展示或者为展示也就是yes no 展示 专题分类
     * */
    @RequestMapping("selectSpecialBO")
    public void selectSpecialBO(HttpServletRequest request, HttpServletResponse response,String status,Integer classId,Integer pageNo,Integer pageSize){
        Map<String,Object> map=new HashMap<String, Object>();
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        if(queryInfo != null) {
            List<SpecialBO> specialBOList=specialService.selectSpecialBO(status,classId,queryInfo.getPageOffset(),queryInfo.getPageSize());
            Integer count=specialService.selectSpecialBOCount(status,classId);
            map.put("count",count);
            map.put("specialBOList",specialBOList);
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            super.safeJsonPrint(response,json);
        }




    }

    /**
     * 修改一条或者多条专题分类状态
     * */
    @RequestMapping("delSpecialById")
    public void delSpecialById(HttpServletRequest request, HttpServletResponse response,String idArr,String status){
        if(idArr==null||idArr.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();


        String[] ids=idArr.split(",");
        boolean b=specialService.delSpecialById(ids,status,id);
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
    @RequestMapping("updateSpecialById")
    public void updateSpecialById(HttpServletRequest request, HttpServletResponse response,SpecialBO specialBO){
        if(specialBO==null||specialBO.getId()==null||specialBO.getId()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        specialBO.setUpdateId(id);

        if(specialService.updateSpecialById(specialBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }


    /**
     * 增加一条专题
     * */
    @RequestMapping("addSpecial")
    public void addSpecial(HttpServletRequest request, HttpServletResponse response,SpecialBO specialBO) throws IOException {
        if(specialBO==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        specialBO.setCreateId(id);
        if(specialService.addSpecial(specialBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "添加失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 查看单个专题
     * */
    @RequestMapping("selectSpecialBOById")
    public void selectSpecialBOById(HttpServletRequest request, HttpServletResponse response,Integer id){
        if(id==null||id.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        SpecialBO specialBO=specialService.selectSpecialBOById(id);
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(specialBO));
        super.safeJsonPrint(response,json);
    }


}
