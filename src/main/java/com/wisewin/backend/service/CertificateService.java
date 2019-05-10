package com.wisewin.backend.service;

import com.wisewin.backend.common.constants.CourseConstants;
import com.wisewin.backend.dao.CertificateDAO;
import com.wisewin.backend.entity.bo.CertificateResultBO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

@Service("certificateService")
@Transactional
public class CertificateService {

    @Resource
    private CertificateDAO certificateDAO;

    /**
     * 查询用户证书
     * @param map
     * @return
     */
    public List<CertificateResultBO> selectUser(Map<String,Object> map){

        return certificateDAO.selectUser(map);
    }
    /**
     * 修改发送状态
     * @param id
     * @return
     */
    public void updateSend(Integer id){
        Map<String,Object> map=new HashMap<String, Object>();
//        map.put("userId",userId);
//        map.put("courseId",courseId);
//        根据需求释放注释,后期修改用
//        当userId,为空courseId不为空时,修改所有用户的courseId课时为已发送证书
//        当 courseId  ,为空 userId  不为空时,修改 userId 用户的所有课时为已发送证书
//        当userId,为空courseId为空时,修改所有用户的所有课时为已发送证书
//        当userId,不为空courseId不为空时,修改userId用户的 courseId 课时为已发送证书
        map.put("id",id);
        map.put("send","Sent");
         certificateDAO.updateSend(map);
    }


    /**
     * 查询是否有用户购买此证书记录
     */
    public boolean queryUserCount(Integer ceId){
        return  certificateDAO.queryUserCount(ceId)>0;
    }

    public void addCertificate(List<Integer> list,Integer ceId) {
        if(list!=null && list.size()>0){
            for(Integer ids:list){
                CertificateResultBO certificateResultBO=new CertificateResultBO();
                certificateResultBO.setCourseId(ids);// 用户id
                certificateResultBO.setCourseId(ceId);//课程id
                certificateResultBO.setSend(CourseConstants.CANNOT.getValue()); //未发送
                certificateResultBO.setStatus(CourseConstants.CANNOT.getValue()); //未考证
                certificateResultBO.setCreateTime(new Date());
                certificateDAO.addCertificate(certificateResultBO);

            }
        }
    }
}
