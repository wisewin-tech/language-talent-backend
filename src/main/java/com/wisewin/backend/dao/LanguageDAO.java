package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.LanguageBO;
import com.wisewin.backend.entity.bo.LanguageChoiceBO;

import java.util.List;
import java.util.Map;

/**
 * 语言
 */
public interface LanguageDAO {

    /**
     * 查询所有语言
     *  param languageName 语言名称
     *  pram status  状态上/下架
     *  pram hotOrNot 是否热门语言
     *  pram  preference  当前时间 (特惠中)
     */
    List<LanguageBO>  queryLanguageList(Map<String,Object> map);

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
     */
    int addLanguage(LanguageBO languageBO);

    /**
     *  语言修改
     */
    int  updateLanguage(LanguageBO languageBO);

    /**
     *
     * 语言查询通过
     * @param  id
     */
    LanguageBO queryLanguageById(Integer id);

    /**
     *  选择语言
     *   return id
     *          name
     */
    List<LanguageChoiceBO> queryLanguageChoice();


    void updateNotice(String notice);
}
