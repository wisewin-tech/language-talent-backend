package com.wisewin.backend.service;

import com.wisewin.backend.dao.OrderDao;
import com.wisewin.backend.entity.bo.OrderBO;
import com.wisewin.backend.entity.bo.OrderCoursesBO;
import com.wisewin.backend.entity.param.OrderParam;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 *  订单
 */
@Service("OrderService")
@Transactional
public class OrderService {

    @Resource
    OrderDao orderDao;

    /**
     * 根据用户查订单，订单中包括多个课程
     * */
    public List<OrderBO> queryOrderById(Integer id){
        List<OrderBO> orderList=orderDao.queryOrderById(id);
        for(int i=0;i<orderList.size();i++){
            List<OrderCoursesBO> orderCoursesBOList=
                    orderDao.queryOrderCoursesByOrderId(new Integer(orderList.get(i).getOrderNumber()));
            orderList.get(i).setOrderCoursesBOList(orderCoursesBOList);
        }
        return orderList;
    }

    /**
     * 按时间段 手机号 订单号 分页查询所有订单
     */
    public List<OrderBO> queryOrderByCond(OrderParam orderParam){
        return orderDao.queryOrderByCond(orderParam);
    }

    /**
     * 按时间段 手机号 订单号 分页查询所有订单总数
     */
    public List<OrderBO> queryOrderByCondCount(OrderParam orderParam){
        return orderDao.queryOrderByCondCount(orderParam);
    }
}
