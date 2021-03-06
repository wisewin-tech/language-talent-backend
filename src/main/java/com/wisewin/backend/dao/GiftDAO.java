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

    //总数
    int selectCount(@Param("batchNumber") String batchNumber,@Param("title")String title,@Param("cardnumber")String cardnumber,@Param("value")Integer value,@Param("status")String status);

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


    List<GiftBO> queryGifByBatch(Long batch);

    /**
     * 查询该批次号下未兑换的礼品卡数量
     */
    int countBatch(Long batch);

    /**
     * 查询是否有已使用
     * @param ids
     */
    int useCount(@Param("ids")List<Integer> ids);

    /**
     * 批量修改时间
     * @param map
     */
    void updateDate(Map<String, Object> map);
}
