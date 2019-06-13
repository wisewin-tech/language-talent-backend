package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.DiscoverBO;
import com.wisewin.backend.entity.dto.DiscoverDTO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.DiscoverService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 王彬 on 2019/5/5.
 */
@Controller
@RequestMapping("/discover")
public class DiscoverController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(DiscoverController.class);


    @Resource
    private DiscoverService discoverService;
    @Resource
    private LogService logService;
    /**
     * 分页条件查询发现列表
     *
     * @param request
     * @param response
     * @param pageNo   当前页 默认1
     * @param pageSize 每页显示条数  默认10
     * @param type     类型 条件  可选
     * @param title    标题 条件 可选
     */
    @RequestMapping(value = "/discoverList", method = RequestMethod.POST)
    public void discoverList(HttpServletRequest request, HttpServletResponse response, Integer pageNo, Integer pageSize, String type, String createTime, String title) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,pageNo,pageSize,type,createTime,title);
        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if (queryInfo != null) {
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        System.err.println(createTime);
        //用于模糊查询
        //DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println(createTime);
        condition.put("type", type);
        if(!StringUtils.isEmpty(createTime)){
            condition.put("createTime", createTime);
        }

        condition.put("title", title);
        condition.put("yes", "yes");
        Map<String, Object> countMap = new HashMap<String, Object>();
        countMap.put("type", type);

        if(!StringUtils.isEmpty(createTime)){
            countMap.put("createTime", createTime);
        }

        countMap.put("title", title);
        countMap.put("yes", "yes");
        Integer count = discoverService.countDiscover(countMap);
        logService.call("discoverService.queryListDiscoverBO",condition);
        List<DiscoverBO> list = discoverService.queryListDiscoverBO(condition);
        DiscoverDTO jsonString = new DiscoverDTO();
        jsonString.setList(list);
        jsonString.setCount(count);

        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonString));

        super.safeJsonPrint(response, json);
        logService.end("/discover/discoverList",json);
    }

    /**
     * 批量删除
     */
    @RequestMapping("/updateDiscoverbyShows")
    public void updateDiscoverbyShows(HttpServletRequest request, HttpServletResponse response, String discoverById) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,discoverById);
        if (StringUtils.isEmpty(discoverById)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000003"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbyShows",result);
            return;
        }
        //获取第一个字符
        String first = discoverById.substring(0, 1);
        //获取最后一个字符
        String last = discoverById.substring(discoverById.length() - 1, discoverById.length());
        if (!first.equals("[") || !last.equals("]")) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            return;
        }

        Integer[] idArr = JsonUtils.getIntegerArray4Json(discoverById);
        if (idArr == null || idArr.length == 0) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbyShows",result);
            return;
        }
        logService.call("discoverService.updateDiscoverbyShows(idArr)",idArr);
        discoverService.updateDiscoverbyShows(idArr);

        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
        super.safeJsonPrint(response, result);
        logService.end("/discover/updateDiscoverbyShows",result);
    }

    /**
     * 批量置顶
     */
    @RequestMapping("/updateDiscoverbySticks")
    public void updateDiscoverbySticks(HttpServletRequest request, HttpServletResponse response, String discoverById, String stick) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,discoverById,stick);
        if (StringUtils.isEmpty(discoverById)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbySticks",result);
            return;
        }
        //获取第一个字符
        String first = discoverById.substring(0, 1);
        //获取最后一个字符
        String last = discoverById.substring(discoverById.length() - 1, discoverById.length());

        if (!first.equals("[") || !last.equals("]")) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbySticks",result);
            return;
        }
        if (StringUtils.isEmpty(stick)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbySticks",result);
            return;
        }
        System.out.println(stick);
        Integer[] idArr = JsonUtils.getIntegerArray4Json(discoverById);
        if (idArr == null || idArr.length == 0) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbySticks",result);
            return;
        }

        //置顶
        if (stick.equals("yes")) {
            logService.call("discoverService.updateDiscoverbySticks",idArr, stick);
            discoverService.updateDiscoverbySticks(idArr, stick);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("置顶成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbySticks",result);
            return;
        }

        //置顶
        if (stick.equals("no")) {
            logService.call("discoverService.updateDiscoverbySticks",idArr, stick);
            discoverService.updateDiscoverbySticks(idArr, stick);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("取消置顶成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscoverbySticks",result);
            return;
        }

    }

    /**
     * 单表查询
     */
    @RequestMapping("/queryDiscover")
    public void queryDiscover(HttpServletRequest request, HttpServletResponse response, String id) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        if (StringUtils.isEmpty(id)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/queryDiscover",result);
            return;
        }
        logService.call(" discoverService.queryDiscover(id)",id);
        DiscoverBO discoverBO = discoverService.queryDiscover(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(discoverBO));
        logService.end("/discover/queryDiscover",json);
        super.safeJsonPrint(response, json);
    }


    /**
     * 修改
     */
    @RequestMapping("/updateDiscover")
    public void queryDiscover(HttpServletRequest request, HttpServletResponse response, DiscoverBO discoverBO) {
        AdminBO adminBO = super.getLoginAdmin(request);
        logService.startController(adminBO,request,discoverBO);
        if (adminBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscover",result);
            return;
        }
        if (discoverBO.getId() == null || StringUtils.isEmpty(discoverBO.getType())) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscover",result);
            return;
        }

        discoverBO.setDcUpdatename(adminBO.getName());
        discoverBO.setUpdateTime(new Date());

        if (discoverBO.getType().equals("journalism")) {
            logService.call("discoverService.updateJournalism",discoverBO);
            discoverService.updateJournalism(discoverBO);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscover",result);
            return;
        }
        if (discoverBO.getType().equals("curriculum")) {
            logService.call("discoverService.updateCurriculum",discoverBO);
            discoverService.updateCurriculum(discoverBO);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscover",result);
            return;
        }
        if (discoverBO.getType().equals("activity")) {
            logService.call("discoverService.updateActivity",discoverBO);
            discoverService.updateActivity(discoverBO);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/updateDiscover",result);
            return;
        }
    }

    /**
     * 发现添加
     *
     * @param request
     * @param response
     * @param discoverBO
     */
    @RequestMapping("/insertDiscover")
    public void insertDiscover(HttpServletRequest request, HttpServletResponse response, DiscoverBO discoverBO) {
        AdminBO adminBO = super.getLoginAdmin(request);
        logService.startController(adminBO,request,discoverBO);
        if (adminBO == null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/insertDiscover",result);
            return;
        }

        if (StringUtils.isEmpty(discoverBO.getType())) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/insertDiscover",result);
            return;
        }
        discoverBO.setDcUpdatename(adminBO.getName());
        discoverBO.setUpdateTime(new Date());
        discoverBO.setDcName(adminBO.getName());
        discoverBO.setCreateTime(new Date());
        //新闻
        if (discoverBO.getType().equals("journalism")) {
            logService.call("discoverService.insertJournalism()",discoverBO);
            discoverService.insertJournalism(discoverBO);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/insertDiscover",result);
            return;
        }
        //视频
        if (discoverBO.getType().equals("curriculum")) {
            logService.call("discoverService.insertCurriculum()",discoverBO);
            discoverService.insertCurriculum(discoverBO);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/insertDiscover",result);
            return;
        }
        //线下活动
        if (discoverBO.getType().equals("activity")) {
            logService.call("discoverService.insertActivity()",discoverBO);
            discoverService.insertActivity(discoverBO);
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功"));
            super.safeJsonPrint(response, result);
            logService.end("/discover/insertDiscover",result);
            return;
        }
    }
}


