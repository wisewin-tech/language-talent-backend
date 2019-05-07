package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.RecordBO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RecordDAO {

    /**
     * 查看用户咖豆积分礼品卡的增减记录
     * 时间
     * 咖豆或者积分或者礼品卡
     */
   List<RecordBO> queryRecordById(@Param("id")Integer id, @Param("source")String source, @Param("afterTime") Date afterTime, @Param("beforeTime")Date beforeTime);
}
