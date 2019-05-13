package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.bo.GiftRecordBO;
import com.wisewin.backend.entity.param.GiftParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GiftRecordDAO {
    /**
     * 查询
     * @return
     */
    List<GiftRecordBO> selectAll(Map<String, Object> map);
    //查询用户礼品卡记录的数量
    Integer selectCount(Map<String, Object> map);

}
