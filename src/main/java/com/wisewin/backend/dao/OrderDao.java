package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.LanguageBO;
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
     * 根据课程id 查出购买这个课程的用户id
     */
    public List<Integer> queryCoursesById(@Param("coursesId")Integer coursesId);

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
    public List<OrderBO> queryOrderByCond(@Param("orderParam") OrderParam orderParam,@Param("list") List<Integer> list);

    /**
     * 按时间段 手机号 订单号 查询所有订单的用户id
     */
    public Integer queryOrderByCondCount(@Param("orderParam")OrderParam orderParam,@Param("list") List<Integer> list);

    /**
     * 查询管理员管理的语言id
     * @param adminId
     * @return
     */
    List<Integer> queryAdminLanguage(Integer adminId);

    /**
     * 查询语言下面的课程id
     */
    List<Integer> queryLc(@Param("list") List<Integer> list);

    /**
     * 查询语言下的所有语言name id
     * @return
     */
    List<LanguageBO> queryLangBO();


    /**
     * 删除角色语言id
     * @param roleId
     */
    void deleteRoleLanguage(Integer roleId);

    /**
     * 添加角色语言id
     * @param roleId
     * @param languageId
     */
    void insertRoleLanguage(@Param("roleId")Integer roleId,@Param("languageId")int[] languageId);
}
