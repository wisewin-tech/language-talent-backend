package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.KeyValuesBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface KeyValDAO {
    /**
     * 查询
     * @return
     */
    List<KeyValuesBO> selectAll(Map<String, Object> map);

    /**
     * 添加
     * @param map
     */
    void addKeyVal(Map<String, Object> map);
    /**
     * 修改
     * @param
     */
    void updateVal(Map<String,Object> map);

    /**
     * 删除
     * @param id
     */
    void deleteKey(Integer id);

}
