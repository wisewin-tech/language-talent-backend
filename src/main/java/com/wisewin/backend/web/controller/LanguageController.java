package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.LanguageBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.LanguageParam;
import com.wisewin.backend.service.LanguageService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 *  语言
 */
@Controller
@RequestMapping("/Language")
public class LanguageController extends BaseCotroller {

    @Resource
    private LanguageService  languageService;

    /**
     * 查询所有语言
     *  param languageName 语言名称
     *  pram status  状态上/下架
     *  pram hotOrNot 是否热门语言
     *  pram  preference  当前时间 (特惠中)
     */
    @RequestMapping("/queryLanguageList")
     public void  queryLanguageList(HttpServletRequest request, HttpServletResponse response, LanguageParam  languageParam){

        List<LanguageBO> languageBOS = languageService.queryLanguageList(languageParam.getLanguageName(), languageParam.getStatus(), languageParam.getHotOrNot(), languageParam.getPreference());

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(languageBOS));
        super.safeJsonPrint(response, json);

    }





}
