package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.DiscoverBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by 王彬 on 2019/5/5.
 */
public interface DiscoverDAO {
    /**
     * 发现列表
     */
    List<DiscoverBO> queryListDiscoverBO( Map<String,Object> map);

    /**
     * 批量删除
     * @param idArr
     * @return
     */
    void  updateDiscoverbyShows(Integer[] idArr);


    /**
     * 批量置顶
     * @param idArr
     */
    void updateDiscoverbySticks(@Param("idArr")Integer[] idArr, @Param("stick")String stick);

    /**
     * 修改
     */
    void updateDiscover(DiscoverBO discoverBO);

    /**
     * 修改单表查询
     */
    DiscoverBO queryDiscoverBO(String id);


    /**
     * 查询总条数
     */
    Integer countDiscover(Map<String,Object> map);

    /**
     * 添加
     */
    void insertDiscover(DiscoverBO discoverBO);
}
