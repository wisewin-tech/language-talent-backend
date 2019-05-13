package com.wisewin.backend.service;

import com.wisewin.backend.dao.KeyValDAO;
import com.wisewin.backend.entity.bo.KeyValuesBO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class KeyValService {
        @Resource
        private KeyValDAO keyValDAO;

    /**
     * 查询所有键值对
     * @return
     */
    public List<KeyValuesBO> selectAll( Map<String,Object> map) {
       return keyValDAO.selectAll(map);
    }

    /**
     * 修改val值
     * @param map
     */
    public void updateVal(Map<String,Object> map) {
        keyValDAO.updateVal(map);
    }

    /**
     * 添加
     * @param key
     * @param val
     */
    public void addKeyVal(String key,String val){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("key",key);
        map.put("val",val);
         keyValDAO.addKeyVal(map);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteKey(Integer id) {
       keyValDAO.deleteKey(id);
    }

}
