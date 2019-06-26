package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.ClauseBO;

import java.util.List;

//条款管理
public interface ClauseDAO {
    /**
     * 增加一条条款
     * */
    Integer addClause(ClauseBO clauseBO);


    /**
     * 修改一条条款
     * */
    Integer updClause(ClauseBO clauseBO);


    /**
     * 查询一条条款
     * */
    ClauseBO selectClauseBOById(Integer id);

    /**
     * 查询所有条款
     * */
    List<ClauseBO> selectClauseBOs();



}
