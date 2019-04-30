package com.wisewin.backend.service;

import com.wisewin.backend.dao.DictionariestypeDAO;
import com.wisewin.backend.entity.bo.DictionariesBO;
import com.wisewin.backend.entity.bo.DictionariesjoinBO;
import com.wisewin.backend.entity.bo.DictionariestypeBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("DictionariestypeService")
@Transactional
public class DictionariestypeService {


    //调用持久层
    @Resource
    private DictionariestypeDAO dictionariestypeDAO;


    /**
     * 添加
     * String keyName; //类型名字
     * Integer keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     *
     */
    public boolean getaddDictionariestype(String keyName,Integer keyId,Double rank,Integer updateNameId){

        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(keyName,keyId,rank,updateNameId);

        return dictionariestypeDAO.addDictionariestype(dictionariestypeBO)>0;
    }

    /**
     * 显示
     * String keyName; //类型名字
     * Integer keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     */
    public List<DictionariestypeBO> getqueryDictionariestype(String keyName,Integer keyId,Double rank,Integer updateNameId,Date updateTime){

        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(keyName,keyId,rank,updateNameId,updateTime);

        return dictionariestypeDAO.queryDictionariestype(dictionariestypeBO);
    }

    /**
     * 修改
     * Integer id //字典类型id
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     */
    public boolean getupdateDictionariestype(Integer id,String keyName,Double rank,Integer updateNameId){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        map.put("keyName",keyName);
        map.put("rank",rank);
        map.put("updateNameId",updateNameId);

        return dictionariestypeDAO.updateDictionariestype(map)>0;
    }

    /**
     * 删除
     * Integer id //字典类型id
     */
    public boolean getdeleteDictionariestype(Integer id){

        return  dictionariestypeDAO.deleteDictionariestype(id)>0;
    }


    /////////////////////////字典内容表///////////////


    /**
     * 查找类型是否为空
     * String key_name
     * Integer key_id
     */
    public List<DictionariestypeBO> getqueryDictionarieslist(String keyName,Integer keyId){
        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(keyName,keyId);
        return  dictionariestypeDAO.queryDictionariestype(dictionariestypeBO);
    }

    /**
     *添加
     *  String key; //类型名字
     *  String value; //类型
     *  Integer dnId; //连接字典类型表
     *  String dnName; //创建人
     *  Integer updateUserId; //修改用户id
     *  String amend; //是否可以修改(yes/no)
     *  Double rank; //排序
     */
    public boolean getaddDictionaries(String key,String value,Integer dnId,String dnName,Integer updateUserId,Double rank){

        DictionariesBO dictionariesBO=new DictionariesBO(key,value,dnId,dnName,updateUserId,rank);

        return  dictionariestypeDAO.addDictionaries(dictionariesBO)>0;
    }

    /**
     *显示字典内容
     * String value; //类型
     * String dnName; //创建人
     *  Date dnReleasetime; //发布时间
     * Integer updateUserId; //修改用户id
     *  Double rank; //排序
     *  String keyName; //类型名字
     */
    public List<DictionariesjoinBO> getqueryDictionaries(DictionariesjoinBO dictionariesjoinBO){

        return dictionariestypeDAO.queryDictionaries(dictionariesjoinBO);

    }
    /**
     *修改字典内容
     Integer id; //字典id
     String key; //类型名字
     String value; //类型
     Integer dnId; //连接字典类型表
     Integer updateUserId; //修改用户id
     Double rank; //排序
     */
    public boolean getupdateDictionaries(Integer id,String key,String value,Integer dnId,Integer updateUserId,Double rank){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        map.put("key",key);
        map.put("value",value);
        map.put("dnId",dnId);
        map.put("updateUserId",updateUserId);
        map.put("rank",rank);

        return dictionariestypeDAO.updateDictionaries(map)>0;

    }
    /**
     * 删除字典内容
     * Integer id
     */
    public boolean  getdeleteDictionaries(Integer id){
    return  dictionariestypeDAO.deleteDictionaries(id)>0;
    }
}
