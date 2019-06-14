package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.DictionariesBO;
import com.wisewin.backend.entity.bo.DictionariesjoinBO;
import com.wisewin.backend.entity.bo.DictionariestypeBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.DictionariesParam;
import com.wisewin.backend.entity.param.DictionariestypeParam;
import com.wisewin.backend.service.DictionariestypeService;
import com.wisewin.backend.service.base.LogService;
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
    @Resource
    private LogService logService;

    /**
     * 添加字典类型表
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     */
    @RequestMapping("/addDictionariestype")
    public void addDictionariestype(HttpServletRequest request, HttpServletResponse response, DictionariestypeParam param) {
        //创建获取管理员id
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, param);
        if (loginAdmin == null) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response, languagejson);
            logService.end("/Dictionariestype/addDictionariestype", languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        if (StringUtils.isObjEmpty(param.getKeyName()) || StringUtils.isObjEmpty(param.getRank()) || id == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/addDictionariestype", json);
            return;
        }
        //判断是否有数据keyName
        logService.call("dictionariestypeService.getfindDictionariestypekeyName", param.getKeyName());
        int dictionariestype = dictionariestypeService.getfindDictionariestypekeyName(param.getKeyName());
        logService.result(dictionariestype);
        if (dictionariestype == 0) {
            //判断是否有数据ValueName
            logService.call("dictionariestypeService.getfindDictionariestype", param.getValueName());
            int findictionaries = dictionariestypeService.getfindDictionariestype(param.getValueName());
            logService.result(findictionaries);
            if (findictionaries == 0) {
                //添加字典类型
                logService.call("dictionariestypeService.getaddDictionariestype", param.getValueName(), param.getRank(), id, param.getValueName());
                boolean dictionariestypejson = dictionariestypeService.getaddDictionariestype(param.getKeyName(), param.getRank(), id, param.getValueName());
                if (dictionariestypejson) {
                    String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
                    super.safeJsonPrint(response, languagejson);
                    logService.end("/Dictionariestype/addDictionariestype", languagejson);
                    return;
                }

            }

        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
        super.safeJsonPrint(response, json);
        logService.end("/Dictionariestype/addDictionariestype", json);
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
    public void queryDictionariestype(HttpServletRequest request, HttpServletResponse response, String keyName) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, keyName);
        //显示字典类型表的内容
        logService.call("dictionariestypeService.getqueryDictionariestype()", keyName);
        List<DictionariestypeBO> list = dictionariestypeService.getqueryDictionariestype(keyName);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response, json);
        logService.end("Dictionariestype/queryDictionariestype", json);
        return;

    }

    /**
     * 修改字典类型表
     * Integer id //字典类型id
     * String keyName; //类型名字
     * Double rank; //排序
     * Integer updateNameId; //最后修改人id
     */
    @RequestMapping("/updateDictionariestype")
    public void updateDictionariestype(HttpServletRequest request, HttpServletResponse response, DictionariestypeParam param) {
        //创建获取管理员id
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, param);
        if (loginAdmin == null) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response, languagejson);
            logService.end("/Dictionariestype/updateDictionariestype", languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        if (StringUtils.isObjEmpty(param.getId()) || StringUtils.isObjEmpty(param.getKeyName()) || StringUtils.isObjEmpty(param.getRank()) || id == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/updateDictionariestype", json);
            return;
        }


        //进行字典类型表内容修改
        logService.call("dictionariestypeService.getupdateDictionariestype", param.getId(), param.getKeyName(), param.getRank(), param.getUpdateNameId(), param.getValueName());
        boolean updateDictionariestypejson = dictionariestypeService.getupdateDictionariestype(param.getId(), param.getKeyName(), param.getRank(), param.getUpdateNameId(), param.getValueName());
        if (updateDictionariestypejson) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, languagejson);
            logService.end("/Dictionariestype/updateDictionariestype", languagejson);
            return;
        }
    }

    /**
     * 删除字典类型表
     * Integer id //字典类型id
     */
    @RequestMapping("/deleteDictionariestype")
    public void deleteDictionariestype(HttpServletRequest request, HttpServletResponse response, String DcId) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, DcId);

        if (DcId==null||"".equals(DcId)) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/deleteDictionariestype", json);
            return;
        }
        //转换为Integer数组类型类型
        Integer[] Did = JsonUtils.getIntegerArray4Json(DcId);
        //批量删除
        logService.call("dictionariestypeService.getdeleteDictionariestype()", Did);
        boolean deleteDictionariestypejson = dictionariestypeService.getdeleteDictionariestype(Did);

        if (deleteDictionariestypejson) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response, languagejson);
            logService.end("/Dictionariestype/deleteDictionariestype", languagejson);
            return;
        }
        String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response, languagejson);
        logService.end("/Dictionariestype/deleteDictionariestype", languagejson);
        return;
    }

    /////////////////////////字典内容表///////////////

    /**
     * 添加字典内容表
     * String key; //类型名字
     * String value; //内容
     * Integer outerId; //连接字典类型表
     * String dnName; //创建人
     * Integer updateUserId; //修改用户id
     * Double rank; //排序
     */
    @RequestMapping("/addDictionaries")
    public void addDictionaries(HttpServletRequest request, HttpServletResponse response, DictionariesParam param, String keyName) {
        //创建获取管理员id
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, param, keyName);
        if (loginAdmin == null) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response, languagejson);
            logService.end("/Dictionariestype/addDictionaries", languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        //通过keyName来查找数据
        logService.call("dictionariestypeService.getqueryDictionarieslist()", keyName);
        List<DictionariestypeBO> list = dictionariestypeService.getqueryDictionarieslist(keyName);
        logService.result(list);
        if (list == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/addDictionaries", json);
            return;
        }

        if (StringUtils.isObjEmpty(param.getKey()) || StringUtils.isObjEmpty(param.getValue()) || StringUtils.isObjEmpty(param.getOuter())
                || id == null || StringUtils.isObjEmpty(param.getRank())) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/addDictionaries", json);
            return;
        }

        //通过Value进行取数据，如果有数据将不能添加
        logService.call("dictionariestypeService.getfindloadDictionaries()", keyName);
        int dictionariesBO = dictionariestypeService.getfindloadDictionaries(keyName);
        if (dictionariesBO > 0) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeHtmlPrint(response, languagejson);
            logService.end("/Dictionariestype/addDictionaries", languagejson);
            return;
        }
        //添加字典内容表的内容
        logService.call("dictionariestypeService.getaddDictionaries()", param.getKey(), param.getValue(), param.getDnName(),
                id, param.getRank(), param.getOuter());
        dictionariestypeService.getaddDictionaries(param.getKey(), param.getValue(), param.getDnName(),
                id, param.getRank(), param.getOuter());

        String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
        super.safeJsonPrint(response, languagejson);
        logService.end("/Dictionariestype/addDictionaries", languagejson);
        return;
    }

    /**
     * 显示字典内容
     * private Integer id; //字典id
     * private String key; //类型名字
     * private String value; //类型
     * private Integer outerId; //连接字典类型表
     * private String dnName; //创建人
     * private Date dnReleasetime; //发布时间
     * private Integer updateUserId; //修改用户id
     * private Double rank; //排序
     */
    @RequestMapping("/queryDictionaries")
    public void queryDictionaries(HttpServletRequest request, HttpServletResponse response, Integer id, String key, String value, String outer, String dnName, String dnReleasetime, Integer updateUserId, Double rank) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, id, key, value, outer, dnName, dnReleasetime, updateUserId, rank);
        if (!StringUtils.isEmpty(outer)) {
            logService.call("dictionariestypeService.getqueryDictionaries()", id, key, value, outer, dnName, com.wisewin.backend.util.dates.DateUtil.getDate(dnReleasetime), updateUserId, rank);
            List<DictionariesBO> list = dictionariestypeService.getqueryDictionaries(id, key, value, outer, dnName, com.wisewin.backend.util.dates.DateUtil.getDate(dnReleasetime), updateUserId, rank);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/queryDictionaries", json);
            return;
        }
        //显示字典内容
        logService.call("dictionariestypeService.getqueryloadDictionarieslist", id, key, value, outer, dnName, com.wisewin.backend.util.dates.DateUtil.getDate(dnReleasetime), updateUserId, rank);
        List<DictionariesBO> list1 = dictionariestypeService.getqueryloadDictionarieslist(id, key, value, outer, dnName, com.wisewin.backend.util.dates.DateUtil.getDate(dnReleasetime), updateUserId, rank);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list1));
        super.safeJsonPrint(response, json);
        logService.end("/Dictionariestype/queryDictionaries", json);
        return;

    }

    /**
     * 修改字典内容
     * Integer id; //字典id
     * String key; //类型名字
     * String value; //类型
     * Integer dnId; //连接字典类型表
     * Integer updateUserId; //修改用户id
     * Double rank; //排序
     */
    @RequestMapping("/updateDictionaries")
    public void updateDictionaries(HttpServletRequest request, HttpServletResponse response, DictionariesParam param, String keyName) {
        //创建获取管理员id
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request, param, keyName);
        if (loginAdmin == null) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response, languagejson);
            logService.end("/Dictionariestype/updateDictionaries", languagejson);
            return;
        }
        Integer id = loginAdmin.getId();
        //查询keyName是否为空
        logService.call("dictionariestypeService.getqueryDictionarieslist()", keyName);
        List<DictionariestypeBO> list = dictionariestypeService.getqueryDictionarieslist(keyName);
        if (list == null) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/updateDictionaries", json);
            return;
        }


        if (StringUtils.isObjEmpty(param.getId()) || StringUtils.isObjEmpty(param.getKey()) || StringUtils.isObjEmpty(param.getValue()) || StringUtils.isObjEmpty(param.getOuter())
                || id == null || StringUtils.isObjEmpty(param.getRank())) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/Dictionariestype/updateDictionaries", json);
            return;
        }

        //根据字典内容id查找修改的值是否一样
        logService.call("dictionariestypeService.getfindDictionariesId()", param.getId());
        DictionariesBO dictionariesBO = dictionariestypeService.getfindDictionariesId(param.getId());
        logService.result(dictionariesBO);
        if (!dictionariesBO.getValue().equals(param.getValue())) {
            //查询数据库是否有相同数据
            logService.call("dictionariestypeService.getfindloadDictionaries()", param.getValue());
            int dictionariesBO1 = dictionariestypeService.getfindloadDictionaries(param.getValue());
            if (dictionariesBO1 != 0) {
                String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
                super.safeHtmlPrint(response, languagejson);
                logService.end("/Dictionariestype/updateDictionaries", languagejson);
                return;
            }
        }
        //修改
        logService.call("dictionariestypeService.getupdateDictionaries()", param.getId());
        boolean updateDictionaries = dictionariestypeService.getupdateDictionaries(param.getId(), param.getKey(), param.getValue(), id, param.getRank(), param.getOuter());
        if (updateDictionaries) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, languagejson);
            logService.end("/Dictionariestype/updateDictionaries", languagejson);
            return;
        }
    }

    /**
     * 删除字典内容
     * Integer id
     */
    @RequestMapping("/deleteDictionaries")
    public void deleteDictionaries(HttpServletRequest request, HttpServletResponse response, String DcId) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin, request,DcId);

        if (DcId==null||DcId.length()<=2) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response, languagejson);
            logService.end("/Dictionariestype/deleteDictionaries",languagejson);
            return;
        }

        //转换成数组类型
        Integer[] cid = JsonUtils.getIntegerArray4Json(DcId);
        for (int i=0;i<cid.length;i++){
            System.err.println("=================="+cid[i]);
        }
        boolean deleteDictionaries = dictionariestypeService.getdeleteDictionaries(cid);
        if (deleteDictionaries) {
            String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response, languagejson);
            logService.end("/Dictionariestype/deleteDictionaries",languagejson);
            return;
        }
        String languagejson = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response, languagejson);
        logService.end("/Dictionariestype/deleteDictionaries",languagejson);
        return;
    }
}
