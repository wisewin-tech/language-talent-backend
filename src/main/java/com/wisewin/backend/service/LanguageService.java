package com.wisewin.backend.service;

import com.wisewin.backend.dao.LanguageDAO;
import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.LanguageBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 语言
 */
@Service("LanguageService")
@Transactional
public class LanguageService {
    @Resource
    private LanguageDAO  languageDAO;

    /**
     * 查询所有语言
     *  param languageName 语言名称
     *  pram status  状态上/下架
     *  pram hotOrNot 是否热门语言
     *  pram  preference  当前时间 (特惠中)
     */
    public List<LanguageBO> queryLanguageList(String languageName, String status, String hotOrNot, String preference){
        Map<String,Object>  queryMap=new HashMap<String, Object>();
        queryMap.put("languageName",languageName);
        queryMap.put("status",status);
        queryMap.put("hotOrNot",hotOrNot);
        if(preference!=null)
             queryMap.put("preference",new Date());
        return languageDAO.queryLanguageList(queryMap);
    }


}
