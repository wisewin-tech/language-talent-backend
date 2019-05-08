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
     * 按时间段 手机号 订单号 分页查询所有订单
     * 所有订单总数
     */
    public Map<String,Object> queryOrderByCond(OrderParam orderParam){
        Map<String,Object> map=new HashMap<String, Object>();
        List<OrderBO> orderBOList=orderDao.queryOrderByCond(orderParam);//没有手机号条件 分页查出的
        Integer count=orderDao.queryOrderByCondCount(orderParam);//没有分页的用户id 也就是总数
//        if(orderParam.getMobile()!=null&&orderParam.getMobile()!=""){
//            //筛选手机号
//            Integer id = userDAO.selectByPhone(orderParam.getMobile()).getId();//用户id
//            for (int i=0;i<orderBOList.size();i++){
//                if(!orderBOList.get(i).getUserId().equals(id)){//如果用户id不等于查出来的订单的id 就代表没有那个手机号的订单 则删除
//                    orderBOList.remove(i);
//                }
//            }
//            //筛选手机号计算总数
//            for (int i=0;i<idList.size();i++){
//                if(!idList.get(i).equals(id)){//如果用户id不等于查出来的订单的id 就代表没有那个手机号的订单 则删除
//                    idList.remove(i);
//                }
//            }
//        }
        map.put("count",count);
        map.put("orderBOList",orderBOList);
        return map;
    }


}
