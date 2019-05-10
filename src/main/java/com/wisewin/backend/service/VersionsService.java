package com.wisewin.backend.service;


import com.wisewin.backend.dao.VersionsDAO;
import com.wisewin.backend.entity.bo.VersionsBO;
import com.wisewin.backend.entity.param.VersionsParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("VersionsService")
@Transactional
public class VersionsService {

    @Resource
    private VersionsDAO versionsDAO;


    /**
     * 添加版本
     *   Integer versionsnum; //发版次数
     * String model; //版本号
     * String content; //内容
     * Integer adminId; //后台管理员id
     * Integer updateadminId; //后台管理员修改id
     * String compatibility; //兼容版本
     */
    public boolean getaddVersions(Integer versionsnum,String model,String content,Integer adminId,Integer updateadminId,String compatibility){
        VersionsBO versionsBO=new VersionsBO(versionsnum,model,content,adminId,updateadminId,compatibility);

        return versionsDAO.addVersions(versionsBO)>0;
    }


    /**
     * 查询版本
     */
    public List<VersionsParam> getqueryVersions(VersionsParam param){
        return  versionsDAO.queryVersions(param);
    }

    /**
     * 删除
     */
    public boolean getdeleteVersions(String[] id){
    return  versionsDAO.deleteVersions(id)>0;
    }

    /**
     * 修改
     */
    public boolean getupdateVersions(Integer id,Integer versionsnum,String model,String content,Integer updateadminId,String compatibility){
        VersionsBO versionsBO=new VersionsBO(id,versionsnum,model,content,updateadminId,compatibility);
        return  versionsDAO.updateVersions(versionsBO)>0;
    }
}
