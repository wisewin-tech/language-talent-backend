package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.KeyValuesBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.KeyValService;
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

@Controller
@RequestMapping("/keyVal")
public class KeyValController extends BaseCotroller {
    @Resource
    KeyValService keyValService;

    /**
     *
     * @param pageNo
     * @param pageSize
     * @param key
     * @param val
     * @param response
     * @param request
     */
    @RequestMapping("/selectAll")
    public void selectAll(Integer pageNo, Integer pageSize,String key,String val,HttpServletResponse response, HttpServletRequest request) {
        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if(queryInfo != null){
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        //把查询条件放入map中
        condition.put("key",key);
        condition.put("val",val);
        //把带有条件的查询结果集放入map中
        List<KeyValuesBO> list=keyValService.selectAll(condition);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response, json);
    }
    /**
     * 修改val值
     * @param id
     * @param val
     * @param response
     * @param request
     */
    @RequestMapping("/updateVal")
    public void updateVal(Integer id,String val,HttpServletResponse response, HttpServletRequest request) {
        //参数非空验证
        if(id==null|| StringUtils.isEmpty(val)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
            super.safeJsonPrint(response, result);
            return;
        }
        keyValService.updateVal(id,val);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
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
