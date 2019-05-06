package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.CourseBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;

import java.util.List;
import java.util.Map;

/**
 * 课程
 */
public interface CourseDAO {


    /**
     *  查询课程列表
     *  courseName 课程名字
     *  status  状态
     *  hotOrNot 是否为热门
     *  certificateOrNot  是否可以考证
     * @param map
     * @return
     */
    List<CourseBO> queryCourseList(Map<String,Object>  map);

    /**
     * 查询数量
     *
     */
    Integer queryCourseCount(Map<String,Object> map);

    /**
     * 添加课程
     */
    int addCourse(CourseBO  courseBO);


    /**
     *  修改课程
     */
    int  updateCourse(CourseBO courseBO);

    /**
     *  通过id 查询课程
     */
    CourseBO queryCourseId(Integer id);


    /**
     * 通过语言查询所有课程
     *  param languageId  语言id
     */
    List<LanguageChoiceBO> queryCourseChoice(Integer languageId);

}
