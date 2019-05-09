package com.wisewin.backend.web.controller;


import com.wisewin.backend.entity.bo.CouplebackBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.CouplebackParam;
import com.wisewin.backend.service.CouplebackService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.dates.DateUtil;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/Coupleback")
public class CouplebackController  extends BaseCotroller{


    @Resource
    private CouplebackService couplebackService;


    /**
     * 查询
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("/queryCoupleback")
    public void  queryCoupleback(HttpServletRequest request, HttpServletResponse response, CouplebackParam param){



        List<CouplebackBO> list=couplebackService.getqueryCoupleback(param.getContent(),param.getContactpattern(),param.getPattern(),param.getPictureurl(), DateUtil.getDate(param.getUpdateTime()),param.getDisposeresult(),param.getDisposeresult());


            String json=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list));
            super.safeJsonPrint(response,json);
            return;





    }
}
