package com.wisewin.backend.service;

import com.wisewin.backend.dao.SpecialClassDAO;
import com.wisewin.backend.entity.bo.SpecialClassBO;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.INTERNAL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("SpecialClassService")
@Transactional
public class SpecialClassService {

    @Resource
    SpecialClassDAO specialClassDAO;

    /**
     * 专题分类的增加
     * */
    public boolean addSpecialClass(SpecialClassBO specialClassBO){
        return specialClassDAO.addSpecialClass(specialClassBO)>0;
    }

    /**
     * 专题分类的删除
     * */
    public boolean delSpecialClassById(Integer[] idArr,String status,Integer id){
        return specialClassDAO.delSpecialClassById(idArr,status,id)>0;
    }

    /**
     * 专题分类的修改
     * */
    public boolean updateSpecialClassById(SpecialClassBO specialClassBO){
        return specialClassDAO.updateSpecialClassById(specialClassBO)>0;
    }

    /**
     * 专题分类的查询
     * */
    public List<SpecialClassBO> selectSpecialClassBO(String status, Integer pageNo,Integer pageSize){
        return specialClassDAO.selectSpecialClassBO(status,pageNo,pageSize);
    }

    /**
     * 查询 专题展示或者不展示的共有多少条
     * */
    public Integer selectSpecialClassBOCount(String status){
        return specialClassDAO.selectSpecialClassBOCount(status);
    }

}
