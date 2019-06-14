package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.DictionaryBO;

import java.util.List;

//字典
public interface DictionaryDAO {

    //查询
    List<DictionaryBO> getDictionaryBOs();
}
