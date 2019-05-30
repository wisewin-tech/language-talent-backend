package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.SystemoperationBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.SystemoperationService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.dates.DateUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/Systemoperation")
public class SystemoperationController extends BaseCotroller {


    @Resource
    private SystemoperationService systemoperationService;
/**
 * 显示
 *   Integer id; //系统操作id
 * Integer adminId; //后台id
 * String content; //内容
 *  String contenttype; //内容类型
 * String operationtype; //操作类型(增删改查)
 *  Date soReleasetime; //操作时间
 *  Integer  page; //页数
 * Integer strip; //条数
 */

@RequestMapping("/querySystemoperation")
    public void querySystemoperation(HttpServletRequest request, HttpServletResponse response,Integer id,Integer adminId,String content,String contenttype,
                                     String operationtype,String soReleasetime,Integer  page,Integer strip){

        if (page==null || strip==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        List<SystemoperationBO> list=systemoperationService.getquerySystemoperation(id,adminId,content,contenttype,operationtype, DateUtil.getDate(soReleasetime),page,strip);
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response,json);
        return;

}
}
