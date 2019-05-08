package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.LanguageBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;
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
    private LanguageService languageService;

    /**
     * 查询所有语言
     * param languageName 语言名称
     * pram status  状态上/下架
     * pram hotOrNot 是否热门语言
     * pram  preference  当前时间 (特惠中)
     */
    @RequestMapping("/queryLanguageList")
    public void queryLanguageList(HttpServletRequest request, HttpServletResponse response, LanguageParam languageParam) {
        List<LanguageBO> languageBOS = languageService.queryLanguageList(languageParam.getLanguageName(), languageParam.getStatus(), languageParam.getHotOrNot(), languageParam.getPreference());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(languageBOS));
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
    @RequestMapping("/addLanguage")
    public void queryLanguageList(HttpServletRequest request, HttpServletResponse response, LanguageBO languageParam) {
        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(languageParam.getLanguageName()==null || languageParam.getEnsignImageUrl()==null  || languageParam.getPrice()==null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        boolean flag = languageService.addLanguage(languageParam, loginAdmin.getId());
        if(flag){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;

    }


    /**
     *  语言修改
     */
    @RequestMapping("/updateLanguage")
    public void  updateLanguage(HttpServletRequest request,HttpServletResponse response,LanguageBO  languageParam){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        if(languageParam.getId()==null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        boolean flag = languageService.updateLanguage(languageParam, loginAdmin.getId());
        if(flag){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;

    }



    /**
     *  语言删除
     *  @param  id  语言id
     */
    @RequestMapping("/deleteLanguage")
    public void   deleteLanguage(HttpServletResponse response,HttpServletRequest  request,Integer id){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        if(id==null ){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        boolean flag = languageService.deleteLanguage(id, loginAdmin.getId());
        if(flag){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     * 选择语言查询
     */
    @RequestMapping("/queryLanguageChoice")
    public void queryLanguageChoice(HttpServletRequest request,HttpServletResponse response){
        List<LanguageChoiceBO> languageChoiceBOS = languageService.queryLanguageChoice();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(languageChoiceBOS));
        super.safeJsonPrint(response, json);
        return;
    }




}