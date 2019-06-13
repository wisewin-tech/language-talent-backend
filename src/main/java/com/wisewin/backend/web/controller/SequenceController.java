package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StsUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/sequence")
public class SequenceController extends BaseCotroller {

    @Resource
    private LogService logService;

    /**
     *  获取和播放凭证
     * @param request
     * @param response
     */
    @RequestMapping(value = "/get" , method = RequestMethod.POST)
    public void get(HttpServletRequest request,HttpServletResponse response) {
        AdminBO loginUser = super.getLoginAdmin(request);
        logService.startController(loginUser,request,null);
        if(loginUser==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000021"));
            logService.end("sequence/get",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("StsUtil.getStsMessage",loginUser.getId().toString());
        Map<String, String> stsMessage = StsUtil.getStsMessage(loginUser.getId().toString()+"AdminPaly");
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(stsMessage));
        logService.end("sequence/get",json);
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  获取OSS临时凭证
      */
 /*
    @RequestMapping(value = "/getStsOss" , method = RequestMethod.POST)
    public void getOssSts(HttpServletRequest request,HttpServletResponse response) {
        Map<String, String> stsMessage = StsUtil.getStsOss(request.getSession().getId());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(stsMessage));
        super.safeJsonPrint(response, json);
        return;
    }*/



}
