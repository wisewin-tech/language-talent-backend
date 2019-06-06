package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/User")
public class UserController extends BaseCotroller {
    @Resource
    UserService userService;
    @Resource
    private LogService logService;

    /**
     *
     *根据条件分页查询用户信息
     * map里需要四个条件,页码，每页多少条数据
     * */
    @RequestMapping("/queryUsers")
    public void queryUsers(HttpServletRequest request, HttpServletResponse response, UserParam param){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);
        if(param.getPageNo()==null||param.getPageSize()==null||param.getPageNo()==0||param.getPageSize()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("User/queryUsers",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("name",param.getName());
        map.put("nickname",param.getNickname());
        map.put("email",param.getEmail());
        map.put("mobile",param.getMobile());

        QueryInfo queryInfo = getQueryInfo(param.getPageNo(),param.getPageSize());
        if(queryInfo != null){
            map.put("pageSize",queryInfo.getPageSize());
            map.put("pageIndex",queryInfo.getPageOffset());
        }
        logService.call("userService.selectUsers",map);
        List<UserBO> userBOList=userService.selectUsers(map);//查询到用户信息
        logService.result(userBOList);
        logService.call("userService.selectUsersCount",map);
        Integer userCount=userService.selectUsersCount(map);//用户数量
        logService.result(userCount);

        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("userBOList",userBOList);
        resultMap.put("count",userCount);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        logService.end("User/queryUsers",json);
        super.safeJsonPrint(response,json);
    }

    /**
     *
     *传入json格式用户id数组，转为数组把用户删除
     * */
    @RequestMapping("/deleteUsersById")
    public void deleteUsersById(HttpServletRequest request,HttpServletResponse response,String idArrJSON,String status){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,idArrJSON,status);
        if(idArrJSON==null||status==null||status.length()==0||idArrJSON.length()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("User/deleteUsersById",languagejson);
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer[] idArr=JsonUtils.getIntegerArray4Json(idArrJSON);
        logService.call("userService.deleteUsersById",idArr,status);
        Integer line=userService.deleteUsersById(idArr,status);
        logService.result(line);
        if(line>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
            logService.end("userService.deleteUsersById",result);
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            logService.end("userService.deleteUsersById",result);
            super.safeJsonPrint(response , result);
        }
    }



}
