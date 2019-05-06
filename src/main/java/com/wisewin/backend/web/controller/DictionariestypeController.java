package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.DictionariesBO;
import com.wisewin.backend.entity.bo.DictionariesjoinBO;
import com.wisewin.backend.entity.bo.DictionariestypeBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.DictionariesParam;
import com.wisewin.backend.entity.param.DictionariestypeParam;
import com.wisewin.backend.service.DictionariestypeService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Dictionariestype")
public class DictionariestypeController extends BaseCotroller {

    //调用service层
    @Resource
    private DictionariestypeService dictionariestypeService;

    /**
     * 添加字典类型表
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     *
     */
    @RequestMapping("/addDictionariestype")
    public void addDictionariestype(HttpServletRequest request, HttpServletResponse response, DictionariestypeParam param){

        if (param.getKeyName().equals("") && param.getRank()==null  && param.getUpdateNameId()==null){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            return;
        }

        int dictionariestype=dictionariestypeService.getfindDictionariestypekeyName(param.getKeyName());
        if (dictionariestype==0){
            int findictionaries=dictionariestypeService.getfindDictionariestype(param.getValueName());
            if (findictionaries==0){
                boolean dictionariestypejson=dictionariestypeService.getaddDictionariestype(param.getKeyName(),param.getRank(),param.getUpdateNameId(),param.getValueName());
                if (dictionariestypejson){
                    String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
                    super.safeJsonPrint(response,languagejson);
                    return;
              }

            }

        }
        String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
        super.safeJsonPrint(response,json);
        return;


    }


    /**
     * 显示字典类型表
     * String keyName; //类型名字
     * Integ    er keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
         * Date updateTime; //最后修改时间
     */

    @RequestMapping("/queryDictionariestype")
    public void queryDictionariestype(HttpServletRequest request,HttpServletResponse response,Integer id,String keyName ,Double rank,Integer updateNameId,String updateTime,String valueName){


       // if (!StringUtils.isEmpty(keyName)) {
            List<DictionariestypeBO> list = dictionariestypeService.getqueryDictionariestype(id, keyName, rank, updateNameId, com.wisewin.backend.util.dates.DateUtil.getDate(updateTime), valueName);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
            super.safeJsonPrint(response, json);
            return;
     //   }
//            List<DictionariestypeBO> list1=dictionariestypeService.getqueryDictionariestypelist(id,keyName,rank,updateNameId,updateTime);
//            String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list1));
//            super.safeJsonPrint(response,json);
//            return;


    }

    /**
     * 修改字典类型表
     *  Integer id //字典类型id
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     *
     */
    @RequestMapping("/updateDictionariestype")
    public  void updateDictionariestype(HttpServletRequest request,HttpServletResponse response,DictionariestypeParam param) {

        if (param.getId() == null && param.getKeyName().equals(" ") && param.getRank() == null && param.getUpdateNameId() == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }

        int dictionariestype = dictionariestypeService.getfindDictionariestypekeyName(param.getKeyName());
        if (dictionariestype == 0) {
            int findictionaries = dictionariestypeService.getfindDictionariestype(param.getValueName());
            if (findictionaries == 0) {
                boolean updateDictionariestypejson = dictionariestypeService.getupdateDictionariestype(param.getId(), param.getKeyName(), param.getRank(), param.getUpdateNameId(), param.getValueName());
                if (updateDictionariestypejson) {
                    String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
                    super.safeJsonPrint(response, languagejson);
                    return;
                }

            }

        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
        super.safeHtmlPrint(response,languagejson);
        return;


    }

    /**
     * 删除字典类型表
     * Integer id //字典类型id
     */
    @RequestMapping("/deleteDictionariestype")
    public void deleteDictionariestype(HttpServletRequest request,HttpServletResponse response,String Dcyid){
        if (Dcyid==null){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }
        //转换为Integer数组类型类型
        Integer[] Did=JsonUtils.getIntegerArray4Json(Dcyid);
        boolean deleteDictionariestypejson=dictionariestypeService.getdeleteDictionariestype(Did);

        if (deleteDictionariestypejson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }

    /////////////////////////字典内容表///////////////

    /**
     *添加字典内容表
     *  String key; //类型名字
     *  String value; //内容
     *  Integer outerId; //连接字典类型表
     *  String dnName; //创建人
     *  Integer updateUserId; //修改用户id
     *  Double rank; //排序
     */
    @RequestMapping("/addDictionaries")
    public void addDictionaries(HttpServletRequest request,HttpServletResponse response,DictionariesParam param,String keyName ){

        List<DictionariestypeBO> list=dictionariestypeService.getqueryDictionarieslist(keyName);
        if (list==null){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            return;
        }


        if (param.getKey().equals("") && param.getValue().equals("") && param.getOuterId()==null
                && param.getDnName().equals("") && param.getUpdateUserId()==null  && param.getRank()==null){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            return;
        }


        int dictionariesBO=dictionariestypeService.getfindloadDictionaries(param.getValue());
        if (dictionariesBO>0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        boolean addDictionariesjson=dictionariestypeService.getaddDictionaries(param.getKey(),param.getValue(),param.getDnName(),
                param.getUpdateUserId(),param.getRank(),param.getOuterId());
        if (addDictionariesjson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
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
    @RequestMapping("/queryDictionaries")
    public void queryDictionaries(HttpServletRequest request, HttpServletResponse response, Integer id,String key,String value,Integer outerId,String dnName,String dnReleasetime,Integer updateUserId,Double rank){

        if ( !StringUtils.isObjEmpty(outerId)){
            List<DictionariesBO> list=dictionariestypeService.getqueryDictionaries(id,key,value,outerId,dnName,com.wisewin.backend.util.dates.DateUtil.getDate(dnReleasetime),updateUserId,rank);
            String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
            super.safeJsonPrint(response,json);
            return;
        }
        List<DictionariesBO> list1=dictionariestypeService.getqueryloadDictionarieslist(id,key,value,outerId,dnName,com.wisewin.backend.util.dates.DateUtil.getDate(dnReleasetime),updateUserId,rank);
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list1));
        super.safeJsonPrint(response,json);
        return;

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
    @RequestMapping("/updateDictionaries")
    public void updateDictionaries(HttpServletRequest request,HttpServletResponse response,DictionariesParam param,String keyName){
      List<DictionariestypeBO> list=dictionariestypeService.getqueryDictionarieslist(keyName);
      if (list.equals("")){
          String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
          super.safeJsonPrint(response,json);
          return;
      }


      if (param.getId()==null && param.getKey().equals("") && param.getValue().equals("") && param.getOuterId()==null
                                                                && param.getUpdateUserId()==null && param.getRank()==null){
          String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
          super.safeJsonPrint(response,json);
          return;
      }

      boolean updateDictionaries=dictionariestypeService.getupdateDictionaries(param.getId(),param.getKey(),param.getValue(),param.getOuterId(),param.getUpdateUserId(),param.getRank());
      if (updateDictionaries){
          String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
          super.safeJsonPrint(response,languagejson);
          return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }

    /**
     * 删除字典内容
     * Integer id
     */
    @RequestMapping("/deleteDictionaries")
    public void deleteDictionaries(HttpServletRequest request,HttpServletResponse response,String DcId){
    if (DcId==null){
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }
    //转换成数组类型
    Integer[] cid=JsonUtils.getIntegerArray4Json(DcId);

    boolean deleteDictionaries=dictionariestypeService.getdeleteDictionaries(cid);
    if (deleteDictionaries){
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
        super.safeJsonPrint(response,languagejson);
        return;
    }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }
}
