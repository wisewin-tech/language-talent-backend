package com.wisewin.backend.service;

import com.wisewin.backend.dao.OrderDao;
import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.LanguageBO;
import com.wisewin.backend.entity.bo.OrderBO;
import com.wisewin.backend.entity.bo.OrderCoursesBO;
import com.wisewin.backend.entity.dto.LgDTO;
import com.wisewin.backend.entity.param.OrderParam;
import org.apache.ibatis.annotations.Lang;
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
    public Map<String,Object> queryOrderByCond(OrderParam orderParam,Integer adminId){
        Map<String,Object> map=new HashMap<String, Object>();
        //查询管理员管理的语言id
        List<Integer> lst =  orderDao.queryAdminLanguage(adminId);
        if(lst.size() > 0 && lst != null){
            List<Integer> lt = orderDao.queryLc(lst);
            lst.addAll(lt);
            System.err.println(lst);
        }

        if(lst.size() <= 0 || lst == null){
            map.put("count",0);
            map.put("orderBOList","");
            return map;
        }

        List<OrderBO> orderBOList = orderDao.queryOrderByCond(orderParam,lst);//全部总订单
        Integer count=orderDao.queryOrderByCondCount(orderParam,lst);//总订单总数
        for (OrderBO order:orderBOList) {
            System.err.println(order.getLanguageName());
            List<OrderCoursesBO> orderCoursesBOList=orderDao.queryOrderCoursesByOrderId(order.getId());
            order.setOrderCoursesBOList(orderCoursesBOList);
        }
        map.put("count",count);
        map.put("orderBOList",orderBOList);
        return map;
    }

    public List<LgDTO> queryLg() {
        List<LanguageBO> lst = orderDao.queryLangBO();
        List<LgDTO> lt = new ArrayList<>();
        if (lst.size() > 0 && lst != null) {
            for (int i = 0; i < lst.size(); i++) {
                LgDTO lgDTO = new LgDTO();
                lgDTO.setId(lst.get(i).getId());
                lgDTO.setLanguageName(lst.get(i).getLanguageName());
                lt.add(lgDTO);
            }
        }
        return lt;
    }

    public void insertRoleLanguage(Integer roleId, Integer[] languageId){
     if(languageId.length > 0 && languageId != null){
         orderDao.deleteRoleLanguage(roleId);
         orderDao.insertRoleLanguage(roleId,languageId);
         return;
     }
        orderDao.deleteRoleLanguage(roleId);
        return;
    }

    public void deleteRoleLanguage(Integer roleId){
        orderDao.deleteRoleLanguage(roleId);
        return;
    }
}
