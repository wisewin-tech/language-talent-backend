package com.wisewin.backend.web.controller;

import com.wisewin.backend.common.constants.UserConstants;
import com.wisewin.backend.dao.TestDAO;
import com.wisewin.backend.entity.bo.UserBO;
import com.wisewin.backend.entity.bo.common.constants.SysConstants;
import com.wisewin.backend.entity.dto.ResultDTOBuilder;
import com.wisewin.backend.entity.param.UserParam;
import com.wisewin.backend.service.UserService;
import com.wisewin.backend.util.AccountValidatorUtil;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.ParamNullUtil;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.util.redisUtils.RedissonHandler;
import com.wisewin.backend.web.controller.base.BaseCotroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Shibo Sun
 *         主机controller
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseCotroller {

    static final Logger log = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestDAO testDAO;
    @Resource
    private UserService userService;
    /**
     * 手机号格式判断
     */
    private boolean phoneFormt(String phone,HttpServletResponse response)  {
        if (!(AccountValidatorUtil.isMobile(phone))){
                    //如果手机号格式不正确,提示.返回false
                    String json = JsonUtils.getJsonString4JavaPOJO
                            (ResultDTOBuilder.failure("0000001", "手机号格式不正确"));
               super.safeJsonPrint(response, json);

            return false;
        }
        //手机号格式通过,返回true
        return true;
    }

    /**
     * 将user对象存入到Cookie
     * @param response
     * @param userBO
     */
    private void putUser(HttpServletResponse response,UserBO userBO){
        String uuid = UUID.randomUUID().toString();
        super.putLoginUser(uuid, userBO);
        super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid,24 * 60 * 60);
    }


    /**
     * 发送验证码
     * @param phone
     *
     */
    @RequestMapping("/send")
    public void send(String phone,HttpServletResponse response)  {
        //手机号非空+格式判断
        if (this.phoneFormt(phone,response)){
            userService.send(phone);
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("验证码发送成功!"));
            super.safeJsonPrint(response, json);
        }


}

    /**
     * 手机号验证通过,对比用户验证码,登录或添加用户信息
     * @param phone
     * @param verify 用户验证码
     */
    //用户注册或者登录
    @RequestMapping("/register")
    public void register(String phone,String verify,HttpServletResponse response,HttpServletRequest request){
        //手机号非空+格式判断
        this.phoneFormt(phone,response);
        //用户传参非空判断
        if (StringUtils.isEmpty(verify)) {
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
            return;
        }
        //获取Redis中的用户验证码
        String mobileAuthCode = RedissonHandler.getInstance().get(phone + UserConstants.VERIFY.getValue());
        System.out.println("register方法中,缓存中的验证码为"+mobileAuthCode);
        //如果和用户收到的验证码相同
        if(verify.equals(mobileAuthCode)){
           //通过手机号查询表中是否有该用户
             UserBO userBO = userService.selectPhone(phone);
            if(userBO!=null){
                //user对象存入cookie中
                this.putUser(response,userBO);
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("登录成功!"));
                super.safeJsonPrint(response, json);

            }else{ //如果表里没有该用户,添加用户手机号,把带有手机号的user对象存入cookie中,登录成功,
                UserBO userBO1=new UserBO();
                userBO1.setMobile(phone);
                userService.insertUser(userBO1);
                userBO1 = userService.selectPhone(phone);
                //将只带有带有手机号的user对象存入cookie中
                this.putUser(response,userBO1);
                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加信息完成,登录成功"));
                super.safeJsonPrint(response, json);
            }
        }else{
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "验证码错误"));
            super.safeJsonPrint(response, json);
        }
    }

    /**
     *
     * @param response
     * @param request
     */
    @RequestMapping("/update")
    public void updateUser(HttpServletResponse response, HttpServletRequest request, UserParam userParam) {
        //从cookie中获取他的user对象的id
        Integer id=this.getId(request);
        //如果获取不到,参数异常
        if (id==null){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }
        //如果获取到了,判断user参数不为空
        if (ParamNullUtil.checkObjAllFieldsIsNull(userParam)){
            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001"));
            super.safeJsonPrint(response, json);
        }
        //把id设置到user参数对象中
        userParam.setId(id);
        //修改信息
        userService.updateUser(userParam);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改信息成功"));
        super.safeJsonPrint(response, json);
    }

