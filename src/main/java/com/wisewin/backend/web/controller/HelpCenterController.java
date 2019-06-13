package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.HelpCenterBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.HelpCenterService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/helpCenter")
public class HelpCenterController extends BaseCotroller {
    @Resource
    private HelpCenterService helpCenterService;
    @Resource
    private LogService logService;
    /**
     * 帮助中心列表
     * @param request
     * @param response
     */
    @RequestMapping("/selectHelpCenter")
    public void selectHelpCenter(HttpServletRequest request, HttpServletResponse response) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request);
        logService.call("helpCenterService.selectHelpCenter()");
        List<HelpCenterBO> helpCenterBOList = helpCenterService.selectHelpCenter();

        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(helpCenterBOList));
        logService.end("/helpCenter/selectHelpCenter",request);
        super.safeJsonPrint(response, result);
    }

    /**
     * 新增帮助中心信息
     * @param response
     * @param request
     */
    @RequestMapping("/insertHelpCenter")
    public void insertHelpCenter(HelpCenterBO helpCenterBO, HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,helpCenterBO);
        if (helpCenterBO==null||StringUtils.isEmpty(helpCenterBO.getContent())) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/insertHelpCenter",result);
            return;
        }

        helpCenterBO.setCreateId(loginAdmin.getId());
        logService.call("helpCenterService.insertHelpCenter",helpCenterBO);
        Integer i = helpCenterService.insertHelpCenter(helpCenterBO);
        if (i > 0) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("帮助中心信息添加成功！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/insertHelpCenter",result);
        } else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "帮助中心信息添加失败！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/insertHelpCenter",result);
        }
    }

    /**
     * 修改帮助中心
     * @param id
     * @param title        标题
     * @param content      内容
     * @param serialNumber 排序码
     * @param response
     * @param request
     */
    @RequestMapping("/updateHelpCenter")
    public void updateHelpCenter(Integer id, String title, String content, Integer serialNumber, HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id,title,content,serialNumber);
        if (id==null) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/updateHelpCenter",result);
            return;
        }
        HelpCenterBO helpCenterBO = new HelpCenterBO();
        helpCenterBO.setUpdateId(loginAdmin.getId());
        helpCenterBO.setId(id);
        helpCenterBO.setTitle(title);
        helpCenterBO.setContent(content);
        helpCenterBO.setSerialNumber(serialNumber);
        //判断是否修改成功
        logService.call("helpCenterService.updateHelpCenter",helpCenterBO);
        boolean i = helpCenterService.updateHelpCenter(helpCenterBO);
        if (i) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改帮助中心信息成功！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/updateHelpCenter",result);
        } else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "修改帮助中心信息失败！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/updateHelpCenter",result);
        }
    }

    /**
     * 删除帮助中心信息
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("/deleteHelpContent")
    public void deleteHelpContent(Integer id, HttpServletRequest request, HttpServletResponse response) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        //参数验证
        if (StringUtils.isEmpty(String.valueOf(id))) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/deleteHelpContent",result);
            return;
        }
        logService.call("helpCenterService.deleteHelpContent",id);
        boolean i = helpCenterService.deleteHelpContent(id);
        //判断是否删除成功
        if (i) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除帮助中心信息成功！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/deleteHelpContent",result);
        } else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "删除帮助中心信息失败！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/deleteHelpContent",result);
        }
    }

    /**
     * 获取帮助中心内容
     * @param id
     * @param request
     * @param response
     */
    @RequestMapping("/getparticulars")
    public void getparticulars(Integer id, HttpServletRequest request, HttpServletResponse response) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        //参数验证
        if (StringUtils.isEmpty(String.valueOf(id))) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            logService.end("/helpCenter/getparticulars",result);
            return;
        }
        logService.call("/helpCenter/getparticulars",id);
        HelpCenterBO helpCenterBO = helpCenterService.getparticulars(id);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(helpCenterBO));
        super.safeJsonPrint(response, result);
        logService.end("/helpCenter/getparticulars",result);
    }
}
