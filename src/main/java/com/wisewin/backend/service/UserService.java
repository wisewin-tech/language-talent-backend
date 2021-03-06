package com.wisewin.backend.service;



import com.wisewin.backend.common.constants.UserConstants;
import com.wisewin.backend.dao.UserDAO;
import com.wisewin.backend.entity.bo.InvitationRecordBO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.RandomUtils;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.util.message.SendMessageUtil;
import com.wisewin.backend.util.redisUtils.RedissonHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class UserService {
    @Resource
    private UserDAO userDAO;

    /**
     * 发送验证码
     *
     * @param phone
     */
    public void send(String phone) {
        //验证手机号格式
        String number = RandomUtils.getRandomNumber(6);
        //发送验证码
        SendMessageUtil.sendSignInCodeMessage(phone, number);
        // 保存验证码信息到Redis
        RedissonHandler.getInstance().set(phone + UserConstants.VERIFY.getValue(), number, 180L);
        //获取缓存中验证码
        String mobileAuthCode = RedissonHandler.getInstance().get(phone + UserConstants.VERIFY.getValue());
        System.out.println("send方法缓存中的验证码为" + mobileAuthCode);
    }


    /**
     * 通过手机号查询用户信息
     */
    public UserBO selectPhone(String phone) {
        System.out.println(phone);
        System.out.println( "UserBO对象:" +userDAO.selectByPhone(phone));
        return userDAO.selectByPhone(phone) ;

    }


    /**
     * 添加用户信息
     *
     * @param userBO
     */
    public void insertUser(UserBO userBO) {

        userDAO.insertUser(userBO);
    }

    /**
     * 修改用户信息
     * @param userParam
     */
    public void updateUser( UserParam userParam) {
        String password=userParam.getPassword();
        //如果用户有修改密码,对密码进行加密
        if (!StringUtils.isEmpty(password)){
            userParam.setPassword(MD5Util.digest(password));
        }
        userDAO.updateUser(userParam);

    }

    /**
     * 条件分页查询用户信息
     * @param map
     */
    public List<UserBO> selectUsers(Map<String,Object> map) {
       return userDAO.selectUsers(map);
    }

    /**
     * 批量删除用户
     * @param idArr
     */
    public Integer deleteUsersById(Integer[] idArr,String status) {
        return userDAO.deleteUsersById(idArr,status);
    }

    /**
     *
     * 条件分页查询用户个数
     * @param map
     * @return
     */
    public Integer selectUsersCount(Map<String,Object> map){
        return userDAO.selectUsersCount(map);
    }

    /**
     * 查询用户邀请记录
     */
    public List<UserBO> getInvitationRecord(Integer id,Integer pageNo,Integer pageSize){
        return userDAO.getInvitationRecord(id,pageNo,pageSize);
    }
    /**
     * 查询用户邀请记录数
     */
    public Integer getInvitationRecordCount(Integer id){
        return userDAO.getInvitationRecordCount(id);
    }
    /**
     * 查询所有用户邀请记录group by byr.mobile
     */
    public List<InvitationRecordBO> getAllInvitationRecord(Map<String,Object> map){
        //获取到邀请记录
        List<InvitationRecordBO> result=userDAO.getAllInvitationRecord(map);
        return result;
    }

    /**getAllInvitationRecord
     * 查询用户邀请记录数
     */
    public Integer getAllInvitationRecordCount(Map<String,Object> map){

        return userDAO.getAllInvitationRecordCount(map).size();
    }
}
