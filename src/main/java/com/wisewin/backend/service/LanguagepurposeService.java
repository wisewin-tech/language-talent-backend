package com.wisewin.backend.service;

import com.wisewin.backend.dao.LanguagepurposeDAO;
import com.wisewin.backend.entity.bo.LanguagepurposeBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("LanguagepurposeService")
@Transactional
public class LanguagepurposeService {

    @Resource
    private LanguagepurposeDAO languagepurposeDAO;

    /**
     * 添加
     * 添加用户喜欢哪个目的
     *   Integer purposeId; //目的 id
     *   Integer userId; //用户iD
     */
    public boolean getaddLanguagepurpose(Integer purposeId,Integer userId){

        LanguagepurposeBO languagepurposeBO=new LanguagepurposeBO(purposeId,userId);
        return languagepurposeDAO.addLanguagepurpose(languagepurposeBO)>0;
    }
}
