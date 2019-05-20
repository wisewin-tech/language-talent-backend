package com.wisewin.backend.service;

import com.wisewin.backend.dao.NoticeDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 *  购买须知
 */
@Service("noticeService")
@Transactional
public class NoticeService {
    @Resource
    private NoticeDAO noticeDAO;
    @Resource
    private LanguageService  languageService;
    @Resource
    private CourseService  courseService;

    /**
     * 查询购买须知
     */
    public  String queryNotice(){
        return noticeDAO.queryNotice();
    }

    /**
     *添加购买须知
     */
    public void addNotice(String notice){
        noticeDAO.addNotice(notice);
    }

    /**
     * 查询购买须知数量
     */
    public int queryCount(){
        return noticeDAO.queryCount();
    }

    /**
     * 修改购买须知
     */
    public  void  updateNotice(String notice){
      /*  int row = this.queryCount();
        if(row>0){
            noticeDAO.updateNotice(notice);
        }else{
           this.addNotice(notice);
        }*/
        languageService.updateNotice(notice);
        courseService.updateNotice(notice);
    }




}
