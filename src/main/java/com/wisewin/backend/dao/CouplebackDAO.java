package com.wisewin.backend.dao;


import com.wisewin.backend.entity.bo.CouplebackBO;
import com.wisewin.backend.entity.param.CouplebackParam;

import java.util.List;

/**
 * 反馈
 */
public interface CouplebackDAO {

/**
 * 反馈查询
 */
    List<CouplebackBO>  queryCoupleback(CouplebackBO couplebackBO);
}
