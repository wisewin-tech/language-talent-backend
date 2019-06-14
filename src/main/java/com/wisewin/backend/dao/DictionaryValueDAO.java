package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.DictionaryBO;
import com.wisewin.backend.entity.bo.DictionaryValueBO;

import java.util.List;

//字典 对应的 字典值
public interface DictionaryValueDAO {

    //增
    Integer addDic(DictionaryValueBO dictionaryBO);
    //删
    Integer delDic(Integer id);

    //改
    Integer updDic(DictionaryValueBO dictionaryBO);
    //查
    List<DictionaryValueBO> getDIcs(Integer kid);
}
