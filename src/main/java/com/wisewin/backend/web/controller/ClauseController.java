package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.ClauseDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.BannerBO;
import com.wisewin.backend.entity.bo.ClauseBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.ClauseService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *  条款
 */
@Controller
@RequestMapping("/Clause")
public class ClauseController extends BaseCotroller {

    @Resource
    ClauseService clauseService;

    /**
     * 增加一条条款
     * */
    @RequestMapping("/addClause")
    public void addClause(HttpServletRequest request, HttpServletResponse response,ClauseBO clauseBO){
        if(clauseBO==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
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

        clauseBO.setUpdateId(id);
        if(clauseService.addClause(clauseBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "添加失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }


    /**
     * 修改一条条款
     * */
    @RequestMapping("/updClause")
    public void updClause(HttpServletRequest request, HttpServletResponse response,ClauseBO clauseBO){
        if(clauseBO==null){
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

        clauseBO.setUpdateId(id);

        if(clauseService.updClause(clauseBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }


    /**
     * 查询一条条款
     * */
    @RequestMapping("/selectClauseBOById")
    public void selectClauseBOById(HttpServletRequest request, HttpServletResponse response,Integer id){
        if(id==null || id.equals("")||id==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        ClauseBO ClauseBO=clauseService.selectClauseBOById(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(ClauseBO));
        super.safeJsonPrint(response,json);
    }

    /**
     * 查询所有条款
     * */
    @RequestMapping("/selectClauseBOs")
    public void selectClauseBOs(HttpServletRequest request, HttpServletResponse response){
        List<ClauseBO> ClauseBOList=clauseService.selectClauseBOs();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(ClauseBOList));
        super.safeJsonPrint(response,json);
    }

}
