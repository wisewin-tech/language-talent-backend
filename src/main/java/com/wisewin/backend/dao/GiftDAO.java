package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.param.GiftParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GiftDAO {
    /**
     * 查询
     * @return
     */
    List<GiftBO> selectAll(Map<String, Object> map);
    Integer selectCount(Map<String, Object> map);

    /**
     * 添加
     * @param list
     */
    void addGift(@Param("list")List<GiftParam> list);

    /**
     * 修改
     * @param giftParam
     */
    void updateGift(GiftParam giftParam);

    /**
     * 批量删除
     * @param idArr
     */
    Integer frostGift(@Param("idArr") Integer[] idArr);
    Integer unfreezeGift(@Param("idArr") Integer[] idArr);



}
