package com.wisewin.backend.service;

import com.wisewin.backend.dao.FeedbackDAO;
import com.wisewin.backend.entity.bo.FeedbackBO;
import com.wisewin.backend.entity.param.FeedbackParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("feedbackService")
@Transactional
public class FeedbackService {
    @Resource
    private FeedbackDAO feedbackDAO;

    /**
     * 查询意见反馈信息
     * @param map
     * @return
     */
    public List<FeedbackBO> selectFeedback(Map<String,Object> map){
        return feedbackDAO.selectFeedback(map);
    }


    /**
     * 查询总条数
     * @param mapParam
     * @return
     */
    public Integer selectbylimitCount(Map<String,Object> mapParam){
        return feedbackDAO.selectbylimitCount(mapParam);
    }

    /**
     * 修改意见反馈
     * @param feedbackParam
     * @return
     */
    public boolean updateFeedback(FeedbackParam feedbackParam){
        return feedbackDAO.updateFeedback(feedbackParam);
    }

    /**
     * 查询所有信息
     * @param feedbackBO
     * @return
     */
    public List<FeedbackBO> getFeedback(FeedbackBO feedbackBO){
        return feedbackDAO.getFeedback(feedbackBO);
    }
}
