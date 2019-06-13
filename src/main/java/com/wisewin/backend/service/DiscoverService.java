package com.wisewin.backend.service;

import com.wisewin.backend.dao.DiscoverDAO;
import com.wisewin.backend.entity.bo.DiscoverBO;
import com.wisewin.backend.pop.SystemConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彬 on 2019/5/5.
 */
@Transactional
@Service("discoverService")
public class DiscoverService {

    @Resource
    private DiscoverDAO discoverDAO;

    public List<DiscoverBO> queryListDiscoverBO(Map<String,Object> map){
        return discoverDAO.queryListDiscoverBO(map);
    }

    public void updateDiscoverbyShows(Integer[] idArr) {
          discoverDAO.updateDiscoverbyShows(idArr);
    }

    public void updateDiscoverbySticks(Integer[] idArr,String stick) {
        discoverDAO.updateDiscoverbySticks(idArr,stick);
    }

    public DiscoverBO queryDiscover(String id){
     return    discoverDAO.queryDiscoverBO(id);
    }

    public Integer countDiscover(Map<String,Object> map){
        return discoverDAO.countDiscover(map);
    }

    /**
     * 修改 新闻类型 Journalism
     */
    public void  updateJournalism(DiscoverBO discoverBO){
        discoverDAO.updateJournalism(discoverBO);
    }

    /**
     * 修改 视频类型 curriculum
     */
    public  void  updateCurriculum(DiscoverBO discoverBO){
        discoverDAO.updateCurriculum(discoverBO);
    }

    /**
     * 修改 线下活动类型 activity
     */
    public  void  updateActivity(DiscoverBO discoverBO){
        discoverDAO.updateActivity(discoverBO);
    }

    /**
     * 添加 新闻类型 Journalism
     */
    public void insertJournalism(DiscoverBO discoverBO){
        discoverBO.setBrowse(0);
        discoverBO.setLikenum(0);
        discoverBO.setShow("yes");
        discoverDAO.insertJournalism(discoverBO);
        String url= SystemConfig.getString("domain_name")+"/discover.html?id="+discoverBO.getId();
        discoverDAO.insetskip(discoverBO.getId(),url);
        //discoverDAO.updateActivity(discoverBO);
    }

    /**
     * 添加 视频类型 curriculum
     */
    public  void  insertCurriculum(DiscoverBO discoverBO){
        discoverBO.setBrowse(0);
        discoverBO.setLikenum(0);
        discoverBO.setShow("yes");
        discoverDAO.insertCurriculum(discoverBO);
        String url= SystemConfig.getString("domain_name")+"/discover.html?id="+discoverBO.getId();
        discoverDAO.insetskip(discoverBO.getId(),url);
    }

    /**
     * 添加 线下活动类型 activity
     */
    public void  insertActivity(DiscoverBO discoverBO){

        discoverBO.setBrowse(0);
        discoverBO.setLikenum(0);
        discoverBO.setParticipation(0);
        discoverBO.setShow("yes");
        discoverBO.setStick("no");
        //discoverBO.setSkip( SystemConfig.getString("domain_name")+"/activity.html?id="+discoverBO.getId());
        //discoverBO.setSkip(url);
        discoverDAO.insertActivity(discoverBO);
        String url= SystemConfig.getString("domain_name")+"/activity.html?id="+discoverBO.getId();
        discoverDAO.insetskip(discoverBO.getId(),url);
       //discoverDAO.updateActivity(discoverBO);
    }

}
