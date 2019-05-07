package com.wisewin.backend.service;

import com.wisewin.backend.dao.SystemoperationDAO;
import com.wisewin.backend.entity.bo.SystemoperationBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 系统操作
 */
@Transactional
@Service("systemoperationService")
public class SystemoperationService {

    @Resource
    private SystemoperationDAO systemoperationDAO;


    /**
     * 添加
     *  Integer adminId; //后台id
     * String content; //内容
     * String contenttype; //内容类型   需要填写的内容的类型，如语言类型
     * String operationtype; //操作类型(增删改查)
     */
    public boolean getaddSystemoperation(Integer adminId,String content,String contenttype,String operationtype){
        SystemoperationBO systemoperationBO=new SystemoperationBO(adminId,content,contenttype,operationtype);
        return  systemoperationDAO.addSystemoperation(systemoperationBO)>0;
    }

    /**
     * 显示
     *   Integer id; //系统操作id
     * Integer adminId; //后台id
     * String content; //内容
     *  String contenttype; //内容类型
     * String operationtype; //操作类型(增删改查)
     *  Date soReleasetime; //操作时间
     *  Integer  page; //页数
     * Integer strip; //条数
     */
    public List<SystemoperationBO> getquerySystemoperation(Integer id,Integer adminId,String content,String contenttype,String operationtype,Date soReleasetime,
                                                           Integer  page,Integer strip){
        SystemoperationBO systemoperationBO=new SystemoperationBO(id,adminId,content,contenttype,operationtype,soReleasetime,page,strip);
        return  systemoperationDAO.querySystemoperation(systemoperationBO);
    }

}
