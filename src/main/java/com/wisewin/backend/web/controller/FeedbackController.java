package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.FeedbackBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.FeedbackParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.FeedbackService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feedback")
public class FeedbackController extends BaseCotroller {
    @Resource
    private FeedbackService feedbackService;
    @Resource
    private LogService logService;
    /**
     * 通过状态查询意见反馈
     * @param feedbackParam
     * @param request
     * @param response
     */
    @RequestMapping("/selectFeedback")
    public void selectFeedback(Integer pageNo, Integer pageSize, FeedbackParam feedbackParam, HttpServletRequest request, HttpServletResponse response){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,pageNo,pageSize,feedbackParam);
        //验证参数
        if (feedbackParam==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
            logService.end("/feedback/selectFeedback",result);
            return ;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        Map<String, Object> maps = new HashMap<String, Object>();
        if(queryInfo != null){
            maps.put("pageOffset", queryInfo.getPageOffset());
            maps.put("pageSize", queryInfo.getPageSize());
        }
            maps.put("feedbackParam",feedbackParam);
//        maps.put("status",feedbackParam.getStatus());
//        maps.put("begin",feedbackParam.getBegin());
//        maps.put("end",feedbackParam.getEnd());
        logService.call("feedbackService.selectFeedback",maps);
        List<FeedbackBO> feedbackBOList = feedbackService.selectFeedback(maps);
        Integer count = feedbackService.selectbylimitCount(maps);
        Map<String,Object>  resultMap=new HashMap<String, Object>();
        resultMap.put("data",feedbackBOList);
        resultMap.put("count",count);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, result);
        logService.end("/feedback/selectFeedback",result);
    }

    /**
     * 修改意见反馈信息
     * @param id
     * @param feedbackParam
     * @param request
     * @param response
     */
    @RequestMapping("/updateFeedback")
    public void updateFeedback(Integer id, FeedbackParam feedbackParam, HttpServletRequest request, HttpServletResponse response){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id,feedbackParam);
        //参数验证
        if (StringUtils.isEmpty(String.valueOf(id))||feedbackParam==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            super.safeJsonPrint(response, result);
            logService.end("feedback/updateFeedback",result);
            return ;
        }
        FeedbackBO feedbackBO = new FeedbackBO();
        feedbackBO.setId(id);
        AdminBO adminBO = super.getLoginAdmin(request);
        Integer adminId = adminBO.getId();
        List<FeedbackBO> feedbackBOList= feedbackService.getFeedback(feedbackBO);
        for (FeedbackBO feedback:feedbackBOList){
            feedback.setStatus(feedbackParam.getStatus());
            feedback.setAdminId(adminId);
            feedback.setUpdateTime(new Date());
        }
        logService.call("feedbackService.updateFeedback",feedbackParam);
        boolean i = feedbackService.updateFeedback(feedbackParam);
        if (i) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改意见反馈状态成功！"));
            super.safeJsonPrint(response, result);
            logService.end("feedback/updateFeedback",result);
        }else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000012","修改意见反馈状态失败！"));
            super.safeJsonPrint(response, result);
            logService.end("feedback/updateFeedback",result);
        }

    }
}
