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
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     *String valueName; //类型内容
     */
    public boolean getaddDictionariestype(String keyName,Double rank,Integer updateNameId,String valueName){

        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(keyName,rank,updateNameId,valueName);

        return dictionariestypeDAO.addDictionariestype(dictionariestypeBO)>0;
    }

    /**
     * 判断是否有数据keyName
     */
    public int getfindDictionariestypekeyName(String keyName){

        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO();
        dictionariestypeBO.setKeyName(keyName);
        return dictionariestypeDAO.findDictionariestypekeyName(dictionariestypeBO);
    }


    /**
     * 有条件查询显示
     *  private Integer id; //字典类型表id
     * String keyName; //类型名字
     * Integer keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     * String valueName; //类型内容
     *
     */
    public List<DictionariestypeBO> getqueryDictionariestype(Integer id, String keyName,Double rank,Integer updateNameId,Date updateTime,String valueName){

        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(id,keyName,rank,updateNameId,updateTime,valueName);

        return dictionariestypeDAO.queryDictionariestype(dictionariestypeBO);
    }



    /**
     * 通过id查找
     */
    public DictionariestypeBO getfindDictionariestypeid(Integer id,String keyName,String valueName){
        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(id,keyName,valueName);
        return  dictionariestypeDAO.findDictionariestypeid(dictionariestypeBO);
    }

    /**
     * 显示字典类型
     *    Integer id; //字典类型表id
     *   String keyName; //类型名字
     *   Double rank; //排序
     *   Integer updateNameId; //最后修改人id
     *   Date updateTime; //最后修改时间
     */
    public List<DictionariestypeBO> getqueryDictionariestypelist(Integer id, String keyName,Double rank,Integer updateNameId,Date updateTime,String valueName){
        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO(id,keyName,rank,updateNameId,updateTime,valueName);
        return  dictionariestypeDAO.queryDictionariestypelist(dictionariestypeBO);
    }



    /**
     * 修改
     * Integer id //字典类型id
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     * Date updateTime; //最后修改时间
     */
    public boolean getupdateDictionariestype(Integer id,String keyName,Double rank,Integer updateNameId,String valueName){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        map.put("keyName",keyName);
        map.put("rank",rank);
        map.put("updateNameId",updateNameId);
        map.put("valueName",valueName);

        return dictionariestypeDAO.updateDictionariestype(map)>0;
    }
    /**
     * 查询字典表是否有数据
     */
    public int getfindDictionariestype(String valueName){
        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO();
        dictionariestypeBO.setValueName(valueName);
        return  dictionariestypeDAO.findDictionariestypevalueName(dictionariestypeBO);
    }
    /**
     * 删除
     * Integer id //字典类型id
     */
    public boolean getdeleteDictionariestype(Integer[] Did){

        return  dictionariestypeDAO.deleteDictionariestype(Did)>0;
    }

    /**
     * 查找类型是否为空
     * String key_name
     * Integer key_id
     */
    public List<DictionariestypeBO> getqueryDictionarieslist(String keyName){
        DictionariestypeBO dictionariestypeBO=new DictionariestypeBO();
        dictionariestypeBO.setKeyName(keyName);
        return  dictionariestypeDAO.queryDictionariestype(dictionariestypeBO);
    }

    /////////////////////////字典内容表///////////////



    /**
     *添加
     *  String key; //类型名字
     *  String value; //类型
     *  Integer outerId; //连接字典类型表
     *  String dnName; //创建人
     *  Integer updateUserId; //修改用户id
     *  Double rank; //排序
     */
    public boolean getaddDictionaries(String key,String value,String dnName,Integer updateUserId,Double rank,String outer){

        DictionariesBO dictionariesBO=new DictionariesBO(key,value,dnName,updateUserId,rank,outer);

        return  dictionariestypeDAO.addDictionaries(dictionariesBO)>0;
    }

    /**
     *显示字典内容
     private Integer id; //字典id
     private String key; //类型名字
     private String value; //类型
     private Integer outerId; //连接字典类型表
     private String dnName; //创建人
     private Date dnReleasetime; //发布时间
     private Integer updateUserId; //修改用户id
     private Double rank; //排序
     */
    public List<DictionariesBO> getqueryDictionaries(Integer id,String key,String value,String outer,String dnName,Date dnReleasetime,Integer updateUserId,Double rank){

        DictionariesBO dictionariesBO=new DictionariesBO(id, key, value, dnName, dnReleasetime, outer, updateUserId, rank);

         return dictionariestypeDAO.queryDictionaries(dictionariesBO);

    }

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
    public List<DictionariesBO> getqueryloadDictionarieslist(Integer id,String key,String value,String outer,String dnName,Date dnReleasetime,Integer updateUserId,Double rank){
                DictionariesBO dictionariesBO=new DictionariesBO(id, key, value, dnName, dnReleasetime, outer, updateUserId, rank);
                return  dictionariestypeDAO.queryloadDictionarieslist(dictionariesBO);
    }

    /**
     * 查找是否存在数据
     */
    public int getfindloadDictionaries(String value){
        DictionariesBO dictionariesBO=new DictionariesBO(value);
        return  dictionariestypeDAO.findloadDictionaries(dictionariesBO);

    }


    /**
     * 通过id来查找
     */
    public DictionariesBO getfindDictionariesId(Integer id){
        DictionariesBO dictionariesBO=new DictionariesBO();
        dictionariesBO.setId(id);

        return  dictionariestypeDAO.findDictionariesId(dictionariesBO);
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
    public boolean getupdateDictionaries(Integer id,String key,String value,Integer updateUserId,Double rank,String outer){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        map.put("key",key);
        map.put("value",value);
        map.put("updateUserId",updateUserId);
        map.put("rank",rank);
        map.put("outer",outer);

        return dictionariestypeDAO.updateDictionaries(map)>0;

    }
    /**
     * 删除字典内容
     * Integer id
     */
    public boolean  getdeleteDictionaries(Integer[] cid){
    return  dictionariestypeDAO.deleteDictionaries(cid)>0;
    }
}
