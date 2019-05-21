package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.SpecialClassDAO;
import com.wisewin.backend.entity.bo.AdminBO;
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
     * 按状态 分页 展示 专题分类
     * */
    @RequestMapping("selectSpecialClassBO")
    public void selectSpecialClassBO(HttpServletRequest request, HttpServletResponse response,String status,Integer pageNo,Integer pageSize){
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
     * 查询状态正常的专题分类name id
     * */
    @RequestMapping("selectNameAndId")
    public void selectNameAndId(HttpServletRequest request, HttpServletResponse response){
        List<SpecialClassBO> specialClassBOList=specialClassService.selectNameAndId();
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(specialClassBOList));
        super.safeJsonPrint(response,json);
    }

    /**
     * 修改一条或者多条专题分类状态
     * */
    @RequestMapping("delSpecialClassById")
    public void delSpecialClassById(HttpServletRequest request, HttpServletResponse response,String idArr,String status){
        if(idArr==null||idArr.equals("")||status==null||status.equals("")){
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
        boolean b=specialClassService.delSpecialClassById(ids,status,id);
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

        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();

        specialClassBO.setUpdateId(id);

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
        if(specialClassBO==null||specialClassBO.getTitle()==null){
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

        specialClassBO.setCreateId(id);

        if(specialClassService.addSpecialClass(specialClassBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "添加失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }

}
