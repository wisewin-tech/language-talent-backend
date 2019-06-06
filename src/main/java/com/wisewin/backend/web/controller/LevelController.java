package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.LevelBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.LeavelParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.LevelService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  级别
 */
@Controller
@RequestMapping("/leavel")
public class LevelController extends BaseCotroller {

    @Resource
    private LevelService levelService;
    @Resource
    private LogService logService;

    /**
     * 查询级别
     * param  courseId  课程id
     * @return
     */
    @RequestMapping(value="/queryLeavelList" ,method= RequestMethod.POST)
    public void  queryLeavelList(HttpServletRequest request, HttpServletResponse  response, LeavelParam  leavelParam){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(leavelParam,request,leavelParam);
        QueryInfo queryInfo = getQueryInfo(leavelParam.getPageNo(),leavelParam.getPageSize());
        Map<String, Object> queryMap = new HashMap<String, Object>();
        if(queryInfo != null){
            queryMap.put("pageOffset", queryInfo.getPageOffset());
            queryMap.put("pageSize", queryInfo.getPageSize());
        }
        queryMap.put("courseName",leavelParam.getCourseId());
        queryMap.put("status",leavelParam.getStatus());
        queryMap.put("courseId",leavelParam.getCourseId());
        queryMap.put("languageId",leavelParam.getLanguageId());

        logService.call("levelService.queryLeavelList",queryMap);
        List<LevelBO> levelBOS = levelService.queryLeavelList(queryMap);
        logService.result(levelBOS);
        logService.call("levelService.queryLeavelCount",queryMap);
        Integer count = levelService.queryLeavelCount(queryMap);
        logService.result(count);
        Map<String,Object>  resultMap=new HashMap<String, Object>();
        resultMap.put("levelList",levelBOS);
        resultMap.put("count",count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        logService.end("leavel/queryLeavelList",json);
        super.safeJsonPrint(response, json);
    }

    /**
     *  添加级别
     * @param request
     * @param response
     * @param levelBO
     */
    @RequestMapping(value="/addLeavel",method= RequestMethod.POST)
    public void addLeavel(HttpServletRequest request,HttpServletResponse  response,LevelBO levelBO){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,levelBO);
        if(levelBO.getLevelName()==null||levelBO.getCourseId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("leavel/addLeavel",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("levelService.addLeavel",levelBO,loginAdmin.getId());
        boolean falg = levelService.addLeavel(levelBO,loginAdmin.getId());
        logService.result(falg);

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            logService.end("leavel/addLeavel",json);
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("leavel/addLeavel",json);
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  修改级别
     * @param request
     * @param response
     * @param levelBO
     */
    @RequestMapping(value = "/updateCourse",method= RequestMethod.POST)
    public void updateCourse(HttpServletRequest request,HttpServletResponse  response,LevelBO levelBO){
        AdminBO  adminBO=super.getLoginAdmin(request);
        logService.startController(adminBO,request,levelBO);
        if(levelBO.getId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("leavel/updateCourse",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("levelService.updateLeavel",levelBO,adminBO.getId());
        boolean falg = levelService.updateLeavel(levelBO,adminBO.getId());
        logService.result(falg);

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            logService.end("leavel/updateCourse",json);
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("leavel/updateCourse",json);
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  删除级别
     * @param request
     * @param response
     * @Param id  级别id
     */
    @RequestMapping(value = "/deledeCourse",method= RequestMethod.POST)
    public void updateCourse(HttpServletRequest request,HttpServletResponse  response,Integer id){
        AdminBO  adminBO=super.getLoginAdmin(request);
        logService.startController(adminBO,request,id);
        if(id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("leavel/deledeCourse",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("levelService.deleteLeavel",id);
        boolean falg = levelService.deleteLeavel(id);
        logService.result(falg);
        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            logService.end("leavel/deledeCourse",json);
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("leavel/deledeCourse",json);
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *   查询级别
     *  param courseId  课程id
     */
    @RequestMapping(value = "/queryCourseChoice",method= RequestMethod.POST)
    public void  queryCourseChoice(HttpServletRequest request,HttpServletResponse response,Integer courseId){
        AdminBO  adminBO=super.getLoginAdmin(request);
        logService.startController(adminBO,request,courseId);
        if(courseId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("leavel/queryCourseChoice",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("levelService.queryLeavelChoice",courseId);
        List<LevelBO> levelBOS = levelService.queryLeavelChoice(courseId);
        logService.result(levelBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(levelBOS));
        logService.end("leavel/queryCourseChoice",json);
        super.safeJsonPrint(response, json);
        return;
    }


}