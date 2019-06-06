package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.LanguagepurposeParam;
import com.wisewin.backend.service.LanguagepurposeService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Languagepurpose")
public class LanguagepurposeController extends BaseCotroller {

    @Resource
    private LanguagepurposeService languagepurposeService;
    @Resource
    private LogService logService;

    /**
     * 添加
     * 添加用户喜欢哪个目的
     *   Integer purposeId; //目的 id
     *   Integer userId; //用户iD
     */
    @RequestMapping("/addLanguagepurpose")
    public void addLanguagepurpose(HttpServletRequest request, HttpServletResponse response, LanguagepurposeParam param){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);

        if (param.getPurposeId().equals("") || param.getUserId().equals("")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Languagepurpose/addLanguagepurpose",json);
            super.safeJsonPrint(response,json);
        }
        logService.call("languagepurposeService.getaddLanguagepurpose",param.getPurposeId(),param.getUserId());
        boolean addLanguagepurposejson=languagepurposeService.getaddLanguagepurpose(param.getPurposeId(),param.getUserId());
        logService.result(addLanguagepurposejson);
        if (addLanguagepurposejson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            logService.end("Languagepurpose/addLanguagepurpose",languagejson);
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("Languagepurpose/addLanguagepurpose",languagejson);
        super.safeHtmlPrint(response,languagejson);
        return;
    }
}
