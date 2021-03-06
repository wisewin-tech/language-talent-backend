package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.GoalBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.GoalParam;
import com.wisewin.backend.service.GoalService;
import com.wisewin.backend.service.base.LogService;
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
@RequestMapping("/Goal")
public class GoalController extends BaseCotroller {

    @Resource
    private GoalService goalService;
    @Resource
    private LogService  logService;
    /**
     * 添加目的
     *   String ppPurpose; //目的
     *   Integer adminId; //创建人
     */

    @RequestMapping("/addGoal")
    public void addPurpose(HttpServletRequest request, HttpServletResponse response, GoalParam param){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);
        if (param.getPpPurpose().equals("") || param.getAdminId().equals("")){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("/Goal/addGoal",json);
            super.safeJsonPrint(response,json);
            return;
        }
         //有值返回true
        logService.call("goalService.getaddGoal",param.getPpPurpose(),param.getAdminId(),param.getRank());
        boolean addPurposejson= goalService.getaddGoal(param.getPpPurpose(),param.getAdminId(),param.getRank());
        if (addPurposejson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response,languagejson);
            logService.end("/Goal/addGoal",languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
        super.safeHtmlPrint(response,languagejson);
        logService.end("/Goal/addGoal",languagejson);
        return;
    }


    /**
     *   显示目的
     *   String ppPurpose; //目的
     *   Integer adminId; //创建人
     *   Date ppReleasetime; //创建时间
     *   Date ppUpdatetime; //修改时间
     */
    @RequestMapping("/queryGoal")
    public void queryGoal(HttpServletRequest request, HttpServletResponse response, Integer id,String ppPurpose, Integer adminId, String ppReleasetime, String ppUpdatetime,Double rank){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id,ppPurpose,adminId,ppReleasetime,ppUpdatetime,rank);
        logService.call("goalService.getqueryGoal",id,ppPurpose,adminId,com.wisewin.backend.util.dates.DateUtil.getDate(ppReleasetime),com.wisewin.backend.util.dates.DateUtil.getDate(ppUpdatetime),rank);
        List<GoalBO> list=goalService.getqueryGoal(id,ppPurpose,adminId,com.wisewin.backend.util.dates.DateUtil.getDate(ppReleasetime),com.wisewin.backend.util.dates.DateUtil.getDate(ppUpdatetime),rank);
        String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
        super.safeJsonPrint(response,json);
        logService.end("/Goal/queryGoal",json);
    }

    /**
     *   修改语言目的
     *   Integer id; //目的id
     *   String ppPurpose; //目的
     *   Integer adminId; //最后修改人id
     */
    @RequestMapping("/updateGoal")
    public void updateGoal(HttpServletRequest request,HttpServletResponse response,GoalParam param){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);

        if (param.getId()==null || "".equals(param.getPpPurpose())){
            String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
            logService.end("/Goal/updateGoal",json);
            return;
        }
        logService.call("goalService.getupdateGoal",param.getId(),param.getPpPurpose(),param.getAdminId(),param.getRank());
        boolean updateGoaljson=goalService.getupdateGoal(param.getId(),param.getPpPurpose(),param.getAdminId(),param.getRank());

        if (updateGoaljson){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response,languagejson);
            logService.end("/Goal/updateGoal",languagejson);
            return;
        }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        logService.end("/Goal/updateGoal",languagejson);
        return;

    }

    /**
     * 删除目的
     * Integer id //目的id
     */
    @RequestMapping("/deleteGoal")
    public void deleteGoal(HttpServletRequest request,HttpServletResponse response, Integer id){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);

        //判断id是否为空
     if (id==null){
         String json= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
         super.safeJsonPrint(response,json);
         return;
     }

     logService.call("goalService.getdeleteGoal()",id);
     boolean deleteGoaljson=goalService.getdeleteGoal(id);

     if (deleteGoaljson){
         String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
         super.safeJsonPrint(response,languagejson);
         logService.end("/Goal/deleteGoal",id);
         return;
     }
        String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeHtmlPrint(response,languagejson);
        logService.end("/Goal/deleteGoal",id);
        return;
    }
}
