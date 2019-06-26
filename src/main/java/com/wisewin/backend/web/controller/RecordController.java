package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.RecordBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.RecordParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.RecordService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.dates.DateUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/Record")
public class RecordController extends BaseCotroller {


    @Resource
    private RecordService recordService;
    @Resource
    private LogService logService;

    @RequestMapping("queryRecordById")
    public void queryRecordById(HttpServletResponse response, HttpServletRequest request, RecordParam param){
        AdminBO loginAdmin= super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);
        if(param.getId()==null||param.getId()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Record/queryRecordById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(param.getPageNo(),param.getPageSize());
        if(queryInfo != null){
            param.setPageNo(queryInfo.getPageOffset());
            param.setPageSize(queryInfo.getPageSize());
        }
        logService.call("recordService.queryRecordById",param);
        List<RecordBO> recordBOList = recordService.queryRecordById(param);//记录信息
        logService.result(recordBOList);
        logService.call("recordService.queryRecordByIdCount",param);
        Integer count=recordService.queryRecordByIdCount(param);//记录总数
        logService.result(count);
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("count",count);
        map.put("recordBOList",recordBOList);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        logService.end("Record/queryRecordById",json);
        super.safeJsonPrint(response,json);
    }
}
