package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.DictionariesjoinBO;
import com.wisewin.backend.entity.bo.DictionariestypeBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.DictionariesParam;
import com.wisewin.backend.entity.param.DictionariestypeParam;
import com.wisewin.backend.service.DictionariestypeService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * Integer keyId; //外键id
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     *
     */
    @RequestMapping("/addDictionariestype")
    public void addDictionariestype(HttpServletRequest request, HttpServletResponse response, DictionariestypeParam param){

        if (param.getKeyName().equals("") && param.getKeyId().equals(" ") && param.getRank().equals(" ") && param.getUpdateNameId().equals(" ")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }

        boolean dictionariestypejson=dictionariestypeService.getaddDictionariestype(param.getKeyName(),param.getKeyId(),param.getRank(),param.getUpdateNameId());
        if (dictionariestypejson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
        super.safeHtmlPrint(response,languagejson);
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
    public void queryDictionariestype(HttpServletRequest request,HttpServletResponse response,String keyName,Integer keyId,Double rank,Integer updateNameId,Date updateTime){

        List<DictionariestypeBO> list=dictionariestypeService.getqueryDictionariestype(keyName,keyId,rank,updateNameId,updateTime);
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response,json);
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
    public  void updateDictionariestype(HttpServletRequest request,HttpServletResponse response,DictionariestypeParam param){

        if (param.getId().equals(" ") && param.getKeyName().equals(" ") && param.getRank().equals(" ") && param.getUpdateNameId().equals(" ")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }

        boolean updateDictionariestypejson=dictionariestypeService.getupdateDictionariestype(param.getId(),param.getKeyName(),param.getRank(),param.getUpdateNameId());
        if (updateDictionariestypejson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }

    /**
     * 删除字典类型表
     * Integer id //字典类型id
     */
    @RequestMapping("/deleteDictionariestype")
    public void deleteDictionariestype(HttpServletRequest request,HttpServletResponse response,Integer id){
        if (id.equals("")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }

        boolean deleteDictionariestypejson=dictionariestypeService.getdeleteDictionariestype(id);

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
     *  Integer dnId; //连接字典类型表
     *  String dnName; //创建人
     *  Integer updateUserId; //修改用户id
     *  Double rank; //排序
     */
    @RequestMapping("/addDictionaries")
    public void addDictionaries(HttpServletRequest request,HttpServletResponse response,DictionariesParam param,String keyName,Integer keyId){

        List<DictionariestypeBO> list=dictionariestypeService.getqueryDictionarieslist(keyName,keyId);
        if (list==null){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            return;
        }


        if (param.getKey().equals("") && param.getValue().equals("") && param.getDnId().equals("")
                && param.getDnName().equals("") && param.getUpdateUserId().equals("")  && param.getRank().equals("")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            return;
        }

        boolean addDictionariesjson=dictionariestypeService.getaddDictionaries(param.getKey(),param.getValue(),param.getDnId(),param.getDnName(),
                                                                                        param.getUpdateUserId(),param.getRank());

        if (addDictionariesjson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
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
    @RequestMapping("/queryDictionaries")
    public void queryDictionaries(HttpServletRequest request, HttpServletResponse response, DictionariesjoinBO dictionariesjoinBO){
        if (dictionariesjoinBO.getKeyName().equals("")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            return;
        }
        List<DictionariesjoinBO> list=dictionariestypeService.getqueryDictionaries(dictionariesjoinBO);
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
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
    public void updateDictionaries(HttpServletRequest request,HttpServletResponse response,DictionariesParam param,String keyName,Integer keyId){
      List<DictionariestypeBO> list=dictionariestypeService.getqueryDictionarieslist(keyName,keyId);
      if (list.equals("")){
          String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
          super.safeJsonPrint(response,json);
          return;
      }

      if (param.getId().equals("") && param.getKey().equals("") && param.getValue().equals("") && param.getDnId().equals("")
                                                                && param.getUpdateUserId().equals("") && param.getRank().equals("")){
          String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
          super.safeJsonPrint(response,json);
          return;
      }

      boolean updateDictionaries=dictionariestypeService.getupdateDictionaries(param.getId(),param.getKey(),param.getValue(),param.getDnId(),param.getUpdateUserId(),param.getRank());
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
    public void deleteDictionaries(HttpServletRequest request,HttpServletResponse response,Integer id){
    if (id.equals("")){
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        return;
    }
    boolean deleteDictionaries=dictionariestypeService.getdeleteDictionaries(id);
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
