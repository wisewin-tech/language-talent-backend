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
    public void queryOrderById(HttpServletRequest request, HttpServletResponse response,Integer id){
        if(id==null||id==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        List<OrderBO> orderBOS = orderService.queryOrderById(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(orderBOS));

        super.safeJsonPrint(response,json);
    }

    /**
     * 根据订单号 手机号 购买时间段 查看所有的订单记录
     */
    @RequestMapping("queryOrderByCond")
    public void queryOrderByCond(HttpServletRequest request, HttpServletResponse response, OrderParam orderParam){
        if(orderParam.getPageNo()==null||orderParam.getPageNo()==0||orderParam.getPageSize()==null||orderParam.getPageSize()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        QueryInfo queryInfo = getQueryInfo(orderParam.getPageNo(),orderParam.getPageSize());
        if(queryInfo != null){
            orderParam.setPageNo(queryInfo.getPageOffset());
            orderParam.setPageSize(queryInfo.getPageSize());
        }

        List<OrderBO> orderBOList = orderService.queryOrderByCond(orderParam);//记录信息

        List<OrderBO> countList=orderService.queryOrderByCondCount(orderParam);//记录总数

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("count",countList.size());
        map.put("orderBOList",orderBOList);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response,json);
    }

}
