package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.NoticeService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 后买须知
 */
@Controller
@RequestMapping("/notice")
public class NoticeController extends BaseCotroller {

    @Resource
    private NoticeService noticeService;

    /**
     *  查询购买须知
     */
    @RequestMapping("/queryNotice")
    public void queryNotice(HttpServletRequest request,HttpServletResponse response){
        String notice = noticeService.queryNotice();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(notice));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  修改所有购买须知
     */
    @RequestMapping("/updateNotice")
    public void updateNotice(HttpServletRequest request,HttpServletResponse response,String notice){
        if(StringUtils.isEmpty(notice)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        noticeService.updateNotice(notice);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        super.safeJsonPrint(response, json);
        return;
    }


}
