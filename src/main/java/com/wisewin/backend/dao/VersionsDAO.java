package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.VersionsBO;
import com.wisewin.backend.entity.param.VersionsParam;
import jdk.nashorn.internal.runtime.Version;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 版本
 */
public interface VersionsDAO {

    /**
     * 添加版本
     *   Integer versionsnum; //发版次数
     * String model; //版本号
     * String content; //内容
     * Integer adminId; //后台管理员id
     * Date createTime; //创建时间
     * Integer updateadminId; //后台管理员修改id
     * Date updateTime; //修改时间
     * String compatibility; //兼容版本
     */

    Integer addVersions(VersionsBO versionsBO);

    /**
     * 查询版本
     */
    List<VersionsParam> queryVersions(VersionsParam param);

    /**
     * 删除
     */
    Integer deleteVersions(@Param("idArr") String[] idArr);

    /**
     * 修改
     */
    Integer updateVersions(VersionsBO versionsBO);

}
