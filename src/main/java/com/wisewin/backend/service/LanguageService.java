package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.LanguageConstants;
import com.wisewin.backend.dao.LanguageDAO;
import com.wisewin.backend.entity.bo.LanguageBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 语言
 */
@Service("LanguageService")
@Transactional
public class LanguageService {
    @Resource
    private LanguageDAO  languageDAO;
    @Resource
    private NoticeService noticeService;
    /**
     * 查询所有语言
     *  param languageName 语言名称
     *  pram status  状态上/下架
     *  pram hotOrNot 是否热门语言
     *  pram  preference  当前时间 (特惠中)
     */
    public List<LanguageBO> queryLanguageList(String languageName, String status, String hotOrNot, String preference){
        Map<String,Object>  queryMap=new HashMap<String, Object>();
        queryMap.put("languageName",languageName);
        queryMap.put("status",status);
        queryMap.put("hotOrNot",hotOrNot);
        if(preference!=null && preference.trim().length()>0)
             queryMap.put("preference",new Date());
        return languageDAO.queryLanguageList(queryMap);
    }



    /**
     *    语言添加
     *    languageName; //语言名称
     *    status; //状态  putaway / soldout
     *    foreignLanguageName; //外文名称
     *    ensignImageUrl; //国旗图片路径
     *    thumbnailImageUrl; //缩略图
     *    popularSort; //热门排序
     *    anguageLightspot; //语言亮点
     *    videoPath; //视频路径
     *    languageIntro; //语言简介
     *    price; //价格
     *    discountPrice; //特惠价
     *    discountStartTime; //特惠开始时间
     *    discountEndTime; //特惠结束时间
     *    hotOrNot;//是否为热门 yes/no
     *    createUserId; //创建人id
     *    createTime; //创建时间
     *    updateUserId; //修改人id
     *    updateTime; //修改时间
     *
     */
    public boolean addLanguage(LanguageBO languageBO, Integer userId) {
        if(languageBO.getPurchaseNotes()==null){
            languageBO.setPurchaseNotes(noticeService.queryNotice());
        }

        if(languageBO.getStatus()==null)
             languageBO.setStatus(LanguageConstants.STATUS_PUTAWAY.getValue());
        languageBO.setCreateTime(new Date());
        languageBO.setCreateUserId(userId);
        return  languageDAO.addLanguage(languageBO)>0;
    }

    /**
     *  语言修改
     */
    public boolean  updateLanguage(LanguageBO  languageBO, Integer userId){
        languageBO.setUpdateTime(new Date());
        languageBO.setUpdateUserId(userId);
        return  languageDAO.updateLanguage(languageBO)>0;
    }

    /**
     *  语言删除
     */
    public boolean  deleteLanguage(Integer id, Integer userId){
        LanguageBO languageBO = languageDAO.queryLanguageById(id);
        if(languageBO==null){
            return false;
        }
        if(languageBO.getStatus()!=null && languageBO.getStatus().equals(LanguageConstants.STATUS_PUTAWAY.getValue())){  //上架状态
            languageBO.setStatus(LanguageConstants.STATUS_SOLDOUT.getValue());
        }else{
            languageBO.setStatus(LanguageConstants.STATUS_PUTAWAY.getValue());
        }
        languageBO.setUpdateTime(new Date());
        languageBO.setUpdateUserId(userId);
        return  languageDAO.updateLanguage(languageBO)>0;
    }


    /**
     * 选择语言 查询
     *
     */
    public List<LanguageChoiceBO>  queryLanguageChoice(){
        return languageDAO.queryLanguageChoice();
    }

    /**
     * 修改购买须知
     * @param notice
     */
    public void updateNotice(String notice){
        languageDAO.updateNotice(notice);
    }
}