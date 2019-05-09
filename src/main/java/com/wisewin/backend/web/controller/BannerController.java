package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.BannerBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.BannerService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/Banner")
public class BannerController extends BaseCotroller {

    @Resource
    BannerService bannerService;

    /**
     * 查询一条或者所有首页信息，轮播图，标题等
     * */
    @RequestMapping("/queryBannerAllOrById")
    public void queryBannerAllOrById(HttpServletResponse response, HttpServletRequest request,String status){
        List<BannerBO> bannerBOList=bannerService.queryBannerAllOrById(status);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(bannerBOList));
        super.safeJsonPrint(response,json);
    }


    /**
     * 修改banner上下架
     * */
    @RequestMapping("/deleteBanner")
    public void deleteBanner(HttpServletResponse response, HttpServletRequest request,String bannerId,String status){
        if(bannerId==null||bannerId.equals("")||status==null||status.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();

        if(bannerService.deleteBanner(bannerId,status,id)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "删除失敗")) ;
            super.safeJsonPrint(response , result);
        }

    }

    /**
     * 删除banner上下架
     * */
    @RequestMapping("/removeBanner")
    public void removeBanner(HttpServletResponse response, HttpServletRequest request,String bannerId){
        if(bannerId==null||bannerId.equals("")){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        if(bannerService.removeBanner(bannerId)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "删除失敗")) ;
            super.safeJsonPrint(response , result);
        }


    }

    /**
     * 修改Banner信息
     * */
    @RequestMapping("/updateBanner")
    public void updateBanner(HttpServletResponse response, HttpServletRequest request,BannerBO bannerBO){
        if(bannerBO==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }

        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();

        bannerBO.setUpdateUserId(id);

        if(bannerService.updateBanner(bannerBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 添加Banner信息
     * */
    @RequestMapping("/addBanner")
    public void addBanner(HttpServletResponse response, HttpServletRequest request,BannerBO bannerBO){
        if(bannerBO==null){
            String languagejson=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        AdminBO loginAdmin = super.getLoginAdmin(request);

        if(loginAdmin==null){
            String languagejson= JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004"));
            super.safeHtmlPrint(response,languagejson);
            return;
        }
        Integer id = loginAdmin.getId();

        bannerBO.setCreateUserId(id);
        if(bannerService.addBanner(bannerBO)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "添加失敗")) ;
            super.safeJsonPrint(response , result);
        }

    }
}
