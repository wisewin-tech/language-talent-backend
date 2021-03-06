package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.LanguageBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.LanguageParam;
import com.wisewin.backend.service.LanguageService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 *  语言
 */
@Controller
@RequestMapping("/Language")
public class LanguageController extends BaseCotroller {

    @Resource
    private LanguageService languageService;
    @Resource
    private LogService logService;

    /**
     * 查询所有语言
     * param languageName 语言名称
     * pram status  状态上/下架
     * pram hotOrNot 是否热门语言
     * pram  preference  当前时间 (特惠中)
     */
    @RequestMapping("/queryLanguageList")
    public void queryLanguageList(HttpServletRequest request, HttpServletResponse response, LanguageParam languageParam) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,languageParam);
        logService.call("languageService.queryLanguageList",languageParam.getLanguageName(), languageParam.getStatus(), languageParam.getHotOrNot(), languageParam.getPreference());
        List<LanguageBO> languageBOS = languageService.queryLanguageList(languageParam.getLanguageName(), languageParam.getStatus(), languageParam.getHotOrNot(), languageParam.getPreference());
        logService.result(languageBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(languageBOS));
        logService.end("Language/queryLanguageList",json);
        super.safeJsonPrint(response, json);
    }

    /**
     * 语言添加
     * languageName; //语言名称
     * status; //状态  putaway / soldout
     * foreignLanguageName; //外文名称
     * ensignImageUrl; //国旗图片路径
     * thumbnailImageUrl; //缩略图
     * popularSort; //热门排序
     * anguageLightspot; //语言亮点
     * videoPath; //视频路径
     * languageIntro; //语言简介
     * price; //价格
     * discountPrice; //特惠价
     * discountStartTime; //特惠开始时间
     * discountEndTime; //特惠结束时间
     * hotOrNot;//是否为热门 yes/no
     * createUserId; //创建人id
     * createTime; //创建时间
     * updateUserId; //修改人id
     * updateTime; //修改时间
     */
    @RequestMapping(value = "/addLanguage",method= RequestMethod.POST)
    public void queryLanguageList(HttpServletRequest request, HttpServletResponse response, LanguageBO languageParam) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,languageParam);
        if(StringUtils.isEmpty(languageParam.getLanguageName()) || languageParam.getEnsignImageUrl()==null  || languageParam.getPrice()==null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Language/addLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }
        if(new BigDecimal("0").compareTo(languageParam.getPrice())==1){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000013"));
            logService.end("Language/addLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }

        logService.call("languageService.addLanguage",languageParam,loginAdmin.getId());
        boolean flag = languageService.addLanguage(languageParam, loginAdmin.getId());
        logService.result(flag);
        if(flag){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            logService.end("Language/addLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("Language/addLanguage",json);
        super.safeJsonPrint(response, json);
        return;

    }


    /**
     *  语言修改
     */
    @RequestMapping(value = "/updateLanguage" ,method= RequestMethod.POST)
    public void  updateLanguage(HttpServletRequest request,HttpServletResponse response,LanguageBO  languageParam){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,languageParam);
        if(languageParam.getId()==null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Language/updateLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("languageService.updateLanguage",languageParam,loginAdmin.getId());
        boolean flag = languageService.updateLanguage(languageParam, loginAdmin.getId());
        logService.result(flag);
        if(flag){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            logService.end("Language/updateLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("Language/updateLanguage",json);
        super.safeJsonPrint(response, json);
        return;

    }



    /**
     *  语言删除
     *  @param  id  语言id
     */
    @RequestMapping(value = "/deleteLanguage",method= RequestMethod.POST)
    public void   deleteLanguage(HttpServletResponse response,HttpServletRequest  request,Integer id){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        if(id==null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("Language/deleteLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }
        logService.call("languageService.deleteLanguage",id, loginAdmin.getId());
        boolean flag = languageService.deleteLanguage(id, loginAdmin.getId());
        logService.result(flag);
        if(flag){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            logService.end("Language/deleteLanguage",json);
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        logService.end("Language/deleteLanguage",json);
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     * 选择语言查询
     */
    @RequestMapping(value = "/queryLanguageChoice",method= RequestMethod.POST)
    public void queryLanguageChoice(HttpServletRequest request,HttpServletResponse response){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,null);
        logService.call("languageService.queryLanguageChoice",null);
        List<LanguageChoiceBO> languageChoiceBOS = languageService.queryLanguageChoice();
        logService.result(languageChoiceBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(languageChoiceBOS));
        logService.end("Language/queryLanguageChoice",json);
        super.safeJsonPrint(response, json);
        return;
    }




}