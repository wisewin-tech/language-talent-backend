package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.ShareBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.service.ShareService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 分享图片
 */
@Controller
@RequestMapping("/share")
public class ShareController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(ShareController.class);

    @Resource
    private ShareService shareService;


    @RequestMapping("/queryShare")
    public void queryShare(HttpServletResponse response, HttpServletRequest  request) throws InterruptedException {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        if(loginAdmin==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeJsonPrint(response, json);
            return;
        }
        List<ShareBO> shareBOS = shareService.queryShare();
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(shareBOS));
        super.safeJsonPrint(response, json);

    }

    @RequestMapping("/updateShare")
    public void test1(HttpServletResponse response, HttpServletRequest  request,ShareBO shareBO) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        if(loginAdmin==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002"));
            super.safeJsonPrint(response, json);
            return;
        }

        if(shareBO.getId()==null|| StringUtils.isEmpty(shareBO.getKey())||StringUtils.isEmpty(shareBO.getRemark())||StringUtils.isEmpty(shareBO.getValue())){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        shareService.updateShare(shareBO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(""));
        super.safeJsonPrint(response, json);
    }


}
