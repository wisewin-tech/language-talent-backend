package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.AboutUsBO;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.AboutUsService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 关于我们
 */
@Controller
@RequestMapping("/aboutUs")
public class AboutUsController extends BaseCotroller {
    @Resource
    private AboutUsService aboutUsService ;
    @Resource
    private LogService  logService;
    /**
     * 查询关于我们
     * @param request
     * @param response
     */
    @RequestMapping("/selectAboutUs")
    public void selectAboutUs(HttpServletRequest request,HttpServletResponse response) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,null);
        //通过查询信息,返回aboutUs对象
        AboutUsBO aboutUs=aboutUsService.selectContent();
        logService.result(aboutUs.toString());
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(aboutUs));
        logService.end("/AboutUsController/selectAboutUs",json);
        super.safeJsonPrint(response, json);
    }

    //更新信息,没有就添加,有,就修改,多了就报错
    @RequestMapping("/updateAboutUs")
    public void updateAboutUs(HttpServletRequest request,HttpServletResponse response,AboutUsBO aboutUsBO) {
        AdminBO adminBO = super.getLoginAdmin(request);
        logService.startController(adminBO,request,aboutUsBO);
        //参数非空验证
        if(aboutUsBO==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            logService.end("/AboutUsController/updateAboutUs",result);
            super.safeJsonPrint(response, result);
            return;
        }
        aboutUsBO.setAdminId(adminBO.getId());
            //对表里数据判断,符合条件的添加和修改,不符合返回false
        logService.call("aboutUsService.updateAbouUs",aboutUsBO);
        boolean t = aboutUsService.updateAbouUs(aboutUsBO);
        logService.result(t);
        if(t){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("信息修改成功"));
            logService.end("/AboutUsController/updateAboutUs",json);
            super.safeJsonPrint(response, json);
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功！"));
            logService.end("/AboutUsController/updateAboutUs",json);
            super.safeJsonPrint(response, json);
        }

    }


}
