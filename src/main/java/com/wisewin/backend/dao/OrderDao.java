package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.OrderBO;
import com.wisewin.backend.entity.bo.OrderCoursesBO;
import com.wisewin.backend.entity.param.OrderParam;
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
    public List<OrderBO> queryOrderById(@Param("id")Integer id,@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize);

    /**
     * 语言订单里包括这个订单下的子课程
     */
    public List<OrderCoursesBO> queryOrderCoursesByOrderId(@Param("orderId")Integer orderId);

    /**
     * 根据用户的id查看他的订单总数
     */
    public Integer queryOrderByIdCount(@Param("id")Integer id);


    /**
     * 按时间段 手机号 订单号 分页查询所有订单
     */
    public List<OrderBO> queryOrderByCond(OrderParam orderParam);

    /**
     * 按时间段 手机号 订单号 查询所有订单的用户id
     */
    public List<Integer> queryOrderByCondCount(OrderParam orderParam);
}
