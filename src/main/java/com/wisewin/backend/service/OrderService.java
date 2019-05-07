package com.wisewin.backend.service;

import com.wisewin.backend.dao.OrderDao;
import com.wisewin.backend.entity.bo.OrderBO;
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

    /**
     * 根据用户查订单，订单中包括多个课程
     * */
    @Resource
    OrderDao orderDao;
    public List<OrderBO> queryOrderById(Integer id){
        return orderDao.queryOrderById(id);
    }
}
