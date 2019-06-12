package com.wisewin.backend.service;

import com.wisewin.backend.dao.OrderDao;
import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.OrderBO;
import com.wisewin.backend.entity.bo.OrderCoursesBO;
import com.wisewin.backend.entity.param.OrderParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  订单
 */
@Service("OrderService")
@Transactional
public class OrderService {

    @Resource
    OrderDao orderDao;

    @Resource
    UserDAO userDAO;

    /**
     * 根据课程id 查出购买这个课程的用户id
     */
    public List<Integer> queryCoursesById(Integer coursesId){
        return orderDao.queryCoursesById(coursesId);
    }

    /**
     * 根据用户查订单，订单中包括多个课程
     * */
    public List<OrderBO> queryOrderById(Integer id,Integer pageNo,Integer pageSize){
        List<OrderBO> orderList=orderDao.queryOrderById(id,pageNo,pageSize);
        for(int i=0;i<orderList.size();i++){
            List<OrderCoursesBO> orderCoursesBOList=
                    orderDao.queryOrderCoursesByOrderId(new Integer(orderList.get(i).getOrderNumber()));
            orderList.get(i).setOrderCoursesBOList(orderCoursesBOList);
        }
        return orderList;
    }
    /**
     * 根据用户的id查看他的订单总数
     */
    public Integer queryOrderByIdCount(Integer id){
        return orderDao.queryOrderByIdCount(id);
    }


    /**
     * !!!!!!按时间段 手机号 订单号 分页查询所有订单
     * 所有订单总数
     */
    public Map<String,Object> queryOrderByCond(OrderParam orderParam){
        Map<String,Object> map=new HashMap<String, Object>();
        System.err.println("=====================yes1=================");
        List<OrderBO> orderBOList=orderDao.queryOrderByCond(orderParam);//全部总订单
        System.err.println("=====================yes2=================");
        Integer count=orderDao.queryOrderByCondCount(orderParam);//总订单总数
        System.err.println("=====================yes3=================");
        for (OrderBO order:orderBOList) {
            List<OrderCoursesBO> orderCoursesBOList=orderDao.queryOrderCoursesByOrderId(order.getId());
            order.setOrderCoursesBOList(orderCoursesBOList);
        }
        map.put("count",count);
        map.put("orderBOList",orderBOList);
        return map;
    }



}
