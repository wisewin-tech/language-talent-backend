package com.wisewin.backend.service;

import com.wisewin.backend.dao.SpecialClassDAO;
import com.wisewin.backend.dao.SpecialDAO;
import com.wisewin.backend.entity.bo.SpecialBO;
import com.wisewin.backend.entity.bo.SpecialClassBO;
import com.wisewin.backend.pop.SystemConfig;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("SpecialService")
@Transactional
public class SpecialService {

    @Resource
    SpecialDAO specialDAO;

    /**
     * 专题的增加
     * */
    public boolean addSpecial(SpecialBO specialBO){
        specialDAO.addSpecial(specialBO);
        String url= SystemConfig.getString("domain_name")+"/special.html?id="+specialBO.getId();
        specialBO.setSkip(url);
        return specialDAO.updateSpecialById(specialBO)>0;

    }

    /**
     * 专题的删除
     * */
    public boolean delSpecialById(String[] idArr,String status,Integer updateId){
        return specialDAO.delSpecialById(idArr,status,updateId)>0;
    }

    /**
     * 专题的修改
     * */
    public boolean updateSpecialById(SpecialBO specialBO){
        return specialDAO.updateSpecialById(specialBO)>0;
    }

    /**
     * 专题的查询
     * */
    public List<SpecialBO> selectSpecialBO(String status,Integer classId,Integer pageNo,Integer pageSize){
        return specialDAO.selectSpecialBO(status,classId,pageNo,pageSize);
    }

    /**
     * 专题分类 状态的查询数量
     * */
    public Integer selectSpecialBOCount(String status,Integer classId){
        return specialDAO.selectSpecialBOCount(status,classId);
    }

    /**
     * 查看单个专题
     * */
    public SpecialBO selectSpecialBOById(Integer id){
        return specialDAO.selectSpecialBOById(id);
    }

}
