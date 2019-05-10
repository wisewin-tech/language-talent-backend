package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.LanguagepurposeParam;
import com.wisewin.backend.service.LanguagepurposeService;
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

    /**
     * 添加
     * 添加用户喜欢哪个目的
     *   Integer purposeId; //目的 id
     *   Integer userId; //用户iD
     */
    @RequestMapping("/addLanguagepurpose")
    public void addLanguagepurpose(HttpServletRequest request, HttpServletResponse response, LanguagepurposeParam param){

        if (param.getPurposeId().equals("") || param.getUserId().equals("")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }

        boolean addLanguagepurposejson=languagepurposeService.getaddLanguagepurpose(param.getPurposeId(),param.getUserId());

        if (addLanguagepurposejson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }
}
