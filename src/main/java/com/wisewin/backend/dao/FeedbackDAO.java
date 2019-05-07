package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.FeedbackBO;
import com.wisewin.backend.entity.param.FeedbackParam;

import java.util.List;
import java.util.Map;

public interface FeedbackDAO {
    /**
     * 通过状态查询意见反馈
     * @param map
     * @return
     */
    List<FeedbackBO> selectFeedback(Map<String, Object> map);


    /**
     *查询总条数
     * @param map
     * @return
     */
    Integer selectbylimitCount(Map<String, Object> map);

    /**
     * 修改意见反馈状态
     * @param feedbackParam
     * @return
     */
    boolean updateFeedback(FeedbackParam feedbackParam);

    /**
     * 查询所有信息
     * @param feedbackBO
     * @return
     */
    List<FeedbackBO> getFeedback(FeedbackBO feedbackBO);
}
