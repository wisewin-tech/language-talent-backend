package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.SpecialBO;
import com.wisewin.backend.entity.bo.SpecialClassBO;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;

import java.util.List;

/**
 * 专题的管理
 * */
public interface SpecialDAO {
    /**
     * 专题的增加
     * */
    Integer addSpecial(SpecialBO specialBO);

    /**
     * 批量修改专题状态
     * */
    Integer delSpecialById(@Param("idArr") Integer[] idArr, @Param("status") String status,@Param("updateId")Integer updateId);
    /**
     * 修改一条专题的信息
     * */
    Integer updateSpecialById(SpecialBO specialBO);

    /**
     * 专题分类 状态的查询
     * */
    List<SpecialBO> selectSpecialBO(@Param("status") String status,@Param("classId")Integer classId,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    /**
     * 专题分类 状态的查询数量
     * */
    Integer selectSpecialBOCount(@Param("status") String status,@Param("classId")Integer classId);


}
