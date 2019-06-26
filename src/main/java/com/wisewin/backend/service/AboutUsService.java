package com.wisewin.backend.service;

import com.wisewin.backend.dao.AboutUsDAO;
import com.wisewin.backend.entity.bo.AboutUsBO;
import com.wisewin.backend.service.base.LogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Service("aboutUsService")
@Transactional
public class AboutUsService {

    @Resource
    private AboutUsDAO aboutUsDAO;
    @Resource
    private LogService logService;
    /**
     * 查询关于我们
     * @return
     */
    public AboutUsBO selectContent() {
        return aboutUsDAO.selectAbout();
    }

    //修改"关于我们"的信息
    /*
    没有数据时,添加;
    只有一条时,修改;
    大于一条时,数据异常
     */

    /**
     *
     * @param aboutUsBO
     * @return
     */
    public boolean updateAbouUs(AboutUsBO aboutUsBO) {
        logService.serviceStart("AboutUsService.updateAbouUs",aboutUsBO);
        logService.call("aboutUsDAO.selectid()");
        int i = aboutUsDAO.selectid();
        logService.result(i);
        if (i == 0) {  //如果表里没有数据
            logService.call("aboutUsDAO.insertAboutUs()",aboutUsBO);
            aboutUsDAO.insertAboutUs(aboutUsBO);
            logService.end("false");
            return false;
        } else  {  //如果表里有且只有一条数据
            //修改内容
            logService.call("boutUsDAO.updateAboutUs()",aboutUsBO);
            aboutUsDAO.updateAboutUs(aboutUsBO);
            logService.end("false");
            return true;
        }

    }
}
