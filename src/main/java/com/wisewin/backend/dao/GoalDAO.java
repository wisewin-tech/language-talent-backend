package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.GoalBO;

import java.util.List;
import java.util.Map;

/**
 *  目的持久层
 *  Created by yyh on 2019/4/27
 */
public interface GoalDAO {

    /**
     * 添加目的
     *   String ppPurpose; //目的
     *   Integer adminId; //创建人
     *   Date ppReleasetime; //创建时间
     *   Date ppUpdatetime; //修改时间
     */
    Integer addGoal(GoalBO goalBO);

    /**
     *   显示目的
     *   String ppPurpose; //目的
     *   Integer adminId; //创建人
     *   Date ppReleasetime; //创建时间
     *   Date ppUpdatetime; //修改时间
     */
    List<GoalBO> queryGoal(GoalBO goalBO);

    /**
     *   修改目的
     *   String ppPurpose; //目的
     *   Integer adminId; //最后修改人id
     *   Date ppUpdatetime; //修改时间
     */
    Integer updateGoal(Map<String, Object> map);

    /**
     * 删除目的
     * Integer id //目的id
     */
    Integer deleteGoal(Integer id);
}
