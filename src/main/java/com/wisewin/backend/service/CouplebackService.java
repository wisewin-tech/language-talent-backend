package com.wisewin.backend.service;


import com.wisewin.backend.dao.CouplebackDAO;
import com.wisewin.backend.entity.bo.CouplebackBO;
import com.wisewin.backend.entity.param.CouplebackParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 反馈[备用]
 */

@Service("CouplebackService")
@Transactional
public class CouplebackService {

    @Resource
    private CouplebackDAO couplebackDAO;

    /**
     * 反馈查

     */
    public List<CouplebackBO> getqueryCoupleback(String content, String contactpattern, String pattern, String pictureurl, Date updateTime,String disposeresult,String disposeperson){
        CouplebackBO couplebackBO=new CouplebackBO(content,contactpattern,pattern,pictureurl,updateTime,disposeresult,disposeperson);

        return  couplebackDAO.queryCoupleback(couplebackBO);
    }


}

