package com.wisewin.backend.web.controller;


import com.wisewin.backend.common.constants.LanguageTalentConstants;
import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.MenuBO;
import com.wisewin.backend.entity.bo.RoleBO;
import com.wisewin.backend.entity.bo.common.constants.SysConstants;
import com.wisewin.backend.entity.dto.*;
import com.wisewin.backend.entity.param.GetAdminParam;
import com.wisewin.backend.entity.param.MenuParam;
import com.wisewin.backend.entity.param.RegisterParam;
import com.wisewin.backend.query.QueryInfo;
import com.wisewin.backend.service.AdminService;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.JsonUtils;
import com.wisewin.backend.util.MD5Util;
import com.wisewin.backend.util.StringUtils;
import com.wisewin.backend.util.redisUtils.RedissonHandler;
import com.wisewin.backend.web.controller.base.BaseCotroller;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用戶信息控制类  add  del  edit get
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseCotroller {
    @Resource
    private AdminService adminService ;
    @Resource
    private LogService logService;

    /**
     * 管理员登录
     * @param request
     * @param response
     * @param mobile 手机号
     * @param password 密码
     */
    @RequestMapping("/adminLogin")
    public void Login(HttpServletRequest request, HttpServletResponse response,String mobile,String password){
        logService.startController(null,request,mobile,password);
        /* 1. 验证参数是否完整 */
        if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(password)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001", "参数异常！")) ;
            logService.end("admin/adminLogin",result);
            super.safeJsonPrint(response, result);
            return ;
        }
        //手机号和密码登录
        logService.call("adminService.queryAdminInfoByMobile()",mobile);
        AdminBO adminBO = adminService.queryAdminInfoByMobile(mobile);
        if(adminBO == null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000004" , "用户不存在！")) ;
            logService.end(result);
            super.safeJsonPrint(response, result);
            return ;
        }

        // 判断密码是否正确
        if(!MD5Util.digest(password).equals(adminBO.getPassword())){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "密码输入不正确！")) ;
            logService.end("admin/adminLogin/",result);
            super.safeJsonPrint(response, result);
            return ;
        }
        adminBO.setPassword("");
        //该用户的所有菜单
        logService.call("adminService.getRoleMenuSuccess()",adminBO.getRoleId());
        List<RoleBO> roleMenuSuccess = adminService.getRoleMenuSuccess(adminBO.getRoleId());
        logService.result(roleMenuSuccess);
        // 登陆客户信息放入Redis缓存
        String uuid = UUID.randomUUID().toString();
        super.putLoginAdmin(uuid,adminBO);
        super.setCookie(response, SysConstants.CURRENT_LOGIN_CLIENT_ID, uuid, 86400);

        Map<String,Object>  resultMap=new HashMap<String, Object>();
        resultMap.put("menu",roleMenuSuccess);
        resultMap.put("admin",adminBO);

        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(resultMap)) ;
        logService.end("/admin/adminLogin",result);
        super.safeJsonPrint(response, result);
        return;
    }



    /**
     * 管理员注册
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("/adminRegister")
    public void register(HttpServletRequest request,HttpServletResponse response,RegisterParam param){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);
        // 判断是否为空
        if(param == null || StringUtils.isEmpty(param.getPassword()) || StringUtils.isEmpty(param.getGender())
                || StringUtils.isEmpty(param.getMobile()) || StringUtils.isEmpty(param.getName()) || StringUtils.isEmpty(String.valueOf(param.getRoleId()))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            logService.end("admin/adminRegister",result);
            super.safeJsonPrint(response , result);
            return ;
        }

        // 判断手机号是否注册过
        logService.call("adminService.selectCountByMobile()",param.getMobile());
        int count = adminService.selectCountByMobile(param.getMobile());
        logService.result(count);
        if(count > 0 ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "管理员账号重复")) ;
            logService.end("/admin/adminRegister",result);
            super.safeJsonPrint(response , result);
            return ;
        }

        // 判断用户名是否注册过
        logService.call("adminService.selectCountByName()",param.getName());
        int name = adminService.selectCountByName(param.getName());
        logService.result(name);
        if(name > 0 ){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "用户名已存在")) ;
            logService.end("/admin/adminRegister",result);
            super.safeJsonPrint(response , result);
            return ;
        }
        AdminBO admin = new AdminBO();
        admin.setPassword(MD5Util.digest(param.getPassword()));
        admin.setName(param.getName());
        admin.setGender(param.getGender()); //
        admin.setPhoneNumber(param.getMobile());
        admin.setStatus(LanguageTalentConstants.NORMAL);// 状态 normal:正常  logout：注销
        admin.setCreateTime(new Date());
        admin.setRoleId(param.getRoleId());
        admin.setEmail(param.getEmail());
        logService.call("adminService.adminRegister()",admin);
        adminService.adminRegister(admin);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
        logService.end("/admin/adminRegister",result);
        super.safeJsonPrint(response, result);
    }

    /**
     * 添加角色
     * @param request
     * @param response
     * @param roleName  角色名称
     */
    @RequestMapping("/addRole")
    public void addRole(HttpServletRequest request,HttpServletResponse response,String roleName){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,roleName);
        // 非空判断
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/addRole",result);
            return ;
        }
        // 判断角色名称是否存在
        logService.call("adminService.selectCountByRoleName()",roleName);
        Integer count = adminService.selectCountByRoleName(roleName);

        if(count>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该角色已存在")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/addRole",result);
            return ;
        }
        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(roleName);
        roleBO.setCreateTime(new Date());
        roleBO.setUpdateTime(new Date());
        logService.call("adminService.addRole()",roleBO);
        adminService.addRole(roleBO);

        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleBO.getId())) ;
        logService.end("/admin/addRole",result);
        super.safeJsonPrint(response, result);
    }

    /**
     * 添加角色 就给角色赋予权限
     * @param request
     * @param response
     * @param roleName  角色名称
     * @param menuIds  权限ids
     */
    @RequestMapping("/addRoleGrantAuthority")
    public void addRoleGrantAuthority(HttpServletRequest request,HttpServletResponse response,String roleName,String menuIds,Integer pageNo, Integer pageSize){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,roleName,menuIds,pageNo,pageNo,pageSize);
        // 非空判断
        if(StringUtils.isEmpty(roleName) || StringUtils.isEmpty(menuIds)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常"+"roleName:"+roleName +"menuIds:"+menuIds)) ;
            logService.end(result);
            super.safeJsonPrint(response , result);
            return ;
        }
        // 判断角色名称是否存在
        logService.call("adminService.selectCountByRoleName()",roleName);
        Integer nameCount = adminService.selectCountByRoleName(roleName);
        if(nameCount>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该角色已存在")) ;
            logService.end("/admin/addRole",result);
            super.safeJsonPrint(response , result);
            return ;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());
        }
        map.put("roleName", roleName);
        // 查询角色所拥有的权限
        logService.call("adminService.selectRoleToMenu()",map,roleName,menuIds);
        List<RoleDTO> roleDTOS = adminService.selectRoleToMenu(map,roleName,menuIds);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleDTOS)) ;
        logService.end("/admin/addRole",result);
        super.safeJsonPrint(response, result);
    }

    /** faild
     * 查询角色对应的权限
     * @param request
     * @param response
     * @param roleName
     */
    @RequestMapping("/getRoleMenu")
    public void getRoleMenu(HttpServletRequest request,HttpServletResponse response,String roleName,String menuIds, Integer pageNo, Integer pageSize){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,roleName,menuIds,menuIds,pageNo,pageSize);
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            logService.end("/admin/getRoleMenu",result);
            super.safeJsonPrint(response , result);
            return ;
        }
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        }
        map.put("roleName", roleName);
        // 查询总记录数
        Integer count = adminService.getCountRoleToMenu(map);

        // 查询角色所拥有的权限
        List<RoleDTO> menuList = adminService.selectRoleToMenu(map,roleName,menuIds);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", menuList);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonObject));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
        logService.end("/admin/getRoleMenu",result);
        super.safeJsonPrint(response, result);
    }

    /**
     * 编辑对应角色的权限
     * 首先获取到给哪个角色赋予权限(获取到角色的id),准备赋予哪个权限(获取到权限id),然后将信息保存到角色权限表
     * @param request
     * @param response
     * @param roleId  角色id
     * @param menuIds  权限id
     */
    @RequestMapping("/grantAuthority")
    public void grantAuthority(HttpServletRequest request,HttpServletResponse response,Integer roleId,String menuIds,String roleName){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,roleId,menuIds,roleName);
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(roleId)) || StringUtils.isEmpty(String.valueOf(menuIds))|| StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常 roleId:"+roleId+"menuIds:"+menuIds+"roleName:"+roleName)) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/grantAuthority",result);
            return ;
        }
        // 更新角色名
        if(!StringUtils.isEmpty(roleName)){
            // 查询角色传的和数据库查的是否一样，一样设置未空
            logService.call("adminService.getRoleById()",roleId);
            RoleBO roleBO = adminService.getRoleById(roleId);
            if(roleBO.getRoleName().equals(roleName)){
                roleName="";
            }else{
                // 不一致查询是否重复
                Integer nameCount = adminService.selectCountByRoleName(roleName);
                if(nameCount>0){
                    String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000002" , "该角色已存在")) ;
                    super.safeJsonPrint(response , result);
                    logService.end("/admin/grantAuthority",result);
                    return ;
                }
            }
            logService.call("adminService.updateRoleNameByRoleId()",roleId);
            adminService.updateRoleNameByRoleId(roleId,roleName,new Date());
        }
        logService.call("adminService.selectRoleMenuById()",roleId,menuIds);
        List<RoleDTO> roleDTOS = adminService.selectRoleMenuById(roleId,menuIds);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleDTOS)) ;
        logService.end("/admin/grantAuthority",result);
        super.safeJsonPrint(response, result);
    }

    /**
     * 查询所有角色
     * @return  所有角色信息
     */
    @RequestMapping("/getRoleList")
    public void RoleList(HttpServletRequest request,HttpServletResponse response){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request);
        logService.call("adminService.getRoleList()");
        List<RoleBO> roleList = adminService.getRoleList();
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(roleList)) ;
        logService.end("/admin/getRoleList",result);
        super.safeJsonPrint(response, result);
    }

    /**
     * 查询所有权限(菜单)
     * @return 所有菜单
     */
    @RequestMapping("/getMenuList")
    public void MenuList(HttpServletRequest request,HttpServletResponse response){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request);
        logService.call("adminService.getMenuList()");
        List<MenuBO> menuList = adminService.getMenuList();
        // JsonUtils.getJONSArray4JavaList(roleList);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        logService.end("/admin/getMenuList",result);
        super.safeJsonPrint(response, result);
    }

    /**
     * 根据父id向权限表中添加菜单
     * @param request
     * @param response
     * @param menuParam
     */
    @RequestMapping("/addMenu")
    public void addMenuByPid(HttpServletRequest request,HttpServletResponse response,MenuParam menuParam){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,menuParam);
        // 判断是否为空
        if(menuParam == null || StringUtils.isEmpty(menuParam.getMenuName()) || StringUtils.isEmpty(menuParam.getUrl())){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/addMenu",result);
            return ;
        }
        if( StringUtils.isEmpty(String.valueOf(menuParam.getPid()))){
            menuParam.setPid(0);
        }
        boolean flag = adminService.selectCountMenuName(menuParam.getMenuName());
        if(flag){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "菜单已存在")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/addMenu",result);
            return ;
        }
        logService.call("adminService.addMenuByPid()");
        int line = adminService.addMenuByPid(menuParam);
        if(line>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("添加成功")) ;
            logService.end("/admin/addMenu",result);
            super.safeJsonPrint(response, result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("添加失败")) ;
            logService.end("/admin/addMenu",result);
            super.safeJsonPrint(response, result);
        }

    }

    /**
     * 删除菜单 如果有子id就提示，如果没有就删除
     * @param request
     * @param response
     * @param id 删除的菜单id
     */
    @RequestMapping("/delMenu")
    public void delMenu(HttpServletRequest request,HttpServletResponse response,Integer id){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,id);
        if(StringUtils.isEmpty(String.valueOf(id))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/delMenu",result);
            return ;
        }
        logService.call("adminService.delMenuById()",id);
        boolean flag = adminService.delMenuById(id);
        if(flag){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/delMenu",result);
            return ;
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "删除失败")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/delMenu",result);
            return ;
        }
    }

    /**faild
     * 根据id 编辑权限信息
     * @param request
     * @param response
     * @param menuParam
     */
    @RequestMapping("/editMenu")
    public void editMenu(HttpServletRequest request,HttpServletResponse response,MenuParam menuParam){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,menuParam);
        if(menuParam==null || menuParam.getId()==null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/editMenu",result);
            return ;
        }
        logService.call("adminService.updateMenuById()",menuParam);
        int line = adminService.updateMenuById(menuParam);
        if(line>0){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/editMenu",result);
            return ;
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失敗")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/editMenu",result);
            return ;
        }
    }

    /**
     * 模糊查询角色信息
     * @param request
     * @param response
     * @param dimName 模糊的名字
     */
    @RequestMapping("/getDimRoleMenu")
    public void getDimRoleMenu(HttpServletRequest request,HttpServletResponse response,String dimName){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,dimName);
        if(StringUtils.isEmpty(dimName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/getDimRoleMenu",result);
            return ;
        }
        logService.call("adminService.getDimRoleMenu",dimName);
        List<MenuDTO> menuList = adminService.getDimRoleMenu(dimName);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        logService.end("/admin/getDimRoleMenu",result);
        super.safeJsonPrint(response, result);
    }


    /**
     * 删除角色信息 根据角色id
     * @param request
     * @param roleIds
     */
    @RequestMapping("/delRoleByIds")
    public void delRoleByIds(HttpServletRequest request,HttpServletResponse response,String roleIds){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,roleIds);
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(roleIds))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/delRoleByIds",result);
            return ;
        }
        //判断该角色下是否用户
        String s = adminService.existsUser(roleIds);
        if(s!=null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000014" , s)) ;
            super.safeJsonPrint(response , result);
            return;
        }


        boolean flag = adminService.delRoleById(roleIds);
        if(flag){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功")) ;
            super.safeJsonPrint(response, result);
            logService.end("/admin/delRoleByIds",result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("删除有误")) ;
            super.safeJsonPrint(response, result);
            logService.end("/admin/delRoleByIds",result);
        }

    }

    /** success
     * 根据角色名称查询角色对应的权限(如果传的角色名是空，则查询所有角色对应的权限)
     * @param request
     * @param response
     */
    @RequestMapping("/getAllRoleMenu")
    public void getAllRoleMenu(HttpServletRequest request,HttpServletResponse response,String roleName,Integer pageNo, Integer pageSize){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        }
        map.put("roleName", roleName);
        // 查询总记录数
        //Integer count = adminService.getCountRoleToMenu(map);
        // Integer count = adminService.getCountRole(map);
        // 查询角色所拥有的权限
        // List<RoleBO> menuList = adminService.selectRoleToMenu(map);

        List<RoleDTO> ros = adminService.getRole(roleName);
        if(ros == null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "没有查到相关记录")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        /*JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", roleDTOs);*/
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(ros));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
        super.safeJsonPrint(response, result);
    }

    @RequestMapping("/getRoleTest")
    public void Test(HttpServletRequest request,HttpServletResponse response,String roleName){
        if(StringUtils.isEmpty(roleName)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
//        List<RoleBO> list = adminService.getRole(new Map<>);
//        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(list)) ;
//        super.safeJsonPrint(response, result);
    }

    /** faild
     * 查询用户所对应的角色
     * @param request
     * @param response
     * @param userName 用戶名
     */
    @RequestMapping("/getAdminRoleByName")
    public void getAdminRoleByName(HttpServletRequest request,HttpServletResponse response,String userName,Integer pageNo, Integer pageSize){

        // 查询用户和对应的角色
        List<AdminRoleDTO> adminRoleDTOS = adminService.getAdminRoleByName(userName);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(adminRoleDTOS)) ;
        super.safeJsonPrint(response, result);
    }

    /** faild
     * 编辑用户所对应的角色
     * @param request
     * @param response
     * @param id 用戶id
     * @param roleId 角色id
     */
    @RequestMapping("/editUserRole")
    public void editUserRole(HttpServletRequest request,HttpServletResponse response,Integer id,Integer roleId){
        // 非空判断
        if(StringUtils.isEmpty(String.valueOf(id))|| StringUtils.isEmpty(String.valueOf(roleId))){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        boolean boo = adminService.editUserRole(roleId, id);
        if(boo){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改有误")) ;
            super.safeJsonPrint(response , result);
        }
    }

    /**
     * 修改admin用戶信息
     * @param request
     * @param response
     * @param param
     * @param
     */
    @RequestMapping("/updateAdminUser")
    public void updateAdminUser(HttpServletRequest request,HttpServletResponse response,RegisterParam param,Integer pageNo,Integer pageSize){
        if(param == null){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }

        boolean flag = adminService.updateAdminUser(param);
        if(flag){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success( "修改成功")) ;
            super.safeJsonPrint(response , result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "用户名以存在")) ;
            super.safeJsonPrint(response , result);
        }

    }

    /**
     * 修改密码
     * @param request
     * @param response
     * @param param
     */
    @RequestMapping("/changePassword")
    public void changePassword(HttpServletRequest request,HttpServletResponse response,RegisterParam param){
        // 非空判断
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,param);
        if(StringUtils.isEmpty(String.valueOf(param.getId()))|| StringUtils.isEmpty(param.getPassword())){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/changePassword",result);
            return ;
        }
        AdminBO adminBO = new AdminBO();
        adminBO.setId(param.getId());
        logService.call("adminService.getAdminNoFenYe()",adminBO);
        List<AdminDTO> adminDTOS = adminService.getAdminNoFenYe(adminBO);
        logService.result(adminBO);
        for (AdminDTO admin: adminDTOS ) {
            if(admin.getPassword().equals(MD5Util.digest(param.getPassword()))){
                String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "新密码和旧密码不能相同")) ;
                super.safeJsonPrint(response , result);
                logService.end("/admin/changePassword",result);
                return ;
            }
        }
        adminBO.setPassword(MD5Util.digest(param.getPassword()));
        logService.call("adminService.updateAdminUser()",param);
        boolean flag = adminService.updateAdminUser(param);
        if(flag){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success( "修改成功")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/changePassword",result);
        }else{
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "修改失败")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/changePassword",result);
        }

    }

    /**
     * 查询用户信息
     * @param request
     * @param response
     * @param adminParam
     */
    @RequestMapping("/getAdmin")
    public void getAdmin(HttpServletRequest request, HttpServletResponse response, GetAdminParam adminParam, Integer pageNo, Integer pageSize){
        QueryInfo queryInfo = getQueryInfo(pageNo, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        if  (queryInfo != null) {
            map.put("pageOffset", queryInfo.getPageOffset());
            map.put("pageSize", queryInfo.getPageSize());

        }

        if(!StringUtils.isEmpty(adminParam.getMobile())){
            map.put("mobile",adminParam.getMobile());
        }
        if(!StringUtils.isEmpty(adminParam.getName())){
            map.put("userName", adminParam.getName());
        }
        if(!StringUtils.isEmpty(String.valueOf(adminParam.getId()))){
            map.put("id", adminParam.getId());
        }
        if(!StringUtils.isEmpty(String.valueOf(adminParam.getRoleId()))){
            map.put("roleId", adminParam.getRoleId());
        }
        if(!StringUtils.isEmpty(adminParam.getEmail())){
            map.put("email", adminParam.getEmail());
        }
        if(!StringUtils.isEmpty(adminParam.getRoleName())){
            map.put("roleName", adminParam.getRoleName());
        }
        // 查询总记录数
        Integer count = adminService.getAdminCountByMap(map);
        // 根据map中的数据查询用户信息
        List<AdminDTO> adminBOS = adminService.getAdmin(map);
        for (int i=0;i<adminBOS.size();i++
             ) {
            adminBOS.get(i).setPassword("");
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("count", count);
        jsonObject.put("data", adminBOS);
        String json = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(jsonObject));
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(json)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 删除某个角色对应的权限
     * @param roleId 角色id
     * @param menuIds 权限id
     * @return 是否删除成功
     */
    @RequestMapping("/delRoleMenu")
    public void delRoleMenu(HttpServletRequest request,HttpServletResponse response,Integer roleId,String menuIds){
        if(roleId==null || StringUtils.isEmpty(menuIds)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        boolean flag = menuIds.contains(",");
        if(flag){
            String [] ids = menuIds.split(",");
            for (String id:ids ) {
                adminService.delRoleMenu(roleId,Integer.parseInt(id));
            }
        }else{
            adminService.delRoleMenu(roleId,Integer.parseInt(menuIds));
        }
        // 查询角色所拥有的权限
        List<RoleDTO> menuList = adminService.selectRoleMenuById(roleId,menuIds);
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success(menuList)) ;
        super.safeJsonPrint(response, result);
    }

    /**
     * 删除用户
     * @param request
     * @param response
     * @param ids 需要刪除的用戶的id
     */
    @RequestMapping("/delAdmin")
    public void delAdmin(HttpServletRequest request,HttpServletResponse response,String ids){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request,ids);
        // 非空判断
        if(StringUtils.isEmpty(ids)){
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
            super.safeJsonPrint(response , result);
            logService.end("/admin/delAdmin",result);
            return ;
        }

        String[] id = ids.split(",");
        logService.call("adminService.delAdminById()",id);
         boolean  delAdminjoin=adminService.delAdminById(id);
        if (delAdminjoin){
            String date=JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("删除成功"));
            super.safeJsonPrint(response,date);
            logService.end("/admin/delAdmin",date);
            return;
        }
        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("0000001" , "参数异常")) ;
        super.safeJsonPrint(response , result);
        logService.end("/admin/delAdmin",result);
        return ;
    }

    //退出登录
    @RequestMapping("/exitLogin")
    public void exit(HttpServletResponse response,HttpServletRequest  request){
        AdminBO loginAdmin = super.getLoginAdmin(request);
        logService.startController(loginAdmin,request);
        //退出登录
        String clientLoginID = super.getClientLoginID(request);
        if (StringUtils.isEmpty(clientLoginID)) {
            String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.failure("00000001" , "没有获取到clientLoginID！")) ;
            super.safeJsonPrint(response , result);
            return ;
        }
        String key = super.createKey(clientLoginID, SysConstants.CURRENT_LOGIN_USER);
        //从redis中删除用户信息
        RedissonHandler.getInstance().delete(key);
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return  ;
        }
        for(Cookie c :cookies ){
            c.setMaxAge(0);
            c.setValue(null);
            c.setPath("/");
            response.addCookie(c);
        }

        String result = JsonUtils.getJsonString4JavaPOJO(ResultDTOBuilder.success("" )) ;
        super.safeJsonPrint(response , result);
        logService.end("/admin/exitLogin",request);
        return ;
    }

}
