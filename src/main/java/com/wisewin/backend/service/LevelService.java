package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.LanguageConstants;
import com.wisewin.backend.dao.LevelDAO;
import com.wisewin.backend.entity.bo.LevelBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  级别
 */
@Service("LeavelService")
@Transactional
public class LevelService {
    @Resource
    private LevelDAO leavelDAO;


    /**
     * 查询级别
     * param  courseId  课时id
     * @return
     */
    public  List<LevelBO> queryLeavelList(Map<String,Object> map){
        return leavelDAO.queryLeavelList(map);
    }

    /**
     * 查询级别数量
     * param courseId  课时id
     * @return
     */
    public Integer  queryLeavelCount(Map<String,Object> map){
        return leavelDAO.queryLeavelCount(map);
    }


    /**
     * 添加级别
     */
    public boolean addLeavel(LevelBO levelBO, Integer userId){
        levelBO.setCreateUserId(userId);
        levelBO.setCreateTime(new Date());
        return  leavelDAO.addLeavel(levelBO)>0;
    }


    /**
     *  修改级别
     */
    public boolean updateLeavel(LevelBO levelBO, Integer userId){
        levelBO.setUpdateUserId(userId);
        levelBO.setUpdateTime(new Date());
        return leavelDAO.updateLeavel(levelBO)>0;
    }


    /**
     *  删除级别
     * @param id
     * @return
     */
    public boolean deleteLeavel(Integer id) {
        LevelBO levelBO = leavelDAO.queryLeavelById(id);
        if(levelBO==null){
            return false;
        }
        if(levelBO.getStatus()!=null && levelBO.getStatus().equals(LanguageConstants.STATUS_PUTAWAY.getValue())){  //上架状态
            levelBO.setStatus(LanguageConstants.STATUS_SOLDOUT.getValue());
        }else{
            levelBO.setStatus(LanguageConstants.STATUS_PUTAWAY.getValue());
        }
        return leavelDAO.updateLeavel(levelBO) > 0;
    }

    /**
     * 查询级别
     *  param courseId  课程id
     */
    public List<LevelBO>  queryLeavelChoice(Integer courseId){
        return leavelDAO.queryLeavelChoice(courseId);
    }

}
