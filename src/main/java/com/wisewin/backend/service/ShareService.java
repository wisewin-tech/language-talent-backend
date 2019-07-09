package com.wisewin.backend.service;

import com.wisewin.backend.dao.ShareDAO;
import com.wisewin.backend.entity.bo.ShareBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service("ShareService")
@Transactional
public class ShareService {
    @Resource
    private ShareDAO shareDAO;



    /**
     * 查询分享图
     */
    public  List<ShareBO> queryShare(){
        return shareDAO.queryShare();
    }

    /**
     * 修改分享图
     */
    public void updateShare(ShareBO shareBO){
        shareDAO.updateShare(shareBO);
    }


}
