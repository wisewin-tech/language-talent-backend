package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.OSSClientUtil;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传与删除
 */
@Controller
@RequestMapping("/upFile")
public class UpFileController extends BaseCotroller {

    @Resource
    private LogService logService;

    /**
     * 上传文件
     * @param request
     * @param response
     * @param file
     * @param flag
     * @throws Exception
     */
    @RequestMapping("/upFile")
    public void upFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file,Boolean flag)
            throws Exception {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,file,flag);
        //图片非空判断
        if (file==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("upFile/upFile",json);
            super.safeJsonPrint(response,json);
        }
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        //上传
        String name="";
        if(flag==null){
            name=ossClientUtil.uploadImg2Oss(file,false);
        }else{
            name=ossClientUtil.uploadImg2Oss(file,true);
        }
        //name:图片路径+图片名(图片名为生成的随机数)
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(name));
        logService.end("upFile/upFile",json);
        super.safeJsonPrint(response,json);
    }



    //删除图片
    @RequestMapping("/delFile")
    public void delFile(String name,HttpServletResponse response,HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,name);
        if (StringUtils.isEmpty(name)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            logService.end("upFile/delFile",json);
            super.safeJsonPrint(response,json);
        }
logService.call("ossClientUtil.deleteFileInfo",name);
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        ossClientUtil.deleteFileInfo(name);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        logService.end("upFile/delFile",json);
        super.safeJsonPrint(response,json);
    }


    @RequestMapping("/test")
    public void test(MultipartFile file,HttpServletResponse response) throws Exception {
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        String s = ossClientUtil.uploadImg2Oss(file,false);
        System.out.println(s);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success
                (null));
        super.safeJsonPrint(response,json);
    }

}
