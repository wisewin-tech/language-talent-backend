package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.OrderBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.OrderParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.OrderService;
import com.wisewin.backend.util.JsonUtils;
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
 *
 * 订单
 * */
@Controller
@RequestMapping("/Order")
public class OrderController extends BaseCotroller {

    @Resource
    OrderService orderService;
    /**
     * 根据用户的id查看他的订单，也就是语言订单
     * 语言订单里包括这个订单的多个课程
     */
    @RequestMapping("queryOrderById")
    public void queryOrderById(HttpServletRequest request, HttpServletResponse response,Integer id,Integer pageNo,Integer pageSize){
        if(id==null||id==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        Map<String,Object> map=new HashMap<String, Object>();
        if(queryInfo != null){
            List<OrderBO> orderBOS = orderService.queryOrderById(id,queryInfo.getPageOffset(),queryInfo.getPageSize());
            Integer count=orderService.queryOrderByIdCount(id);
            map.put("orderBOS",orderBOS);
            map.put("count",count);
        }

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));

        super.safeJsonPrint(response,json);
    }

    /**
     * 根据订单号 手机号 购买时间段 查看所有的订单记录
     */
    @RequestMapping("queryOrderByCond")
    public void queryOrderByCond(HttpServletRequest request, HttpServletResponse response, OrderParam orderParam){
        QueryInfo queryInfo = getQueryInfo(orderParam.getPageNo(),orderParam.getPageSize());
        if(queryInfo != null){
            orderParam.setPageNo(queryInfo.getPageOffset());
            orderParam.setPageSize(queryInfo.getPageSize());
        }

        Map<String,Object> map = orderService.queryOrderByCond(orderParam);//记录信息

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response,json);
    }

    /**
     * 根据课程id 查出购买这个课程的userId
     */
}