//    /**
//     * 注册完以后,只添加了手机信息,完善用户信息
//     * @param name
//     * @param birthday
//     * @param gender
//     * @param password
//     */
//    @RequestMapping("/replenishUser")
//    public void replenishUser(String name,String password,String gender,String birthday,
//                             HttpServletResponse response,HttpServletRequest request){
//        if (name == null||birthday == null||gender == null||super.getId(request) == null||password==null) {
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数不正确"));
//            super.safeJsonPrint(response, json);
//        }
//        System.out.println("replenishUser方法中的user对象的id"+super.getId(request));
//
//        if (userService.replenishUser(super.getId(request),name,  password,  gender, birthday)){
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("信息完善成功"));
//            super.safeJsonPrint(response, json);
//        }else{
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "该用户名已存在"));
//            super.safeJsonPrint(response, json);
//        }
//}
//    /**
//     * @description 生成图片验证码
//     */
//    @RequestMapping("/imageVer")
//    @ResponseBody
//    public void verification(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
//        // 设置响应的类型格式为图片格式
//        response.setContentType("image/jpeg");
//        // 禁止图像缓存。
//        response.setHeader("Pragma", "no-cache");
//        response.setHeader("Cache-Control", "no-cache");
//        response.setDateHeader("Expires", 0);
//        //实例生成验证码对象
//        SCaptcha instance = new SCaptcha();
//        //将验证码存入session
//       // session.setAttribute("verification", instance.getCode());
//        //向页面输出验证码图片
//        //instance.write(response.getOutputStream());
//        System.out.println("图片验证码为:"+instance.getCode());
//        // 保存验证码信息到Redis
//        //RedissonHandler.getInstance().set("??" + "imageverify", instance.getCode(), 180L);
//        //获取缓存中验证码
//        //String mobileAuthCode = RedissonHandler.getInstance().get("??" + "imageverify");
//
//    }
//    /**
//     * 修改用户信息
//     * 注册或者登陆后才能修改用户信息,
//     * @param name
//     */
//    @RequestMapping("/updateUser")
//    public void updateUser(String name,Integer gender,String birthday,
//                           HttpServletResponse response,HttpServletRequest  request){
//        //参数非空验证
//        if (name == null||birthday == null||gender == null||super.getId(request) == null) {
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数不正确"));
//            super.safeJsonPrint(response, json);
//        }
//            //如果用户名不重复,修改信息成功
//        if (userService.updateUser(super.getId(request),name,  gender, birthday)){
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("修改用户信息成功"));
//            super.safeJsonPrint(response, json);
//
//        }else{
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "该用户名已存在"));
//            super.safeJsonPrint(response, json);
//        }
//
//
//    }
//
//    /**
//     * 账号密码登录
//     * @param response
//     */
//    @RequestMapping("/login")
//    public void login(String name, String password, HttpServletResponse response,HttpServletRequest request){
//        //用户传参非空判断
//        if (StringUtils.isEmpty(name)||StringUtils.isEmpty(password)) {
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数不正确"));
//            super.safeJsonPrint(response, json);
//            return;
//        }
//        // 通过用户名判断用户是否存在
//        UserBO user
//                = userService.selectUserByUsername(name);
//        password= MD5Util.digest(password);
//        // 执行登录
//        /*
//        1.如果存在,判断密码正确与否
//        2.如果不存在,提示没有该用户
//         */
//        if (user!=null){
//            if(user.getPassword().equals(password)) { //密码正确
//
//                //将user对象存入到Cookie
//                this.putUser(response,user);
//
//                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("用户登录成功"));
//                super.safeJsonPrint(response, json);
//
//            }else{//否则,提示密码错误
//                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "密码错误"));
//                super.safeJsonPrint(response, json);
//            }
//
//        }else{
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "该用户不存在"));
//            super.safeJsonPrint(response, json);
//        }
//
//
//        }
//    /**
//     * 手机号登录
//     * @param phone
//     * @param verify 用户验证码
//     */
//    @RequestMapping("/loginforphone")
//    public void loginforphone(String phone,String verify,HttpServletResponse response) {
//        //手机号非空+格式判断
//        this.phoneFormt(phone, response);
//        //用户传参非空判断
//        if (StringUtils.isEmpty(verify)) {
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数不正确"));
//            super.safeJsonPrint(response, json);
//            return;
//        }
//        //通过手机号查询表中是否有该用户
//        if (userService.selectPhone(phone)!=null) {  //有,判断验证码
//
//            String mobileAuthCode = RedissonHandler.getInstance().get(phone + "_register");
//            System.out.println("缓存中的验证码为" + mobileAuthCode);
//            //如果和用户收到的验证码相同
//            if(verify.equals(mobileAuthCode)){
//                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("登录成功"));
//                super.safeJsonPrint(response, json);
//                //通过手机号获取user对象
//                UserBO userBO=userService.selectPhone(phone);
//                //将user对象存入到Cookie
//                String uuid = UUID.randomUUID().toString();
//                super.putLoginUser(uuid, userBO);
//                super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid,24 * 60 * 60);
//
//            }else{  //如果和用户收到的验证码不相同
//                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "验证码错误"));
//                super.safeJsonPrint(response, json);
//            }
//        }else{  //没有,提示该用户不存在
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "该用户不存在"));
//            super.safeJsonPrint(response, json);
//            return;
//        }
//
//    }
//
//    /**
//     * 忘记密码,手机号验证修改密码
//     */
//    @RequestMapping("/forgetPassword")
//    public void forgetPassword(String phone,String verify,String newpassword,HttpServletResponse response){
//        //手机号非空+格式判断
//        this.phoneFormt(phone, response);
//        //用户传参非空判断
//        if (StringUtils.isEmpty(verify)||StringUtils.isEmpty(newpassword)) {
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数不正确"));
//            super.safeJsonPrint(response, json);
//            return;
//        }
//        //通过手机号查询表中是否有该用户
//        if (userService.selectPhone(phone)!=null) {  //有,判断验证码
//
//            String mobileAuthCode = RedissonHandler.getInstance().get(phone + "_register");
//            System.out.println("缓存中的验证码为" + mobileAuthCode);
//            //如果和用户收到的验证码相同
//            if(verify.equals(mobileAuthCode)){
//                //修改密码
//                newpassword=MD5Util.digest(newpassword);//新密码加密
//                userService.updatePassword(phone,newpassword);
//                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("密码找回成功!"));
//                super.safeJsonPrint(response, json);
//            }else{  //如果和用户收到的验证码不相同
//                String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "验证码错误"));
//                super.safeJsonPrint(response, json);
//            }
//        }else{  //没有,提示该用户不存在
//            String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "该用户不存在"));
//            super.safeJsonPrint(response, json);
//            return;
//        }
//    }





    @RequestMapping("/test")
    public void test (HttpServletResponse response) {
//        hostDAO.insertHost(hostDO);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(testDAO.test()));
        super.safeJsonPrint(response, json);

    }

    public static void main(String[] args) throws ParseException {
       String startTime = "2018-11-06 11:00:00";
        String endTime = "2018-11-08 11:15:00";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:ss:mm");
        Date d1 = df.parse(startTime);
        Date d2 = df.parse(endTime);
        System.out.println(BigDecimal.valueOf(Math.ceil((((double) d2.getTime() - (double) d1.getTime()) / 3600000)% 24)));
        System.out.println(BigDecimal.valueOf(Math.floor(((double) d2.getTime() - (double) d1.getTime()) / (3600000 * 24))));

        System.out.println(BigDecimal.valueOf(Math.ceil((((double) d2.getTime() - (double) d1.getTime()-(double)900000) / 3600000)% 24)));
        System.out.println(BigDecimal.valueOf(Math.floor(((double) d2.getTime() - (double) d1.getTime()-(double)900000) / (3600000 * 24))));
       /* BigDecimal b=new BigDecimal(45.45);
        int a = b.intValue();
        System.out.print(a);*//*
        BigDecimal timeOut =BigDecimal.valueOf(0);
        BigDecimal price =BigDecimal.valueOf(100);
        BigDecimal hours = BigDecimal.valueOf(2.0);
        timeOut=timeOut.add(price.multiply(hours));
        System.out.print(timeOut);*/
       /* String[] strs = {"aa,bb","bb,bb","cc"};
         //String数组转List
        List<String> strsToList1= Arrays.asList(strs);
        System.out.print(strsToList1);*/
      /*double  time= 1.62113639E8;
        double s =Math.floor(time/(24 * 60 * 60 * 1000));
        System.out.print(s);

         BigDecimal timeOut =BigDecimal.valueOf(0);
        timeOut=timeDay.add(typeBO.getPrice());*/
    }
}
