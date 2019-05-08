package com.wisewin.backend.service;

import com.wisewin.backend.dao.DiscoverDAO;
import com.wisewin.backend.entity.bo.DiscoverBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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

    public void updateDiscover(DiscoverBO discoverBO){
        discoverDAO.updateDiscover(discoverBO);
    }

    public DiscoverBO queryDiscover(String id){
     return    discoverDAO.queryDiscoverBO(id);
    }


    public Integer countDiscover(Map<String,Object> map){
        return discoverDAO.countDiscover(map);
    }

}
