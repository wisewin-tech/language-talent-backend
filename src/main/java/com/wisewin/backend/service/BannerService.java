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
     * 查询一条或者多条Banner信息
     */
    public List<BannerBO> queryBannerAllOrById(Integer id){
       return bannerDAO.queryBannerAllOrById(id);
    }


    /**
     * 删除一条Banner
     */
    public boolean deleteBanner(Integer id){
        return bannerDAO.deleteBanner(id)>0;
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
