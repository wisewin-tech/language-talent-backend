package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.BannerBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO {
    /**
     * 通过手机号查找管理员信息
     *
     * @param mobile
     * @return UserDO
     */
    AdminBO queryAdminInfoByMobile(String mobile);

    /**
     * 查看一条或者多条首页信息 轮播图 要跳转的地址等等
     * @return BannerBO
     */
    List<BannerBO> queryBannerAllOrById(@Param("status")String status);


    /**
     * 删除一条Banner
     */
    Integer deleteBanner(@Param("bannerId") String bannerId,@Param("status")String status,@Param("id") Integer id);

    /**
     * 添加一条Banner
     */
    Integer addBanner(BannerBO bannerBO);

    /**
     * 修改一条Banner
     */
    Integer updateBanner(BannerBO bannerBO);
}
