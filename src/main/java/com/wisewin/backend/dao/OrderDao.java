package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.OrderBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单
 * */
public interface OrderDao {

    /**
     * 根据用户的id查看他的订单，也就是语言订单
     * 语言订单里包括这个订单的多个课程
     */
    public List<OrderBO> queryOrderById(@Param("id")Integer id);
}
