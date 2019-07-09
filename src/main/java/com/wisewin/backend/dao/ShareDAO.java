package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.ShareBO;

import java.util.List;

public interface ShareDAO {

    /**
     * 查询分享图
     */
    List<ShareBO> queryShare();

    /**
     * 修改分享图
     */
    void updateShare(ShareBO shareBO);

}
