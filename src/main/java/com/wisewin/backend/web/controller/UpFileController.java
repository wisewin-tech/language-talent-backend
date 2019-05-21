package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.OSSClientUtil;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 文件上传与删除
 */
@Controller
@RequestMapping("/upFile")
public class UpFileController extends BaseCotroller {


  /*  @RequestMapping("/upVideo")
    public void upVideo(HttpServletResponse response, MultipartFile video) throws Exception {
        if (video==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        //上传
        String name=ossClientUtil.uploadImgvideo(video);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(name));
        super.safeJsonPrint(response,json);
    }*/

    //上传图片
    @RequestMapping("/upFile")
    public void upFile(HttpServletRequest request, HttpServletResponse response, MultipartFile file)
            throws Exception {
        //图片非空判断
        if (file==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }
        OSSClientUtil ossClientUtil=new OSSClientUtil();
        //上传
        String name=ossClientUtil.uploadImg2Oss(file,false);
        //name:图片路径+图片名(图片名为生成的随机数)
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(name));
        super.safeJsonPrint(response,json);
    }


    //删除图片
    @RequestMapping("/delFile")
    public void delFile(String name,HttpServletResponse response) {

        if (StringUtils.isEmpty(name)){
            String json = JsonUtils.getJsonString4JavaPOJO
                    (ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response,json);
        }

        OSSClientUtil ossClientUtil=new OSSClientUtil();
        ossClientUtil.deleteFileInfo(name);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success
                (null));
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
