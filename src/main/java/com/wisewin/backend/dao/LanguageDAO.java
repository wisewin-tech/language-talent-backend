package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.LanguageBO;

import java.util.List;
import java.util.Map;

/**
 * 语言
 */
public interface LanguageDAO {

    /**
     * 查询所有语言
     *  param languageName 语言名称
     *  pram status  状态上/下架
     *  pram hotOrNot 是否热门语言
     *  pram  preference  当前时间 (特惠中)
     */
    List<LanguageBO>  queryLanguageList(Map<String,Object> map);





}
