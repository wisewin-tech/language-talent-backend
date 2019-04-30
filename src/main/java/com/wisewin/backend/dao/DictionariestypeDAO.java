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
     * 条件查询
     * String keyName; //类型名字
     * Integer keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     */
    List<DictionariestypeBO>  queryDictionariestype(DictionariestypeBO dictionariestypeBO);

    /**
     * 显示字典类型
     *    Integer id; //字典类型表id
     *   String keyName; //类型名字
     *   Double rank; //排序
     *   Integer updateNameId; //最后修改人id
     *   Date updateTime; //最后修改时间
     */
    List<DictionariestypeBO> queryDictionariestypelist(DictionariestypeBO dictionariestypeBO);


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
     *条件查询显示字典内容
     * String value; //类型
     * String dnName; //创建人
     *  Date dnReleasetime; //发布时间
     * Integer updateUserId; //修改用户id
     *  Double rank; //排序
     *  String keyName; //类型名字
     */
    List<DictionariesBO> queryDictionaries(DictionariesBO DictionariesBO);

    /**
     * 字典内容显示
     *  Integer id; //字典id
     *  String key; //类型名字
     *  String value; //类型
     *  Integer dnId; //连接字典类型表
     *  String dnName; //创建人
     *  Date dnReleasetime; //发布时间
     *  Integer updateUserId; //修改用户id
     *  Double rank; //排序
     */
    List<DictionariesBO> queryloadDictionarieslist(DictionariesBO dictionariesBO);

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
