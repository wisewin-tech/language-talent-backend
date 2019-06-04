package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.VersionsBO;

import java.util.List;

/**
 * 版本
 */
public interface VersionsDAO {

    /**
     * 添加版本
     * */
    Integer addVersions(VersionsBO versionsBO);

    /**
     * 查询版本
     */
    List<VersionsBO> queryVersions();

    /**
     * 删除
     */
    Integer deleteVersions(Integer vid);


}
