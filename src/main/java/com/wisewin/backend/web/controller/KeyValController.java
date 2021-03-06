package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.KeyValuesBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.KeyValService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置表
 */
@Controller
@RequestMapping("/keyVal")
public class KeyValController extends BaseCotroller {
    @Resource
    KeyValService keyValService;
    @Resource
    private LogService logService;

    /**
     *
     * @param key
     * @param val
     * @param response
     * @param request
     */
    @RequestMapping("/selectAll")
    public void selectAll(String key,String val,HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);

        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        //把查询条件放入map中
        condition.put("key",key);
        condition.put("val",val);
        //把带有条件的查询结果集放入map中
        logService.call("keyValService.selectAll",condition);
        List<KeyValuesBO> list=keyValService.selectAll(condition);
        logService.result(list);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        logService.end("keyVal/selectAll",json);
        super.safeJsonPrint(response, json);
    }
    /**
     * 修改val值
     * @param id 配置表id
     * @param values 配置的属性值
     * @param comment 备注
     * @param response
     * @param request
     */
    @RequestMapping("/updateVal")
    public void updateVal(Integer id,Integer values,String comment,HttpServletResponse response, HttpServletRequest request) {
        AdminBO adminBO = super.getLoginAdmin(request);

        //验证管理员是否登录
        if (adminBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002")) ;
            logService.end("keyVal/updateVal",result);
            super.safeJsonPrint(response, result);
            return;
        }
        //参数非空验证
        if(id==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
            logService.end("keyVal/updateVal",result);
            super.safeJsonPrint(response, result);
            return;
        }
        Map<String,Object> map=new HashMap<String, Object>();
        //把要修改的内容userId,val,comment以及条件id放入map中
        map.put("id",id);
        map.put("userId",adminBO.getId());
        map.put("val",values);
        map.put("comment",comment);
        logService.call("keyValService.updateVal",map);
        keyValService.updateVal(map);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        logService.end("keyVal/updateVal",json);
        super.safeJsonPrint(response, json);
    }

    /**
     * 添加
     * @param key
     * @param values
     * @param response
     * @param request
     */
    public void addKeyVal(String key,String values,HttpServletResponse response, HttpServletRequest request) {

        keyValService.addKeyVal( key, values);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
        super.safeJsonPrint(response, json);
    }

    /**
     * 删除
     * @param id
     * @param response
     * @param request
     */
    public void deleteKey(Integer id,HttpServletResponse response, HttpServletRequest request) {

        keyValService.deleteKey(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
        super.safeJsonPrint(response, json);
    }




}
