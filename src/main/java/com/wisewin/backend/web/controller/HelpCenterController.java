package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.HelpCenterBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.HelpCenterService;
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

    /**
     * 帮助中心列表
     * @param request
     * @param response
     */
    @RequestMapping("/selectHelpCenter")
    public void selectHelpCenter(HttpServletRequest request, HttpServletResponse response) {
        List<HelpCenterBO> helpCenterBOList = helpCenterService.selectHelpCenter();
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(helpCenterBOList));
        super.safeJsonPrint(response, result);
    }

    /**
     * 新增帮助中心信息
     * @param title        标题
     * @param content      内容
     * @param serialNumber 排序码
     * @param response
     * @param request
     */
    @RequestMapping("/insertHelpCenter")
    public void insertHelpCenter(String title, String content, Integer serialNumber, HttpServletResponse response, HttpServletRequest request) {
        if (StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            return;
        }

        HelpCenterBO helpCenterBO = new HelpCenterBO();
        //从cookie中获取对象
        AdminBO adminBO = super.getLoginAdmin(request);
        helpCenterBO.setCreateId(adminBO.getId());
        helpCenterBO.setTitle(title);
        helpCenterBO.setContent(content);
        helpCenterBO.setSerialNumber(serialNumber);
        Integer i = helpCenterService.insertHelpCenter(helpCenterBO);
        if (i > 0) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("帮助中心信息添加成功！"));
            super.safeJsonPrint(response, result);
        } else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "帮助中心信息添加失败！"));
            super.safeJsonPrint(response, result);
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
        if (StringUtils.isEmpty(String.valueOf(id)) || StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            return;
        }
        HelpCenterBO helpCenterBO = new HelpCenterBO();
        //从cookie中获取对象
        AdminBO adminBO = super.getLoginAdmin(request);
        helpCenterBO.setUpdateId(adminBO.getId());
        helpCenterBO.setId(id);
        helpCenterBO.setTitle(title);
        helpCenterBO.setContent(content);
        helpCenterBO.setSerialNumber(serialNumber);
        //判断是否修改成功
        boolean i = helpCenterService.updateHelpCenter(helpCenterBO);
        if (i) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改帮助中心信息成功！"));
            super.safeJsonPrint(response, result);
        } else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "修改帮助中心信息失败！"));
            super.safeJsonPrint(response, result);
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
        //参数验证
        if (StringUtils.isEmpty(String.valueOf(id))) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            return;
        }
        boolean i = helpCenterService.deleteHelpContent(id);
        //判断是否删除成功
        if (i) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除帮助中心信息成功！"));
            super.safeJsonPrint(response, result);
        } else {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002", "删除帮助中心信息失败！"));
            super.safeJsonPrint(response, result);
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
        //参数验证
        if (StringUtils.isEmpty(String.valueOf(id))) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！"));
            super.safeJsonPrint(response, result);
            return;
        }
        HelpCenterBO helpCenterBO = helpCenterService.getparticulars(id);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(helpCenterBO));
        super.safeJsonPrint(response, result);
    }
}
