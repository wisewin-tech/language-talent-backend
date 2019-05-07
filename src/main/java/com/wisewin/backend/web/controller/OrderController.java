package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.OrderBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.OrderService;
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

}
