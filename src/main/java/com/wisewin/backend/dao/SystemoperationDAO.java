package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.SystemoperationBO;

import java.util.List;

/**
 * 系统操作
 */
public interface SystemoperationDAO {

    /**
     * 添加
     *  Integer adminId; //后台id
     * String content; //内容
     * String contenttype; //内容类型
     * String operationtype; //操作类型(增删改查)
     * Date soReleasetime; //操作时间
     */
    Integer addSystemoperation(SystemoperationBO systemoperationBO);

    /**
     * 显示
     *   Integer id; //系统操作id
     * Integer adminId; //后台id
     * String content; //内容
     *  String contenttype; //内容类型
     * String operationtype; //操作类型(增删改查)
     *  Date soReleasetime; //操作时间
     */
    List<SystemoperationBO> querySystemoperation(SystemoperationBO systemoperationBO);


}
