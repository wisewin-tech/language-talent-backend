package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.UserService;
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

    /**
     *
     *根据条件分页查询用户信息
     * map里需要四个条件,页码，每页多少条数据
     * */
    @RequestMapping("/queryUsers")
    public void queryUsers(HttpServletRequest request, HttpServletResponse response, UserParam param){
        if(param.getPageIndex()==0||param.getPageSize()==0){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        Map<String,Object> map=new HashMap<String, Object>();
        map.put("name",param.getName());
        map.put("nickname",param.getNickname());
        map.put("email",param.getEmail());
        map.put("mobile",param.getMobile());

        QueryInfo queryInfo = getQueryInfo(param.getPageIndex(),param.getPageSize());
        if(queryInfo != null){
            map.put("pageSize",queryInfo.getPageSize());
            map.put("pageIndex",queryInfo.getPageOffset());
        }

        List<UserBO> userBOList=userService.selectUsers(map);//查询到用户信息
        Integer userCount=userService.selectUsersCount(map);//用户数量
        Map<String,Object> resultMap=new HashMap<String, Object>();
        resultMap.put("userBOList",userBOList);
        resultMap.put("userCount",userCount);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));

        super.safeJsonPrint(response,json);
    }

    /**
     *
     *传入用户id数组的json，转为数组把用户删除
     * */
    @RequestMapping("/deleteUsersById")
    public void deleteUsersById(HttpServletRequest request,HttpServletResponse response,String idArrJSON){
        if(idArrJSON.equals("")||idArrJSON==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer[] idArr=JsonUtils.getIntegerArray4Json(idArrJSON);
        Integer line=userService.deleteUsersById(idArr);
        if(line>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }



}
