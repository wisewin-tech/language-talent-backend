package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.HelpCenterBO;

import java.util.List;

public interface HelpCenterDAO {
    /**
     * 查询帮助中心
     * @return
     */
    List<HelpCenterBO> selectHelpCenter();

    /**
     * 增加帮助中心信息
     * @param helpCenterBO
     * @return
     */
    Integer insertHelpCenter(HelpCenterBO helpCenterBO);

    /**
     * 修改帮助中心信息
     * @param helpCenterBO
     * @return
     */
    boolean updateHelpCenter(HelpCenterBO helpCenterBO);

    /**
     * 删除帮助中心信息
     * @param id
     * @return
     */
    boolean deleteHelpContent(Integer id);

    /**
     * 查看文本详情
     * @param id
     * @return
     */
    HelpCenterBO getparticulars(Integer id);
}
