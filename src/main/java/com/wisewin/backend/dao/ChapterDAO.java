package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.ChapterBO;

import java.util.List;
import java.util.Map;

/**
 * 课程
 */
public interface ChapterDAO {


    /**
     *  查询课程列表
     *  chapterName 课时名字
     *  status  状态
     *  freeOrNot 是否免费观看
     * @param map
     * @return
     */
    List<ChapterBO> queryChapterList(Map<String, Object> map);

    /**
     * 查询数量
     *
     */
    Integer queryChapterCount(Map<String, Object> map);

    /**
     * 添加课程
     */
    int addChapter(ChapterBO chapterBO);


    /**
     *  修改课程
     */
    int  updateChapter(ChapterBO chapterBO);

    /**
     *  通过id 查询课程
     */
    ChapterBO queryChapterId(Integer id);



}
