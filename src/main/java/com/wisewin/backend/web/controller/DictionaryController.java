package com.wisewin.backend.web.controller;

import com.alibaba.fastjson.JSON;
import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.DictionaryBO;
import com.wisewin.backend.entity.bo.DictionaryValueBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.DictionaryService;
import com.wisewin.backend.service.DictionaryValueService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.Dictionary;
import java.util.List;


@Controller
@RequestMapping("/Dictionary")
public class DictionaryController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(DictionaryController.class);

    @Resource
    private DictionaryService dictionaryService;

    @Resource
    private DictionaryValueService dictionaryValueService;

    //获取字典
    @RequestMapping("/getDictionaryBOs")
    public void getDictionaryBOs(HttpServletResponse response, HttpServletRequest  request) throws InterruptedException {
        List<DictionaryBO> list= dictionaryService.getDictionaryBOs();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response, json);
    }

    //获取字典值
    @RequestMapping("/getDic")
    public void getDic(Integer kid,HttpServletResponse response, HttpServletRequest  request) throws InterruptedException {
        List<DictionaryValueBO> list=dictionaryValueService.getDIcs(kid);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response, json);
    }

    //增加字典值
    @RequestMapping("/addDic")
    public void addDic(DictionaryValueBO dictionaryValueBO,HttpServletResponse response, HttpServletRequest  request) throws InterruptedException {
        boolean bool=dictionaryValueService.addDic(dictionaryValueBO)>0;
        if(dictionaryValueBO==null||dictionaryValueBO.getKid()==null||dictionaryValueBO.getKid()==0||dictionaryValueBO.getValue()==null||dictionaryValueBO.getValue().equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        if(bool){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "添加失敗")) ;
            super.safeJsonPrint(response , result);
        }

    }



    //修改字典值
    @RequestMapping("/updDic")
    public void updDic(DictionaryValueBO dictionaryValueBO,HttpServletResponse response, HttpServletRequest  request) throws InterruptedException {
        boolean bool=dictionaryValueService.updDic(dictionaryValueBO)>0;
        if(bool){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }

    }

    //删除字典值
    @RequestMapping("/delDic")
    public void delDic(Integer id,HttpServletResponse response, HttpServletRequest  request) throws InterruptedException {
        if(id==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        boolean bool=dictionaryValueService.delDic(id)>0;
        if(bool){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "删除失敗")) ;
            super.safeJsonPrint(response , result);
        }

    }



}
