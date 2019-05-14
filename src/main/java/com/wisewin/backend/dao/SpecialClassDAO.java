package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.SpecialClassBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 专题分类的管理
 * */
public interface SpecialClassDAO {
    /**
     * 专题分类的增加
     * */
    Integer addSpecialClass(SpecialClassBO specialClassBO);

    /**
     * 专题分类的删除
     * */
    Integer delSpecialClassById(@Param("idArr") String[] idArr,@Param("status")String status,@Param("updateId")Integer updateId);
    /**
     * 专题分类的修改
     * */
    Integer updateSpecialClassById(SpecialClassBO specialClassBO);

    /**
     * 分页 专题展示或者不展示的分类查询
     * */
    List<SpecialClassBO> selectSpecialClassBO(@Param("status")String status,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    /**
     * 查询 专题展示或者不展示的共有多少条
     * */
    Integer selectSpecialClassBOCount(@Param("status")String status);

    /**
     * 查询 状态正常的专题名称和id
     * */
    List<SpecialClassBO> selectNameAndId();

}
