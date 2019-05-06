package com.wisewin.backend.web.controller;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.ChapterBO;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.ChapterParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.ChapterService;
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

/**
 *  课时
 */
@Controller
@RequestMapping("/chapter")
public class ChapterController extends BaseCotroller {

    @Resource
    private ChapterService chapterService;

    /**
     *  查询课时列表
     *  chapterName 课时名字
     *  status  状态
     *  freeOrNot 是否免费观看
     * @return
     */
    @RequestMapping("/queryChapterList")
    public void queryChapterList(HttpServletRequest request, HttpServletResponse response, ChapterParam chapterParam){

        QueryInfo queryInfo = getQueryInfo(chapterParam.getPageNo(),chapterParam.getPageSize());
        Map<String, Object> queryMap = new HashMap<String, Object>();
        if(queryInfo != null){
            queryMap.put("pageOffset", queryInfo.getPageOffset());
            queryMap.put("pageSize", queryInfo.getPageSize());
        }

        queryMap.put("chapterName",chapterParam.getChapterName());
        queryMap.put("status",chapterParam.getStatus());
        queryMap.put("freeOrNot",chapterParam.getFreeOrNot());


        List<ChapterBO> chapterBOS = chapterService.queryChapterList(queryMap);
        Integer count = chapterService.queryChapterCount(queryMap);
        Map<String,Object>  resultMap = new HashMap<String, Object>();
        resultMap.put("chapterList",chapterBOS);
        resultMap.put("count",count);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap));
        super.safeJsonPrint(response, json);
    }


    /**
     *  添加课程
     * @param request
     * @param response
     * @param chapterBO
     */
   @RequestMapping("/addChapter")
   public void addChapter(HttpServletRequest request,HttpServletResponse  response,ChapterBO chapterBO){
       AdminBO loginAdmin = super.getLoginAdmin(request);
       if(chapterBO.getChapterName()==null){
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
           super.safeJsonPrint(response, json);
           return;
       }

       boolean falg = chapterService.addChapter(chapterBO,loginAdmin.getId());

       if(falg){
           String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("课时添加成功！"));
           super.safeJsonPrint(response, json);
           return;
       }
       String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
       super.safeJsonPrint(response, json);
       return;
   }


    /**
     *  修改课时
     * @param request
     * @param response
     * @param chapterBO
     */
    @RequestMapping("/updateChapter")
    public void updateChapter(HttpServletRequest request,HttpServletResponse  response,ChapterBO  chapterBO){
        AdminBO  adminBO=super.getLoginAdmin(request);
        if(chapterBO.getId()==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        boolean falg = chapterService.updateChapter(chapterBO,adminBO.getId());

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改课时成功！"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;
    }


    /**
     *  删除课时
     * @param request
     * @param response
     * @Param id  课时id
     */
    @RequestMapping("/deledeChapter")
    public void deledeChapter(HttpServletRequest request,HttpServletResponse  response,Integer id){
        if(id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }

        boolean falg = chapterService.deledeChapter(id);

        if(falg){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除课时成功！"));
            super.safeJsonPrint(response, json);
            return;
        }
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
        super.safeJsonPrint(response, json);
        return;
    }


}
