package com.wisewin.backend.service;

import com.wisewin.backend.dao.RecordDAO;
import com.wisewin.backend.entity.bo.RecordBO;
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
    public List<RecordBO> queryRecordById(Integer id, String source, Date afterTime, Date beforeTime){
        return recordDAO.queryRecordById(id,source,afterTime,beforeTime);
    }
}
