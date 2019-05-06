package com.wisewin.backend.service;

import com.wisewin.backend.dao.HelpCenterDAO;
import com.wisewin.backend.entity.bo.HelpCenterBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("helpCenterService")
@Transactional
public class HelpCenterService {
    @Resource
    private HelpCenterDAO helpCenterDAO;

    /**
     * 查询帮助中心
     *
     * @return
     */
    public List<HelpCenterBO> selectHelpCenter() {
        return helpCenterDAO.selectHelpCenter();
    }

    /**
     * 增加帮助中心信息
     *
     * @param helpCenterBO
     * @return
     */
    public Integer insertHelpCenter(HelpCenterBO helpCenterBO) {
        if (helpCenterBO.getTitle() == null || helpCenterBO.getContent() == null) {
            return 0;
        } else {
            return helpCenterDAO.insertHelpCenter(helpCenterBO);
        }
    }

    /**
     * 修改帮助中心信息
     *
     * @param helpCenterBO
     * @return
     */
    public boolean updateHelpCenter(HelpCenterBO helpCenterBO) {
        return helpCenterDAO.updateHelpCenter(helpCenterBO);
    }

    /**
     * 删除帮助中心信息
     *
     * @param id
     * @return
     */
    public boolean deleteHelpContent(Integer id) {
        return helpCenterDAO.deleteHelpContent(id);
    }

    /**
     * 查看文本详情
     * @param id
     * @return
     */
    public HelpCenterBO getparticulars(Integer id){
        return helpCenterDAO.getparticulars(id);
    }

}
