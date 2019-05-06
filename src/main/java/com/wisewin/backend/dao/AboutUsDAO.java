package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.AboutUsBO;

public interface AboutUsDAO {

    /**
     * 查询关于我们
     * @return
     */
    AboutUsBO selectAbout();
    //查询表中数据是否为1
    Integer selectid();
    //修改信息
    void updateAboutUs(AboutUsBO aboutUsBO);
    //添加信息
    void insertAboutUs(AboutUsBO aboutUsBO);
}
