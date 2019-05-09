package com.wisewin.backend.service;

import com.wisewin.backend.dao.SpecialClassDAO;
import com.wisewin.backend.entity.bo.SpecialClassBO;
import org.apache.ibatis.annotations.Param;
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
    public boolean delSpecialClassById(Integer[] idArr,String status){
        return specialClassDAO.delSpecialClassById(idArr,status)>0;
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
    public List<SpecialClassBO> selectSpecialClassBO(String status){
        return specialClassDAO.selectSpecialClassBO(status);
    }

}
