package com.wisewin.backend.service;

import com.wisewin.backend.dao.QuestionDAO;
import com.wisewin.backend.entity.bo.ChapterIdBO;
import com.wisewin.backend.entity.bo.QuestionBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("questionService")
@Transactional
public class QuestionService {
    @Resource
    private QuestionDAO questionDAO;

    /**
     * 添加题目到题库
     * @param questionBO
     * @return
     */
    public Integer addquestion(QuestionBO questionBO){
        return questionDAO.addquestion(questionBO);
    }

    /**
     * 查询题库
     * @param map
     * @return
     */
    public List<QuestionBO> selectQuestion(Map<String,Object> map){
        return questionDAO.selectQuestion(map);
    }
    /**
     * 查询总条数
     * @param mapParam
     * @return
     */
    public Integer selectbylimitCount(Map<String,Object> mapParam){
        return questionDAO.selectbylimitCount(mapParam);
    }

    /**
     * 修改题目
     * @param questionBO
     * @return
     */
    public boolean updateQuestion(QuestionBO questionBO){
        return questionDAO.updateQuestion(questionBO);
    }

    /**
     * 查询某题的所有信息
     * @param id
     * @return
     */
    public QuestionBO getQuestion(Integer id){
        return questionDAO.getQuestion(id);
    }
    //通过课程id查找语言id
    public ChapterIdBO getCourseId(Integer id){
        return questionDAO.getCourseId(id);
    }
    //通过课时id查找课程id级别id语言id
    public ChapterIdBO getChapterId(Integer id){
        return questionDAO.getChapterId(id);
    }

    /**
     * 删除题目
     * @param idArr
     * @return
     */
    public Integer delQuestion(Integer []idArr){
        return questionDAO.delQuestion(idArr);
    }
}
