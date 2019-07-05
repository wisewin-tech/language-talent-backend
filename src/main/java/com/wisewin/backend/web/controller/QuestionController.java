package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.ChapterIdBO;
import com.wisewin.backend.entity.bo.QuestionBO;
import com.wisewin.backend.entity.bo.common.constants.QuestionConstants;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.QuestionService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
public class QuestionController extends BaseCotroller{
    @Resource
    private QuestionService questionService;
    @Resource
    private LogService logService;

    /**
     * 添加题库
     * @param questionBO
     * @param request
     * @param response
     */
    @RequestMapping("/addQuestion")
    public void  addQuestion(QuestionBO questionBO,HttpServletRequest request, HttpServletResponse response){
        AdminBO adminBO = super.getLoginAdmin(request);
        logService.startController(adminBO,request,questionBO);
        if (questionBO==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            logService.end("question/addQuestion",json);
            super.safeJsonPrint(response, json);
            return;
        }

        questionBO.setCreateUserId(adminBO.getId());
        logService.call("questionService.addquestion",questionBO);
        Integer i = questionService.addquestion(questionBO);
        logService.result(i);
        if (i>0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加题目到题库成功!"));
            logService.end("question/addQuestion",json);
            super.safeJsonPrint(response, json);
        }else {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "添加题目到题库失败！"));
            logService.end("question/addQuestion",json);
            super.safeJsonPrint(response, json);
        }

    }

    /**
     * 查询题库
     * @param pageNo
     * @param pageSize
     * @param questionBO
     * @param response
     */
    @RequestMapping("/selectQuestion")
    public void selectQuestion(Integer pageNo, Integer pageSize,QuestionBO questionBO,HttpServletResponse response,HttpServletRequest request){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,questionBO,pageNo,pageSize);
        if (questionBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            logService.end("question/selectQuestion",result);
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
        logService.call("questionService.selectQuestion",maps);
        List<QuestionBO> questionBOList = questionService.selectQuestion(maps);
        logService.result(questionBOList);
        logService.call("questionService.selectbylimitCount",maps);
        Integer count = questionService.selectbylimitCount(maps);
        logService.result(count);
        Map<String,Object>  resultMap=new HashMap<String, Object>();
        resultMap.put("data",questionBOList);
        resultMap.put("count",count);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        logService.end("question/selectQuestion",result);
        super.safeJsonPrint(response, result);

    }

    /**
     * 修改题库
     * @param questionBO
     * @param request
     * @param response
     */
    @RequestMapping("/updateQuestion")
    public void updateQuestion(QuestionBO questionBO,HttpServletRequest request ,HttpServletResponse response){
        AdminBO adminBO = super.getLoginAdmin(request);
        logService.startController(adminBO,request,questionBO);
        if (questionBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            logService.end("question/updateQuestion",result);
            super.safeJsonPrint(response, result);
            return ;
        }

        questionBO.setUpdateUserId(adminBO.getId());

        logService.call("questionService.updateQuestion",questionBO);
        boolean i = questionService.updateQuestion(questionBO);
        logService.result(i);
        if (i){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功！"));
            logService.end("question/updateQuestion",result);
            super.safeJsonPrint(response, result);
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000012","修改失败！"));
            logService.end("question/updateQuestion",result);
            super.safeJsonPrint(response, result);
        }
    }

    /**
     * 删除题库
     * @param idArrJSON
     * @param response
     */
    @RequestMapping("/delQuestion")
    public void delQuestion(String idArrJSON,HttpServletResponse response,HttpServletRequest request){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,idArrJSON);
        //用户传参验证
        if (StringUtils.isEmpty(idArrJSON)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("question/delQuestion",json);
            super.safeJsonPrint(response, json);
            return;
        }

        Integer[] idArr=null;
        //把用户的参数中有id的转为json数组
        try {
            idArr=JsonUtils.getIntegerArray4Json(idArrJSON);

        }catch (Exception e){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("question/delQuestion",json);
            super.safeJsonPrint(response, json);
            return;
        }


        if (idArr.length==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("question/delQuestion",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("questionService.delQuestion",idArr);
        Integer i = questionService.delQuestion(idArr);
        logService.result(i);
        if (i>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除题目成功！共删除"+i+"条数据"));
            logService.end("question/delQuestion",result);
            super.safeJsonPrint(response, result);
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "删除题目失败！")) ;
            logService.end("question/delQuestion",result);
            super.safeJsonPrint(response, result);
        }
    }

    /**
     * 通过id查询题库
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("/getQuestionById")
    public void getQuestionById(Integer id,HttpServletRequest request,HttpServletResponse response) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        if (id == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("question/getQuestionById",result);
            super.safeJsonPrint(response, result);
            return;
        }
        logService.call("questionService.getQuestion",id);
        QuestionBO questionBO = questionService.getQuestion(id);
        logService.result(questionBO);
        ChapterIdBO idBO = new ChapterIdBO();
        if (questionBO != null) {
            if (QuestionConstants.LANGUAGETEST.getValue().equals(questionBO.getTestType())) {
                idBO.setLanguageId(questionBO.getRelevanceId());
            } else if (QuestionConstants.COURSECERTIFICATE.getValue().equals(questionBO.getTestType())) {
                logService.call("questionService.getCourseId",questionBO.getRelevanceId());
                idBO = questionService.getCourseId(questionBO.getRelevanceId());
                logService.result(idBO);
            } else {
                logService.call("questionService.getChapterId",questionBO.getRelevanceId());
                idBO = questionService.getChapterId(questionBO.getRelevanceId());
                logService.result(idBO);
            }
        }
            Map map = new HashMap();
            map.put("question", questionBO);
            map.put("idBO", idBO);

            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
            logService.end("question/getQuestionById",result);
            super.safeJsonPrint(response, result);
            return;
        }


    /**
     *  导入试题
     */
     @RequestMapping("/importQuestions")
     public void importQuestions(MultipartFile file,HttpServletRequest  request,HttpServletResponse  response){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,file);
        if(loginAdmin==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            logService.end("question/importQuestions",result);
            super.safeJsonPrint(response, result);
            return;
        }
        if(file==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("question/importQuestions",result);
            super.safeJsonPrint(response, result);
            return;
        }

        String name = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        if (".xlsx".equals(name) || ".xls".equals(name) ) {
            logService.call("questionService.importQuestions",file,loginAdmin.getId());
            Integer row=questionService.importQuestions(file,loginAdmin.getId());
            logService.result(row);
            if(row==null){
                questionService.synchronizeQuestions(loginAdmin.getId());
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
                super.safeJsonPrint(response, result);
            }else if(row==-1){
                questionService.deleteTest(loginAdmin.getId());
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000010"));
                super.safeJsonPrint(response, result);
                return;
            }else if(row<-2) {
                questionService.deleteTest(loginAdmin.getId());
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000006","第"+((row - -2)*-1)+"行数据出现问题,坐标出现问题"));
                super.safeJsonPrint(response, result);
                return;
            }else {
                questionService.deleteTest(loginAdmin.getId());
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000006","第"+row+"行数据出现问题"));
                super.safeJsonPrint(response, result);
                return;
            }
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000005"));
        logService.end("question/importQuestions",result);
        super.safeJsonPrint(response, result);
     }



}