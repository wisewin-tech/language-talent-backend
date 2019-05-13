package com.wisewin.backend.service;

import com.wisewin.backend.dao.ClauseDAO;
import com.wisewin.backend.entity.bo.ClauseBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//条款管理
@Service
public class ClauseService {
    @Resource
    ClauseDAO clauseDAO;

    /**
     * 增加一条条款
     * */
    public boolean addClause(ClauseBO clauseBO){
        return clauseDAO.addClause(clauseBO)>0;
    }


    /**
     * 修改一条条款
     * */
    public boolean updClause(ClauseBO clauseBO){
        return clauseDAO.updClause(clauseBO)>0;
    }


    /**
     * 查询一条条款
     * */
    public ClauseBO selectClauseBOById(Integer id){
        return clauseDAO.selectClauseBOById(id);
    }

    /**
     * 查询所有条款
     * */
    public List<ClauseBO> selectClauseBOs(){
        return clauseDAO.selectClauseBOs();
    }


}
