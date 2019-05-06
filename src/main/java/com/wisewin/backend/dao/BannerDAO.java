package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.AdminBO;

public interface BannerDAO {
    /**
     * 通过手机号查找管理员信息
     *
     * @param mobile
     * @return UserDO
     */
    AdminBO queryAdminInfoByMobile(String mobile);
}
