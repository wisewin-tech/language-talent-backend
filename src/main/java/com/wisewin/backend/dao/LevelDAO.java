package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.LevelBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *  级别
 */
public interface LevelDAO {
    /**
     * 查询级别
     * param  courseId  课时id
     * @return
     */
    List<LevelBO> queryLeavelList(Map<String,Object> map);

    /**
     * 查询级别数量
     * param courseId  课时id
     * @return
     */
    Integer  queryLeavelCount(Map<String,Object> map);

    /**
     * 添加级别
     */
    Integer addLeavel(LevelBO  levelBO);

    /**
     * 修改级别
     */
    Integer updateLeavel(LevelBO levelBO);

    /**
     * 找查级别
     * @Param id 级别id
     */
    LevelBO queryLeavelById(Integer id);


    /**
     * 查询级别
     * @Param courseId 课程id
     */
    List<LevelBO> queryLeavelChoice(Integer courseId);

    /**
     * 通过课程id和级别名字查询级别id
     * @param courseId
     * @param name
     * @return
     */
    Integer queryLeavelIdByName(@Param("courseId") Integer courseId,@Param("name") String name);

}
