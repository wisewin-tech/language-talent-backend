package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.CertificateBO;
import com.wisewin.backend.entity.bo.CertificateResultBO;

import java.util.List;
import java.util.Map;

public interface CertificateDAO {

    /**
     * 查询用户证书信息
     */
    List<CertificateResultBO> selectUser(Map<String,Object> map);
    /**
     * 查询用户证书信息
     */
    void updateSend(Map<String,Object> map);

    /**
     * 查询是否有用户购买记录
     * @param ceId 课程id
     * @return
     */
    int queryUserCount(Integer ceId);

    void addCertificate(CertificateResultBO certificateResultBO);
}
