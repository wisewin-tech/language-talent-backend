package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.LanguagepurposeBO;

/**
 *  学习语言目久层
 *  Created by yyh on 2019/4/27
 *  相当于是记录用户喜欢哪个目的
 */
public interface LanguagepurposeDAO {

    /**
     * 添加
     * 添加用户喜欢哪个目的
     *   Integer purposeId; //目的 id
     *   Integer userId; //用户iD
     */
    Integer addLanguagepurpose(LanguagepurposeBO languagepurposeBO);


}
