package com.wisewin.backend.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.ChapterIdBO;
import com.wisewin.backend.entity.bo.QuestionBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.QuestionService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseCotroller{
    @Resource
    private QuestionService questionService;

    @RequestMapping("/addQuestion")
    public void  addQuestion(QuestionBO questionBO,HttpServletRequest request, HttpServletResponse response){
        if (questionBO==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, json);
            return;
        }
        AdminBO adminBO = super.getLoginAdmin(request);
        questionBO.setCreateUserId(adminBO.getId());

        Integer i = questionService.addquestion(questionBO);
        if (i>0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加题目到题库成功!"));
            super.safeJsonPrint(response, json);
        }else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "添加题目到题库失败！"));
            super.safeJsonPrint(response, json);
        }

    }

    @RequestMapping("/selectQuestion")
    public void selectQuestion(Integer pageNo, Integer pageSize,QuestionBO questionBO,HttpServletResponse response){
        if (questionBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
            return ;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        Map<String, Object> maps = new HashMap<String, Object>();
        if(queryInfo != null){
            maps.put("pageOffset", queryInfo.getPageOffset());
            maps.put("pageSize", queryInfo.getPageSize());
        }
        maps.put("questionBO",questionBO);

        List<QuestionBO> questionBOList = questionService.selectQuestion(maps);
        Integer count = questionService.selectbylimitCount(maps);
        Map<String,Object>  resultMap=new HashMap<String, Object>();
        resultMap.put("data",questionBOList);
        resultMap.put("count",count);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, result);

    }

    @RequestMapping("/updateQuestion")
    public void updateQuestion(QuestionBO questionBO,HttpServletRequest request ,HttpServletResponse response){
        if (questionBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
            return ;
        }

        AdminBO adminBO = super.getLoginAdmin(request);
        questionBO.setUpdateUserId(adminBO.getId());


        boolean i = questionService.updateQuestion(questionBO);
        if (i){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改题目成功！"));
            super.safeJsonPrint(response, result);
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000012","修改题目失败！"));
            super.safeJsonPrint(response, result);
        }
    }

    @RequestMapping("/delQuestion")
    public void delQuestion(String idArrJSON,HttpServletResponse response){
        //用户传参验证
        if (StringUtils.isEmpty(idArrJSON)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        Integer[] idArr=null;
        //把用户的参数中有id的转为json数组
        try {
            idArr=JsonUtils.getIntegerArray4Json(idArrJSON);

        }catch (Exception e){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }


        if (idArr.length==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        Integer i = questionService.delQuestion(idArr);
        if (i>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除题目成功！共删除"+i+"条数据"));
            super.safeJsonPrint(response, result);
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "删除题目失败！")) ;
            super.safeJsonPrint(response, result);
        }
    }

    @RequestMapping("/getQuestionById")
    public void getQuestionById(Integer id,HttpServletRequest request,HttpServletResponse response) {
        if (id == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }
        QuestionBO questionBO = questionService.getQuestion(id);
        ChapterIdBO idBO = new ChapterIdBO();
        if (questionBO != null) {
            if ("languageTest".equals(questionBO.getTestType())) {
                idBO.setLanguageId(questionBO.getRelevanceId());
            } else if (questionBO.getTestType().equals("courseCertificate")) {
                idBO = questionService.getCourseId(questionBO.getRelevanceId());
            } else {
                idBO = questionService.getChapterId(questionBO.getRelevanceId());
            }
        }
            Map map = new HashMap();
            map.put("question", questionBO);
            map.put("idBO", idBO);

            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            super.safeJsonPrint(response, result);
        }
    }



