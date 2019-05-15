package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.bo.GiftRecordBO;
import com.wisewin.backend.entity.bo.GiftRecordResultBO;
import com.wisewin.backend.entity.param.GiftParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GiftRecordDAO {
    /**
     * 查询
     * @return
     */
    List<GiftRecordResultBO> selectAll(Map<String, Object> map);

}
