package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.GiftRecordResultBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.GiftRecordParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.GiftSRecordService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/giftRecord")
public class GiftRecordController extends BaseCotroller {
    @Resource
    GiftSRecordService giftSRecordService;

    /**
     * 查询所有
     * @param giftRecordParam
     * @param response
     * @param request
     */
    @RequestMapping("/selectAll")
    public void selectAll(Integer pageNo, Integer pageSize, GiftRecordParam giftRecordParam, HttpServletResponse response, HttpServletRequest request) {
        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if(queryInfo != null){
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        //把参数条件 放入map中
        condition.put("giftRecordParam",giftRecordParam);
        List<GiftRecordResultBO> giftRecordBOS = giftSRecordService.selectAll(condition);
        int size = giftRecordBOS.size();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("giftRecordBOS",giftRecordBOS);
        map.put("count",size);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response, json);
    }

}
