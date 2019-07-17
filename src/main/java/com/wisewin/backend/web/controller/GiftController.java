package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.GiftBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.GiftParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.GiftService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 *  礼品卡
 */

@Controller
@RequestMapping("/gift")
public class GiftController extends BaseCotroller {
    @Resource
    GiftService giftService;
    @Resource
    private LogService logService;
    /**
     * 查询所有
     * @param giftParam
     * @param response
     * @param request
     */
    @RequestMapping("/selectAll")
    public void selectAll(Integer pageNo, Integer pageSize, GiftParam giftParam,HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,pageNo,pageSize,giftParam);
        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if(queryInfo != null){
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        //把参数条件 放入map中
        condition.put("giftParam",giftParam);
        logService.call("giftService.selectAll",condition);
        System.err.println(giftParam.toString());
        List<GiftBO> giftBOS = giftService.selectAll(condition);
        logService.call("giftService.selectCount",giftParam.getBatchNumber(),giftParam.getTitle(),giftParam.getCardnumber(),giftParam.getValue(),giftParam.getStatus());
        int count=giftService.selectCount(giftParam.getBatchNumber(),giftParam.getTitle(),giftParam.getCardnumber(),giftParam.getValue(),giftParam.getStatus());
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("giftBOS",giftBOS);
        map.put("count",count);

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response, json);
        logService.end("/gift/selectAll",json);
    }

    /**
     * 添加
     * @param response
     * @param request
     */
    @RequestMapping("/addGift")
    public void addGift(GiftParam giftParam,Integer num,HttpServletResponse response, HttpServletRequest request) {
        //获取管理员账号
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,giftParam,num);
        if(loginAdmin==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/addGift",json);
            return;
        }
        if (num==null||num<=0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/addGift",json);
            return;
        }
        if(num>10000){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000007"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/addGift",json);
            return;
        }

        if(giftParam.getValue()<=0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000011"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/addGift",json);
            return;
        }

        if (StringUtils.isEmpty(giftParam.getTitle())||giftParam.getValue()==null||
                StringUtils.isEmpty(giftParam.getStarttime())||
                StringUtils.isEmpty(giftParam.getFinishtime())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/addGift",json);
            return;
        }
        logService.call(" giftService.addGift()",giftParam,num);
        giftService.addGift(giftParam,num);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
        logService.end("/gift/addGift",json);
    }
    /**
     * 修改
     * @param response
     * @param request
     */
    @RequestMapping("/updateGift")
    public void updateGift(GiftParam giftParam,HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,giftParam,giftParam);
        logService.call("giftService.updateGift",giftParam);
        giftService.updateGift(giftParam);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("0000000"));
        super.safeJsonPrint(response, json);
        logService.end("giftService.updateGift",giftParam);

    }

    /**
     * 批量删除
     *
     * @param idArrJSON  传入json格式用户id数组，
     * @param response
     * @param request
     */
    @RequestMapping("/frostGift")
    public void frostGift(String idArrJSON,String status,HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,idArrJSON,status);
        //获取管理员账号
        String[] split = idArrJSON.split(",");
        Integer [] ints= new Integer [split.length];
        for (int i = 0; i < split.length; i++) {
            ints[i]=Integer.parseInt(split[i]);
        }

        //用户传参验证
        if (StringUtils.isEmpty(idArrJSON)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/frostGift",json);
            return;
        }

        if (ints.length==0){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/gift/frostGift",json);
            return;
        }
        logService.call("giftService.frostGift",ints,status);
        Integer line=giftService.frostGift(ints,status);
        if(line>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("共删除"+line+"条信息")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/frostGift",result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("1111111")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/frostGift",result);
        }

    }


    /**
     * 导出礼品卡
     */
    @RequestMapping("/deriveGift")
    public void deriveGift(HttpServletRequest request ,HttpServletResponse response,Long batch){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,batch);

        if(batch==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/deriveGift",result);
            return;
        }

        if(giftService.countBatch(batch)<1){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000012")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/deriveGift",result);
            return;
        }

        try {
            logService.call("giftService.deriveGift",batch);
            giftService.deriveGift(response,batch);
        } catch (Exception e) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000008")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/deriveGift",result);
            return;
        }

    }


    /**
     * 礼品卡批量修改时间
     */
    @RequestMapping("/updateDate")
    public void updateDate(HttpServletRequest request ,HttpServletResponse response,String ids,Date startTime,Date endTime){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,ids,startTime,endTime);

        if(StringUtils.isEmpty(ids)||startTime==null ||endTime==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/deriveGift",result);
            return;
        }

        if(giftService.userCount(ids)>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000015")) ;
            super.safeJsonPrint(response , result);
            logService.end("/gift/updateDate",result);
            return;
        }

        logService.call("giftService.updateDate",ids,startTime,endTime);
        giftService.updateDate(ids,startTime,endTime,loginAdmin.getId());
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
        super.safeJsonPrint(response , result);
        return;


    }

}
