package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.DictionariesBO;
import com.wisewin.backend.entity.bo.DictionariesjoinBO;
import com.wisewin.backend.entity.bo.DictionariestypeBO;

import java.util.List;
import java.util.Map;

/**
 *  字典类型持久层
 *  Created by yyh on 2019/4/29
 */
public interface DictionariestypeDAO {

    /**
     * 添加
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     *
     */
    Integer  addDictionariestype(DictionariestypeBO dictionariestypeBO);

    /**
     * 显示
     * String keyName; //类型名字
     * Integer keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     */
    List<DictionariestypeBO>  queryDictionariestype(DictionariestypeBO dictionariestypeBO);

    /**
     * 修改
     * Integer id //字典类型id
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     */
    Integer updateDictionariestype(Map<String, Object> map);

    /**
     * 删除
     * Integer id //字典类型id
     */
    Integer deleteDictionariestype(Integer id);



    /////////////////////////字典内容表///////////////
    /**
     *添加字典内容
     *  String key; //类型名字
     *  String value; //类型
     *  Integer dnId; //连接字典类型表
     *  String dnName; //创建人
     *  Date dnReleasetime; //发布时间
     *  Integer updateUserId; //修改用户id
     *  String amend; //是否可以修改(yes/no)
     *  Double rank; //排序
     */
    Integer addDictionaries(DictionariesBO dictionariesBO);

    /**
     *显示字典内容
     * String value; //类型
     * String dnName; //创建人
     *  Date dnReleasetime; //发布时间
     * Integer updateUserId; //修改用户id
     *  Double rank; //排序
     *  String keyName; //类型名字
     */
    List<DictionariesBO> queryDictionaries(DictionariesBO DictionariesBO);

    /**
     *修改字典内容
      Integer id; //字典id
      String key; //类型名字
      String value; //类型
      Integer dnId; //连接字典类型表
      Date dnReleasetime; //最后修改时间时间
      Integer updateUserId; //修改用户id
      Double rank; //排序
     */
    Integer updateDictionaries(Map<String, Object> map);

    /**
     * 删除字典内容
     * Integer id
     */
    Integer deleteDictionaries(Integer id);

}
