package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.RecordBO;
import com.wisewin.backend.entity.param.RecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RecordDAO {

    /**
     * 查看用户咖豆积分礼品卡的增减记录
     * 用户id 时间 类型 页码
     */
   List<RecordBO> queryRecordById(RecordParam recordParam);

    /**
     * 查看用户咖豆积分礼品卡的增减记录总数
     */
    Integer queryRecordByIdCount(RecordParam recordParam);




}
