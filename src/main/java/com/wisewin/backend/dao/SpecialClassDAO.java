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
    Integer delSpecialClassById(@Param("idArr") Integer[] idArr,@Param("status")String status);
    /**
     * 专题分类的修改
     * */
    Integer updateSpecialClassById(SpecialClassBO specialClassBO);

    /**
     * 专题展示或者不展示分类的查询
     * */
    List<SpecialClassBO> selectSpecialClassBO(@Param("status")String status);
}
