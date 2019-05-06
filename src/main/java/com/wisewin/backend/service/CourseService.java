package com.wisewin.backend.service;

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
        courseBO.setCreateUserId(userId);
        courseBO.setCreateTime(new Date());
        return  courseDAO.addCourse(courseBO)>0;
    }


    /**
     *  修改课程
     */
   public boolean updateCourse(CourseBO courseBO,Integer userId){
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
}
