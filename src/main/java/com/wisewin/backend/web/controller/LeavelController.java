package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.LevelBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.LeavelParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.LeavelService;
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
 *  级别
 */
@Controller
@RequestMapping("/leavel")
public class LeavelController extends BaseCotroller {

    @Resource
    private LeavelService leavelService;

    /**
     * 查询级别
     * param  courseId  课程id
     * @return
     */
    @RequestMapping("/queryLeavelList")
    public void  queryLeavelList(HttpServletRequest request, HttpServletResponse  response, LeavelParam  leavelParam){
        QueryInfo queryInfo = getQueryInfo(leavelParam.getPageNo(),leavelParam.getPageSize());
        Map<String, Object> queryMap = new HashMap<String, Object>();
        if(queryInfo != null){
            queryMap.put("pageOffset", queryInfo.getPageOffset());
            queryMap.put("pageSize", queryInfo.getPageSize());
        }
        queryMap.put("courseName",leavelParam.getCourseId());
        queryMap.put("status",leavelParam.getStatus());
        queryMap.put("languageId",leavelParam.getLanguageId());

        List<LevelBO> levelBOS = leavelService.queryLeavelList(queryMap);
        Integer count = leavelService.queryLeavelCount(queryMap);
        Map<String,Object>  resultMap=new HashMap<String, Object>();
        resultMap.put("levelList",levelBOS);
        resultMap.put("count",count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
    }




    /**
     *  添加级别
     * @param request
     * @param response
     * @param levelBO
     */
    @RequestMapping("/addLeavel")
    public void addLeavel(HttpServletRequest request,HttpServletResponse  response,LevelBO levelBO){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        if(levelBO.getLevelName()==null||levelBO.getCourseId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        boolean falg = leavelService.addLeavel(levelBO,loginAdmin.getId());

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  修改级别
     * @param request
     * @param response
     * @param levelBO
     */
    @RequestMapping("/updateCourse")
    public void updateCourse(HttpServletRequest request,HttpServletResponse  response,LevelBO levelBO){
        AdminBO  adminBO=super.getLoginAdmin(request);
        if(levelBO.getId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        boolean falg = leavelService.updateLeavel(levelBO,adminBO.getId());

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  删除级别
     * @param request
     * @param response
     * @Param id  级别id
     */
    @RequestMapping("/deledeCourse")
    public void updateCourse(HttpServletRequest request,HttpServletResponse  response,Integer id){
        if(id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        boolean falg = leavelService.deleteLeavel(id);
        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *   查询级别
     *  param courseId  课程id
     */
    @RequestMapping("/queryCourseChoice")
    public void  queryCourseChoice(HttpServletRequest request,HttpServletResponse response,Integer courseId){
        if(courseId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        List<LevelBO> levelBOS = leavelService.queryLeavelChoice(courseId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(levelBOS));
        super.safeJsonPrint(response, json);
        return;
    }


}