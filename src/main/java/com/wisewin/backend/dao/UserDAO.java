package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.param.UserParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserDAO {



    /**
     * 添加用户信息
     * @param user
     */
    void insertUser(UserBO user);

    /**
     * 通过phone查询用户信息
     * @param phone
     * @return
     */
    UserBO selectByPhone(String phone);

    /**
     *修改用户信息
     * @param userParam
     * @return
     */
    void updateUser(UserParam userParam);

    /**
     * 手机号修改密码
     * @param phone
     * @param newPassword
     */
    void updatePassword(@Param("phone") String phone,
                        @Param("password") String newPassword);

    /**
     * 通过id查找用户user
     * @param id
     * @return
     */

    UserBO selectById(Integer id);

    /**
     * 通过手机号发送验证码修改密码
     * @param phone
     * @param newpassword
     */
    void changePassword(String phone, String newpassword);

    /**
     *
     * 通过用户名查询用户信息
     * @param name
     * @return
     */
    UserBO  selectUserByUsername(String name);

    /**
     *
     * 条件分页查询用户信息
     * @param map
     * @return
     */
    List<UserBO> selectUsers(Map<String,Object> map);

    /**
     *
     * 条件分页查询用户个数
     * @param map
     * @return
     */
    Integer selectUsersCount(Map<String,Object> map);


    /**
     *
     * 批量 逻辑删除用户
     * @param idArr
     * @return
     */
    Integer deleteUsersById(@Param("idArr") Integer[] idArr);

    /**
     *
     * 按用户id查看用户信息
     * @param id
     * @return
     */
    UserBO selectUsersById(@Param("id")Integer id);

    /**
     *
     * 按用户id修改用户信息
     * @param userParam
     * @return
     */
    Integer updateUsersById(@Param("userParam")UserParam userParam);




}
