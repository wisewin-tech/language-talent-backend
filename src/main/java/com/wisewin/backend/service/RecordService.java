package com.wisewin.backend.service;

import com.wisewin.backend.dao.RecordDAO;
import com.wisewin.backend.entity.bo.RecordBO;
import com.wisewin.backend.entity.param.RecordParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户记录
 * */
@Service("RecordService")
@Transactional
public class RecordService {

    @Resource
    RecordDAO recordDAO;

    /**
     * 查看用户咖豆积分礼品卡的增减记录
     * 用户id 时间 类型 页码
     */
    public List<RecordBO> queryRecordById(RecordParam recordParam){
        return recordDAO.queryRecordById(recordParam);
    }

    /**
     * 查看用户咖豆积分礼品卡的增减记录总数
     */
    public Integer queryRecordByIdCount(RecordParam recordParam){
        return recordDAO.queryRecordByIdCount(recordParam);
    }
}
