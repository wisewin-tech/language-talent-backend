package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.DiscoverBO;
import com.wisewin.backend.entity.dto.DiscoverDTO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.DiscoverService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彬 on 2019/5/5.
 */
@Controller
@RequestMapping("/discover")
public class DiscoverController extends BaseCotroller {


    @Resource
    private DiscoverService discoverService;

    /**
     * 分页条件查询发现列表
     * @param request
     * @param response
     * @param pageNo  当前页 默认1
     * @param pageSize 每页显示条数  默认10
     * @param type 类型 条件  可选
     * @param dcReleasetime  创建时间 条件 可选
     * @param title 标题 条件 可选
     */
    @RequestMapping(value = "/discoverList", method = RequestMethod.POST)
    public void discoverList(HttpServletRequest request, HttpServletResponse response, Integer pageNo, Integer pageSize,String type,String dcReleasetime,String title) {



        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if (queryInfo != null) {
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());

        }
        condition.put("type", type);
        condition.put("dcReleasetime", dcReleasetime);
        condition.put("title", title);
        condition.put("yes", "yes");
        Map<String, Object> countMap = new HashMap<String, Object>();
        countMap.put("type", type);
        countMap.put("dcReleasetime", dcReleasetime);
        countMap.put("title", title);
        countMap.put("yes", "yes");
        Integer count =  discoverService.countDiscover(countMap);

        List<DiscoverBO> list = discoverService.queryListDiscoverBO(condition);
        DiscoverDTO  jsonString = new DiscoverDTO();
        jsonString.setList(list);
        jsonString.setCount(count);

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonString));

        super.safeJsonPrint(response, json);
    }

    /**
     * 批量删除
     */
    @RequestMapping("/updateDiscoverbyShows")
    public void updateDiscoverbyShows(HttpServletRequest request,HttpServletResponse response,String idArrJSON){
        if(StringUtils.isEmpty(idArrJSON)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003" ));
            super.safeJsonPrint(response , result);
        }
        //获取第一个字符
        String first=idArrJSON.substring(0, 1);
        //获取最后一个字符
        String last=idArrJSON.substring(idArrJSON.length()-1, idArrJSON.length());

        if(!first.equals("[")||!last.equals("]")){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" ));
            super.safeJsonPrint(response , result);
        }

        Integer[] idArr=JsonUtils.getIntegerArray4Json(idArrJSON);
        if(idArr == null || idArr.length == 0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001")) ;
            super.safeJsonPrint(response , result);
        }
        discoverService.updateDiscoverbyShows(idArr);


        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("批量删除成功")) ;
        super.safeJsonPrint(response , result);
    }

    /**
     * 批量置顶
     */
    @RequestMapping("/updateDiscoverbySticks")
    public void updateDiscoverbySticks(HttpServletRequest request,HttpServletResponse response,String discoverById) {
        if (StringUtils.isEmpty(discoverById)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeJsonPrint(response, result);
        }
        //获取第一个字符
        String first = discoverById.substring(0, 1);
        //获取最后一个字符
        String last = discoverById.substring(discoverById.length() - 1, discoverById.length());

        if (!first.equals("[") || !last.equals("]")) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
        }

        Integer[] idArr = JsonUtils.getIntegerArray4Json(discoverById);
        if (idArr == null || idArr.length == 0) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
        }
        discoverService.updateDiscoverbySticks(idArr);


        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("批量置顶成功"));
        super.safeJsonPrint(response, result);
    }

    /**
     * 置顶
     */
    @RequestMapping("/updateDiscoverbyStick")
    public void updateDiscoverbyStick(HttpServletRequest request,HttpServletResponse response,String discoverId){
        if(StringUtils.isEmpty(discoverId)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeJsonPrint(response, result);
        }
        discoverService.updateDiscoverbyStick(discoverId);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("置顶成功"));
        super.safeJsonPrint(response, result);
    }


    /**
     * 置顶
     */
    @RequestMapping("/updateDiscoverbyShow")
    public void updateDiscoverbyShow(HttpServletRequest request,HttpServletResponse response,String discoverId){
        if(StringUtils.isEmpty(discoverId)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
        }
        discoverService.updateDiscoverbyShow(discoverId);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
        super.safeJsonPrint(response, result);
    }

    /**
     * 修改
     */
    @RequestMapping(value = "/updateDiscover",method =RequestMethod.POST)
    public void updateDiscover(HttpServletRequest request,HttpServletResponse response,   DiscoverBO discoverBO){
            if(discoverBO.equals("")||discoverBO==null){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
                super.safeJsonPrint(response, result);
            }
        discoverService.updateDiscover(discoverBO);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
        super.safeJsonPrint(response, result);
    }

    /**
     * 单表查询
     */
    @RequestMapping("/queryDiscover")
    public void queryDiscover(HttpServletRequest request,HttpServletResponse response,String id){
        if(StringUtils.isEmpty(id)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数不能为空"));
            super.safeJsonPrint(response, result);
        }
       DiscoverBO discoverBO = discoverService.queryDiscover(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(discoverBO));
        super.safeJsonPrint(response, json);
    }
}


