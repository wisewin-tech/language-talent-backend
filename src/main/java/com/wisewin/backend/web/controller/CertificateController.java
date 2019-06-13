package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.CertificateResultBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.CertificateService;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.StringUtils;
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
    @Resource
    private LogService  logService;
    /**
     * 查询用户证书信息
     * @param response
     * @throws Exception
     */
    @RequestMapping("/selectUserCert")
    public void selectUserMedal(Integer pageNo, Integer pageSize,
                                String send,String status,Integer userId,
                                String mobile,String logistics,
                                HttpServletResponse response,HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,pageNo,pageSize,send,status,userId,mobile,logistics);

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
        condition.put("logistics",logistics);
        //查询用户证书
        List<CertificateResultBO> certificateResultBOS = certificateService.selectUser(condition);
        int size = certificateService.selectCertificateCount(condition);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("certificateResultBOS",certificateResultBOS);
        map.put("count",size);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(map));
        super.safeJsonPrint(response, json);
        logService.end("/certifi/selectUserCert",json);
    }

    @RequestMapping("/updateCertSend")
    public void updateSend(Integer id,String logistics,HttpServletResponse response, HttpServletRequest request) {
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id,logistics);
        //获取管理员id

        //验证管理员是否登录
        if (loginAdmin==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002")) ;
            super.safeJsonPrint(response, result);
            logService.end("/certifi/updateCertSend",result);
            return;
        }
        if (id==null|| StringUtils.isEmpty(logistics)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            logService.end("/certifi/updateCertSend",json);
            return;
        }
        logService.call(" certificateService.updateSend(id,loginAdmin.getId(),logistics)",id,loginAdmin,logistics);
        certificateService.updateSend(id,loginAdmin.getId(),logistics);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(null));
        logService.end("/certifi/updateCertSend",json);
        super.safeJsonPrint(response, json);
    }

}
