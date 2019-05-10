package com.wisewin.backend.service;

import com.wisewin.backend.dao.BannerDAO;
import com.wisewin.backend.entity.bo.BannerBO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("BannerService")
@Transactional
public class BannerService {

    @Resource
    BannerDAO bannerDAO;

    /**
     * 按状态查询
     */
    public List<BannerBO> queryBannerAllOrById(String status){
       return bannerDAO.queryBannerAllOrById(status);
    }


    /**
     * 上下架一条Banner
     */
    public boolean deleteBanner(String bannerId,String status,Integer id){
        return bannerDAO.deleteBanner(bannerId,status,id)>0;
    }

    /**
     * 物理删除Banner
     */
    public boolean removeBanner(String bannerId){
        return bannerDAO.removeBanner(bannerId)>0;
    }

    /**
     * 添加一条Banner
     */
    public boolean addBanner(BannerBO bannerBO){
        return bannerDAO.addBanner(bannerBO)>0;
    }

    /**
     * 修改一条Banner
     */
    public boolean updateBanner(BannerBO bannerBO){
        return bannerDAO.updateBanner(bannerBO)>0;
    }

}
