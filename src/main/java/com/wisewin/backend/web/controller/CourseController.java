package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.CourseBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.CourseParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.CourseService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.OSSClientUtil;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  课程
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseCotroller {

    @Resource
    private CourseService  courseService;
    @Resource
    private LogService logService;
    /**
     *  查询课程列表
     *  courseName 课程名字
     *  status  状态
     *  hotOrNot 是否为热门
     *  certificateOrNot  是否可以考证
     * @return
     */
    @RequestMapping(value = "/queryCourseList",method= RequestMethod.POST)
    public void queryCourseList(HttpServletRequest request, HttpServletResponse response, CourseParam  courseParam){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,courseParam);
        QueryInfo queryInfo = getQueryInfo(courseParam.getPageNo(),courseParam.getPageSize());
        Map<String, Object> queryMap = new HashMap<String, Object>();
        if(queryInfo != null){
            queryMap.put("pageOffset", queryInfo.getPageOffset());
            queryMap.put("pageSize", queryInfo.getPageSize());
        }

        queryMap.put("courseName",courseParam.getCourseName());
        queryMap.put("status",courseParam.getStatus());
        queryMap.put("hotOrNot",courseParam.getHotOrNot());
        queryMap.put("certificateOrNot",courseParam.getCertificateOrNot());
        queryMap.put("languageId",courseParam.getLanguageId());

        logService.call("courseService.queryCourseList()",queryMap);
        List<CourseBO> courseBOS = courseService.queryCourseList(queryMap);
        Integer count = courseService.queryCourseCount(queryMap);
        Map<String,Object>  resultMap = new HashMap<String, Object>();
        resultMap.put("count",count);
        resultMap.put("courseList",courseBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
        logService.end("/course/queryCourseList",json);
    }


    /**
     *  添加课程
     * @param request
     * @param response
     * @param courseBO
     */
   @RequestMapping(value = "/addCourse",method= RequestMethod.POST)
   public void addCourse(HttpServletRequest request,HttpServletResponse  response,CourseBO  courseBO){
       AdminBO loginAdmin = super.getLoginAdmin(request);
       logService.startController(loginAdmin,request,courseBO);
       if(StringUtils.isEmpty(courseBO.getCourseName()) || courseBO.getLanguageId()==null || courseBO.getPrice()==null){
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
           super.safeJsonPrint(response, json);
           logService.end("/course/addCourse",json);
           return;
       }

       if(new BigDecimal("0").compareTo(courseBO.getPrice())==1){
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000013"));
           logService.end("/course/addCourse",json);
           super.safeJsonPrint(response, json);
           return;
       }

        logService.call("courseService.addCourse()",courseBO);
       boolean falg = courseService.addCourse(courseBO,loginAdmin.getId());

       if(falg){
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
           super.safeJsonPrint(response, json);
           logService.end("/course/addCourse",json);
           return;
       }
       String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
       super.safeJsonPrint(response, json);
       logService.end("/course/addCourse",json);
       return;
   }

    /**
     *上传讲义
     * @param response
     * @param request
     */
    @RequestMapping("/upFile")
    public void upFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
            throws Exception {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request);
        //图片非空判断
        if (file==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        //上传
        String name=ossClientUtil.uploadImg2Oss(file,true);
        //name:图片路径+图片名(图片名为生成的随机数)
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(name));
        super.safeJsonPrint(response,json);
        logService.end("/course/upFile",json);
    }

    /**
     *  修改课程
     * @param request
     * @param response
     * @param courseBO
     */
    @RequestMapping(value = "/updateCourse",method= RequestMethod.POST)
    public void updateCourse(HttpServletRequest request,HttpServletResponse  response,CourseBO  courseBO){
        AdminBO  adminBO=super.getLoginAdmin(request);
        logService.startController(adminBO,request,courseBO);
        if(courseBO.getId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/course/updateCourse",json);
            return;
        }
        logService.call("courseService.updateCourse()",courseBO,adminBO.getId());
        boolean falg = courseService.updateCourse(courseBO,adminBO.getId());

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            logService.end("/course/updateCourse",json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        logService.end("/course/updateCourse",json);
        return;
    }


    /**
     *  删除课程
     * @param request
     * @param response
     * @Param id  课程id
     */
    @RequestMapping(value = "/deledeCourse",method= RequestMethod.POST)
    public void updateCourse(HttpServletRequest request,HttpServletResponse  response,Integer id){
        AdminBO  adminBO=super.getLoginAdmin(request);
        logService.startController(adminBO,request,id);
        if(id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/course/deledeCourse",json);
            return;
        }
        logService.call("courseService.deledeCourse()",id);
        boolean falg = courseService.deledeCourse(id);

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            logService.end("/course/deledeCourse",json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        logService.end("/course/deledeCourse",json);
        return;
    }



    /**
     *   通过语言查询所有课程
     *  param languageId  语言id
     */
    @RequestMapping(value = "/queryCourseChoice",method= RequestMethod.POST)
     public void  queryCourseChoice(HttpServletRequest request,HttpServletResponse response,Integer languageId){
        AdminBO  adminBO=super.getLoginAdmin(request);
        logService.startController(adminBO,request,languageId);

        if(languageId==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/course/queryCourseChoice",json);
            return;
        }
        logService.call("courseService.queryCourseChoice()",languageId);
        List<LanguageChoiceBO> languageChoiceBOS = courseService.queryCourseChoice(languageId);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(languageChoiceBOS));
        super.safeJsonPrint(response, json);
        logService.end("/course/queryCourseChoice",json);
        return;
    }





}
