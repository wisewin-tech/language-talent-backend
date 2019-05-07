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
@Service("RecordService")
@Transactional
public class RecordService {

    @Resource
    RecordDAO recordDAO;
    public List<RecordBO> queryRecordById(RecordParam recordParam){
        return recordDAO.queryRecordById(recordParam);
    }

    public Integer queryRecordByIdCount(RecordParam recordParam){
        return recordDAO.queryRecordByIdCount(recordParam);
    }
}
