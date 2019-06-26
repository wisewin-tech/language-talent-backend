package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.LanguageConstants;
import com.wisewin.backend.dao.ChapterDAO;
import com.wisewin.backend.entity.bo.ChapterBO;
import com.wisewin.backend.entity.bo.ChapterIdNameBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *  课程
 */
@Service("chapterService")
@Transactional
public class ChapterService {
    @Resource
    private ChapterDAO chapterDAO;

    /**
     *  查询课程列表
     *  chapterName 课时名字
     *  status  状态
     *  freeOrNot 是否免费观看
     * @param map
     * @return
     */
    public List<ChapterBO> queryChapterList(Map<String,Object> map){
        return  chapterDAO.queryChapterList(map);
    }

    /**
     * 查询数量
     *
     */
    public Integer queryChapterCount(Map<String,Object> map){
        return  chapterDAO.queryChapterCount(map);
    }

    /**
     * 添加课时
     */
    public boolean addChapter(ChapterBO chapterBO, Integer userId){
        chapterBO.setCreateUserId(userId);
        chapterBO.setCreateTime(new Date());
        return  chapterDAO.addChapter(chapterBO)>0;
    }


    /**
     *  修改课程
     */
   public boolean updateChapter(ChapterBO chapterBO,Integer userId){
       chapterBO.setUpdateUserId(userId);
       chapterBO.setUpdateTime(new Date());
        return chapterDAO.updateChapter(chapterBO)>0;
    }


    /**
     *  删除课时
     * @param id
     * @return
     */
    public boolean deledeChapter(Integer id) {
        ChapterBO queryChapter = chapterDAO.queryChapterId(id);
        if(queryChapter==null){
            return false;
        }
        if(queryChapter.getStatus()!=null && queryChapter.getStatus().equals(LanguageConstants.STATUS_PUTAWAY.getValue())){  //上架状态
            queryChapter.setStatus(LanguageConstants.STATUS_SOLDOUT.getValue());
        }else{
            queryChapter.setStatus(LanguageConstants.STATUS_PUTAWAY.getValue());
        }
        return chapterDAO.updateChapter(queryChapter) > 0;
    }
    /**
     * 通过语言id/课程id/级别id查询
     * @param chapterBO
     * @return
     */
    public List<ChapterBO> selectChapterById(ChapterBO chapterBO){
        return chapterDAO.selectChapterById(chapterBO);
    }

    /**
     * 通过级别id 和 名字查询 课时id
     * @param leavelId
     * @param name
     * @return
     */
    public Integer queryChapterIdByName(Integer leavelId, String name) {
        return chapterDAO.queryChapterIdByName(leavelId,name);
    }

    /**
     * 通过级别id 查询课时id和名字
     * @param levelId
     * @return
     */
    public List<ChapterIdNameBO> getChapterByLevelId(Integer levelId){
        return chapterDAO.getChapterByLevelId(levelId);
    }
}
