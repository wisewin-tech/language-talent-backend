package com.wisewin.backend.web.controller;

import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.RecordBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.RecordService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.dates.DateUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/Record")
public class RecordController extends BaseCotroller {


    @Resource
    private RecordService recordService;

    @RequestMapping("queryRecordById")
    public void queryRecordById(HttpServletResponse response,HttpServletRequest request,Integer id,String source,String afterTime,String beforeTime){
        if(id==0||id==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        List<RecordBO> recordBOList = recordService.queryRecordById(id,source,DateUtil.gainDate(afterTime),DateUtil.gainDate(beforeTime));
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(recordBOList));
        super.safeJsonPrint(response,json);
    }
}
