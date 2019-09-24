package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.ChapterIdBO;
import com.wisewin.backend.entity.bo.IdsBO;
import com.wisewin.backend.entity.bo.QuestionBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuestionDAO {
    /**
     * 添加题目
     * @param questionBO
     * @return
     */
    Integer addquestion(QuestionBO questionBO);

    /**
     * 查询题库
     * @param map
     * @return
     */
    List<QuestionBO> selectQuestion(Map<String, Object> map);

    /**
     *查询总条数
     * @param map
     * @return
     */
    Integer selectbylimitCount(Map<String, Object> map);

    /**
     * 修改题目
     * @param questionBO
     * @return
     */
    boolean updateQuestion(QuestionBO questionBO);
    /**
     * 查询所有信息
     * @param id
     * @return
     */
    QuestionBO getQuestion(Integer id);
    //通过课程id查找语言id
    ChapterIdBO getCourseId(Integer id);
    //通过课时id查找课程id级别id语言id
    ChapterIdBO getChapterId(Integer id);

    /**
     * 删除题目
     * @param idArr
     * @return
     */
    Integer delQuestion(@Param("idArr") Integer[] idArr);

    /**
     * 添加测试题库
     * @param questionBO
     * @return
     */
    Integer addquestionTest(QuestionBO questionBO);

    /**
     * 删除测试题库
     */
    void  deleteTest(Integer userId);

    /**
     * 查询测试题库
     */
    List<QuestionBO> queryTest(Integer userId);

    List<IdsBO> getIds(@Param("languageId") Integer languageId, @Param("courseId") Integer courseId, @Param("levelId") Integer levelId);
}
