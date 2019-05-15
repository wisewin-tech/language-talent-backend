package com.wisewin.backend.service;

import com.wisewin.backend.dao.GiftDAO;
import com.wisewin.backend.dao.GiftRecordDAO;
import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.bo.GiftRecordBO;
import com.wisewin.backend.entity.bo.GiftRecordResultBO;
import com.wisewin.backend.entity.param.GiftParam;
import com.wisewin.backend.util.IDBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class GiftSRecordService {
        @Resource
        private GiftRecordDAO giftRecordDAO;

    /**
     * 查询所有礼品卡信息
     * @return
     */
    public List<GiftRecordResultBO> selectAll(Map<String,Object> map) {
       return giftRecordDAO.selectAll(map);
    }
}
