package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.CertificateResultBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.CertificateService;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/certifi")
public class CertificateController extends BaseCotroller {
    @Resource
    CertificateService certificateService;

    /**
     * 查询用户证书信息
     * @param response
     * @throws Exception
     */
    @RequestMapping("/selectUserCert")
    public void selectUserMedal(Integer pageNo, Integer pageSize,
                                String send,String status,Integer userId,
                                String mobile,
                                HttpServletResponse response) {
        //封装limit条件,pageNo改为页数
        QueryInfo queryInfo = getQueryInfo(pageNo,pageSize);
        //创建一个用于封装sql条件的map集合
        Map<String, Object> condition = new HashMap<String, Object>();
        if(queryInfo != null){
            //把pageOffset 页数,pageSize每页的条数放入map集合中
            condition.put("pageOffset", queryInfo.getPageOffset());
            condition.put("pageSize", queryInfo.getPageSize());
        }
        condition.put("userId",userId);
        condition.put("send",send);
        condition.put("status",status);
        condition.put("mobile",mobile);
        //查询用户证书
        List<CertificateResultBO> certificateResultBOS = certificateService.selectUser(condition);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(certificateResultBOS));
        super.safeJsonPrint(response, json);
    }

    @RequestMapping("/updateCertSend")
    public void updateSend(Integer id,HttpServletResponse response, HttpServletRequest request) {
        if (id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }
        certificateService.updateSend(id);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        super.safeJsonPrint(response, json);
    }

}
