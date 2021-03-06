package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.CourseConstants;
import com.wisewin.backend.common.constants.LanguageConstants;
import com.wisewin.backend.dao.CourseDAO;
import com.wisewin.backend.entity.bo.CourseBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  课程
 */
@Service("CourseService")
@Transactional
public class CourseService {
    @Resource
    private CourseDAO  courseDAO;

    @Resource
    private CertificateService  certificateService;
    @Resource
    private OrderService orderService;
    /**
     *  查询课程列表
     *  courseName 课程名字
     *  status  状态
     *  hotOrNot 是否为热门
     *  certificateOrNot  是否可以考证
     * @param map
     * @return
     */
    public List<CourseBO> queryCourseList(Map<String,Object> map){
        return  courseDAO.queryCourseList(map);
    }

    /**
     * 查询数量
     *
     */
    public Integer queryCourseCount(Map<String,Object> map){
        return  courseDAO.queryCourseCount(map);
    }

    /**
     * 添加课程
     */
    public boolean addCourse(CourseBO  courseBO,Integer userId){

        if(courseBO.getStatus()==null)
            courseBO.setStatus(LanguageConstants.STATUS_PUTAWAY.getValue());
        courseBO.setCreateUserId(userId);
        courseBO.setCreateTime(new Date());
        return  courseDAO.addCourse(courseBO)>0;
    }


    /**
     *  修改课程
     */
   public boolean updateCourse(CourseBO courseBO,Integer userId){
       CourseBO course = courseDAO.queryCourseId(courseBO.getId());
       if(course==null){
           return false;
       }
       if(courseBO.getCertificateOrNot()!=null && course.getCertificateOrNot()!=null  &&
               !courseBO.getCertificateOrNot().equals(course.getCertificateOrNot())){  //修改了是否可以考证
           if(CourseConstants.MAY.getValue().equals(courseBO.getCertificateOrNot())){ //改为可以考证
               certificateService.addCertificate(orderService.queryCoursesById(courseBO.getId()),courseBO.getId());
           }else{
               //不可以考证
               if(certificateService.queryUserCount(courseBO.getId())){  //有人购买了可以考证不让修改
                    return false;
               }
           }
       }

       certificateService.queryUserCount(courseBO.getId());
       courseBO.setUpdateUserId(userId);
       courseBO.setUpdateTime(new Date());
        return courseDAO.updateCourse(courseBO)>0;
    }


    /**
     *  删除课程
     * @param id
     * @return
     */
    public boolean deledeCourse(Integer id) {
        CourseBO queryCourse = courseDAO.queryCourseId(id);
        if(queryCourse==null){
            return false;
        }
        if(queryCourse.getStatus()!=null && queryCourse.getStatus().equals(LanguageConstants.STATUS_PUTAWAY.getValue())){  //上架状态
            queryCourse.setStatus(LanguageConstants.STATUS_SOLDOUT.getValue());
        }else{
            queryCourse.setStatus(LanguageConstants.STATUS_PUTAWAY.getValue());
        }
        return courseDAO.updateCourse(queryCourse) > 0;
    }

    /**
     * 通过语言查询所有课程
     *  param languageId  语言id
     */
    public  List<LanguageChoiceBO> queryCourseChoice(Integer languageId){
        return courseDAO.queryCourseChoice(languageId);
    }

    /**
     * 修改购买须知
     * @param notice
     */
    public void updateNotice(String notice){
        courseDAO.updateNotice(notice);
    }


    public Integer queryCourseIdByName(Integer languageId, String name) {
        return  courseDAO.queryCourseIdByName(languageId,name);
    }
}