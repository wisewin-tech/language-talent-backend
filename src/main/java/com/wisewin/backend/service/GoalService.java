package com.wisewin.backend.service;


import com.wisewin.backend.dao.GoalDAO;
import com.wisewin.backend.entity.bo.GoalBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("PurposeBOService")
@Transactional
public class GoalService {

    @Resource
    private GoalDAO goalDAO;


    /**
     * 添加目的
     *   String ppPurpose; //目的
     *   Integer adminId; //创建人
     *   Date ppReleasetime; //创建时间
     *   Date ppUpdatetime; //修改时间
     */
    public boolean getaddGoal(String ppPurpose,Integer adminId,Double rank){
        GoalBO goalBO =new GoalBO(ppPurpose,adminId,rank);
        return goalDAO.addGoal(goalBO)>0;
    }
    /**
     *   显示目的
     *   Integer id
     *   String ppPurpose; //目的
     *   Integer adminId; //创建人
     *   Date ppReleasetime; //创建时间
     *   Date ppUpdatetime; //修改时间
     *
     */
    public List<GoalBO> getqueryGoal(Integer id,String ppPurpose, Integer adminId, Date ppReleasetime,Date ppUpdatetime,Double rank){
        GoalBO goalBO=new GoalBO(id,ppPurpose,adminId,ppReleasetime,ppUpdatetime,rank);
        return goalDAO.queryGoal(goalBO);
    }

    /**
     *
     *   修改语言目的
     *   Integer id;      //目的id
     *   String ppPurpose; //目的
     *   Integer adminId; //最后修改人id
     */
    public boolean getupdateGoal(Integer id,String ppPurpose,Integer adminId,Double rank){
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("id",id);
        map.put("ppPurpose",ppPurpose);
        map.put("adminId",adminId);
        map.put("rank",rank);

        return goalDAO.updateGoal(map)>0;
    }

    /**
     * 删除目的
     * Integer id //目的id
     */
    public boolean getdeleteGoal(Integer id){

        return goalDAO.deleteGoal(id)>0;
    }
}
