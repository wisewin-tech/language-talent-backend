package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.DictionaryBO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface DataTableDAO {

    /**
     * 查询次角色下的所有语言
     */
    List<DictionaryBO> queryLanguage(Integer roleId);


    /**
     * startTime 开始时间
     * endTime   结束时间
     * id        语言id
     */
    BigDecimal queryData(Map<String,Object> map);



}
