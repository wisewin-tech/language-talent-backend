package com.wisewin.backend.dao;

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
    List<QuestionBO> selectQuestion(Map<String,Object> map);

    /**
     *查询总条数
     * @param map
     * @return
     */
    Integer selectbylimitCount(Map<String,Object> map);

    /**
     * 修改题目
     * @param questionBO
     * @return
     */
    boolean updateQuestion(QuestionBO questionBO);
    /**
     * 查询所有信息
     * @param questionBO
     * @return
     */
    List<QuestionBO> getQuestion(QuestionBO questionBO);

    /**
     * 删除题目
     * @param idArr
     * @return
     */
    Integer delQuestion(@Param("idArr")Integer [] idArr);
}
