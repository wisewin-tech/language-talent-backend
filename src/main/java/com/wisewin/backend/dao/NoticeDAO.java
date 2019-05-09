package com.wisewin.backend.dao;


public interface NoticeDAO {

    /**
     * 查询购买须知
     */
    String queryNotice();

    /**
     *添加购买须知
     */
     void addNotice(String notice);

    /**
     * 查询购买须知数量
     */
    int queryCount();

    /**
     * 修改购买须知
     */
    int updateNotice(String notice);

}
