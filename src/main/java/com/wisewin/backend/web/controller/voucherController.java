package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.util.voucherUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/voucher")
public class voucherController extends BaseCotroller {

    @Resource
    private LogService logService;
    /**
     * 获取视频上传凭证
     * @param request
     * @param response
     * @param title
     * @param fileName
     */
    @RequestMapping(value = "/getAddress" ,method = RequestMethod.POST)
    public void getAddress(HttpServletRequest  request, HttpServletResponse response,String title,String fileName){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,title,fileName);
        if(StringUtils.isEmpty(title) ||  StringUtils.isEmpty(fileName)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("voucher/getAddress",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("voucherUtil.getUploadAddress",title,fileName);
        Map<String, String> resultMap = voucherUtil.getUploadAddress(title, fileName);
        logService.result(resultMap);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        logService.end("voucher/getAddress",json);
        super.safeJsonPrint(response, json);
    }


    /**
     * 刷新视频上传凭证
     * @param request
     * @param response
     * @Param
     */
    @RequestMapping(value = "/refreshAddress" ,method = RequestMethod.POST)
    public void refreshAddress(HttpServletRequest  request, HttpServletResponse response,String videoId ){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,videoId);
        if(StringUtils.isEmpty(videoId)) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("voucher/refreshAddress",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("voucherUtil.refreshAddress",videoId);
        Map<String, String> resultMap = voucherUtil.refreshAddress(videoId);
        logService.result(resultMap);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        logService.end("voucher/refreshAddress",json);
        super.safeJsonPrint(response, json);
    }


}
