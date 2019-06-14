package com.wisewin.backend.service;


import com.wisewin.backend.dao.AdminDAO;
import com.wisewin.backend.entity.bo.*;
import com.wisewin.backend.entity.dto.AdminDTO;
import com.wisewin.backend.entity.dto.AdminRoleDTO;
import com.wisewin.backend.entity.dto.MenuDTO;
import com.wisewin.backend.entity.dto.RoleDTO;
import com.wisewin.backend.entity.param.MenuParam;
import com.wisewin.backend.entity.param.RegisterParam;
import com.wisewin.backend.service.base.LogService;
import com.wisewin.backend.util.MD5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service("adminService")
@Transactional
public class AdminService {
    @Resource
    private AdminDAO adminDAO;
    @Resource
    private LogService logService;
    /**
     * 根据手机号查找管理员信息
     * 管理员登录
     * @param mobile
     * @return
     */
    public AdminBO queryAdminInfoByMobile(String mobile) {
        logService.serviceStart("AdminService.queryAdminInfoByMobile()",mobile);
        logService.call("adminDAO.queryAdminInfoByMobile()",mobile);
        AdminBO adminBO = adminDAO.queryAdminInfoByMobile(mobile);
        logService.result(adminBO);
        if(adminBO!=null){
            logService.call("adminDAO.queryAdminUrl()",adminBO.getRoleId());
            List<String> urls=adminDAO.queryAdminUrl(adminBO.getRoleId());
            logService.result(urls);
            Set<String>  set=new HashSet<String>();
            if(urls!=null && urls.size()>0){
                for(String url:urls){
                    String[] split = url.split(",");
                    for(String str:split){
                        set.add(str);
                    }
                }
            }
            adminBO.setUrl(set);
        }
        logService.end("AdminService.queryAdminInfoByMobile()",adminBO);
        return adminBO;
    }



    /**
     * 管理员注册
     * @param admin
     * @return
     */
    public int adminRegister(AdminBO admin){
        return adminDAO.adminRegister(admin);
    }


    /**
     * 判断角色名称是否注册过
     * @param roleName
     * @return
     */
    public Integer selectCountByRoleName(String roleName){
        return adminDAO.selectCountByRoleName(roleName);
    }
    /**
     * 查找用户名是否注册过
     * @param name
     * @return
     */
    public int selectCountByName(String name){
        return adminDAO.selectCountByMobile(name);
    }

    /**
     * 查找手机号是否注册过
     * @param mobile
     * @return
     */
    public int selectCountByMobile(String mobile){
        return adminDAO.selectCountByMobile(mobile);
    }

    /**
     * 添加角色信息
     * @param roleBO
     * @return 返回受影响的行数
     */
    public int addRole(RoleBO roleBO){
        return adminDAO.addRole(roleBO);
    }

    /**
     * 查询所有的角色信息
     * @return  角色集合
     */
    public List<RoleBO> getRoleList(){
        return adminDAO.getRoleList();
    }

    /**
     * 查询所有的权限(菜单)信息
     * @return 权限集合
     */
    public List<MenuBO> getMenuList(){
        return adminDAO.getMenuList();
    }

    /**
     * 向角色权限表中添加数据
     * @param roleMenuBo
     * @return
     */
    public int addRoleMenu(RoleMenuBO roleMenuBo){
        return adminDAO.addRoleMenu(roleMenuBo);
    }

    /**
     * 根据用户id查询所对应的权限
     * @param userId 用户id
     * @return 返回权限信息
     */
    public List<MenuBO> getAdminToMenu(Integer userId){
        return adminDAO.getAdminToMenu(userId);
    }

    /**
     * 根据父id向权限表中添加数据
     * @param menuParam  添加的权限信息
     * @return 受影响的行数
     */
    public int addMenuByPid(MenuParam menuParam){
        MenuBO menuBO = new MenuBO();
        menuBO.setMenuName(menuParam.getMenuName());
        menuBO.setPid(menuParam.getPid());
        menuBO.setStatus(menuParam.getStatus());
        menuBO.setUrl(menuParam.getUrl());
        menuBO.setIndex(menuParam.getIndex());
        menuBO.setIcon(menuParam.getIcon());
        menuBO.setCreateTime(new Date());
        menuBO.setUpdateTime(new Date());
        return adminDAO.addMenuByPid(menuBO);
    }

    /**
     * 根据id查找下面有没有子菜单 如果没有则删除，如果有不能删除
     * @param id
     * @return 返回是否删除成功
     */
    public boolean delMenuById(Integer id){
        MenuBO menuBO = adminDAO.getMenuByPid(id);
        if(menuBO==null){
            return adminDAO.delMenuById(id);
        }
        return false;
    }

    /**
     * 根据权限名称查询权限是否存在
     * @param menuName 权限名称
     * @return
     */
    public boolean selectCountMenuName(String menuName){
        return adminDAO.selectCountMenuName(menuName);
    }

    /**
     * 根据菜单id修改菜单信息
     * @param menuParam
     * @return
     */
    public int updateMenuById(MenuParam menuParam){
        MenuBO  menuBO = new MenuBO();
        menuBO.setId(menuParam.getId());
        menuBO.setPid(menuParam.getPid());
        menuBO.setStatus(menuParam.getStatus());
        menuBO.setUrl(menuParam.getUrl());
        menuBO.setMenuName(menuParam.getMenuName());
        menuBO.setIcon(menuParam.getIcon());
        menuBO.setIndex(menuParam.getIndex());
        menuBO.setUpdateTime(new Date());
        return adminDAO.updateMenuById(menuBO);
    }

    /**
     * 根据id查询权限表(菜单信息)
     * @param id  权限id
     * @return 菜单信息
     */

    /**
     * 根据角色名查询拥有的权限
     * @param map
     * @return 返回对应的权限
     */
    public List<RoleDTO> selectRoleToMenu(Map<String,Object> map,String roleName,String menuIds){

        RoleBO roleBO = new RoleBO();
        roleBO.setRoleName(roleName);
        roleBO.setCreateTime(new Date());
        roleBO.setUpdateTime(new Date());
        // 添加角色
        adminDAO.addRole(roleBO);
        boolean status = menuIds.contains(",");
        if(status){
            String[] ids = menuIds.split(",");
            for (String id:
                    ids) {
                RoleMenuBO roleMenuBO = new RoleMenuBO();
                roleMenuBO.setRoleId(roleBO.getId());
                roleMenuBO.setMenuId(Integer.parseInt(id));
                roleMenuBO.setCreateTime(new Date());
                roleMenuBO.setUpdateTime(new Date());
                // 添加权限
                adminDAO.addRoleMenu(roleMenuBO);
            }
        }else{
            RoleMenuBO roleMenuBO = new RoleMenuBO();
            roleMenuBO.setRoleId(roleBO.getId());
            roleMenuBO.setMenuId(Integer.parseInt(menuIds));
            roleMenuBO.setCreateTime(new Date());
            roleMenuBO.setUpdateTime(new Date());
            // 添加权限
            adminDAO.addRoleMenu(roleMenuBO);
        }
        List<RoleBO> roleBOS = adminDAO.selectRoleToMenu(map);
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        for (RoleBO ro:roleBOS) {
            RoleDTO roleDTO = new RoleDTO();
            List<Integer> menuId = new ArrayList<Integer>();// 存放权限id
            List<String> menuName = new ArrayList<String>(); // 存放权限name
            roleDTO.setId(ro.getId());// 角色id
            roleDTO.setRoleName(ro.getRoleName()); // 角色名称
            roleDTO.setCreateTime(ro.getCreateTime());
            roleDTO.setUpdateTime(ro.getUpdateTime());
            List<MenuBO> menus = ro.getMenuBOS();// 角色对应的权限id
            for (int i=0;i<menus.size();i++ ) {
                menuName.add(menus.get(i).getMenuName());
            }
            boolean flag = menuIds.contains(",");
            if(flag) {
                String[] ids = menuIds.split(",");
                for (String id :
                        ids) {
                    menuId.add(Integer.parseInt(id));
                }
            }
            roleDTO.setMenuIds(menuId);
            roleDTO.setMenuNames(menuName);
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;

    }

    /**
     * 根据角色名称查找对应的权限总数
     * @param map
     * @return
     */
    public Integer getCountRoleToMenu(Map<String,Object> map){
        return adminDAO.getCountRoleToMenu(map);
    }

    /**
     * 根据角色id查询拥有的权限
     * @param roleId  角色名
     * @return 返回对应的权限
     */
    public List<RoleDTO> selectRoleMenuById(Integer roleId,String menuIds){
        // 根据角色id删除对应的权限
        adminDAO.delRoleMenuByRoleId(roleId);
        boolean status = menuIds.contains(",");
        if(status){
            String[] ids = menuIds.split(",");
            for (String id:
                    ids) {
                RoleMenuBO roleMenuBO = new RoleMenuBO();
                roleMenuBO.setRoleId(roleId);
                roleMenuBO.setMenuId(Integer.parseInt(id));
                // roleMenuBO.setCreateTime(new Date());
                roleMenuBO.setUpdateTime(new Date());
                // 添加权限
                adminDAO.addRoleMenu(roleMenuBO);
            }
        }else{
            RoleMenuBO roleMenuBO = new RoleMenuBO();
            roleMenuBO.setRoleId(roleId);
            roleMenuBO.setMenuId(Integer.parseInt(menuIds));
            // roleMenuBO.setCreateTime(new Date());
            roleMenuBO.setUpdateTime(new Date());
            // 添加权限
            adminDAO.addRoleMenu(roleMenuBO);
        }
        // 查询角色所拥有的权限
        List<RoleDTO> roleDTOS = new ArrayList<RoleDTO>();
        List<RoleBO> menuList = adminDAO.selectRoleMenuById(roleId);
        for (RoleBO ro:menuList) {
            RoleDTO roleDTO = new RoleDTO();
            List<Integer> menuId = new ArrayList<Integer>();// 存放权限id
            List<String> menuName = new ArrayList<String>(); // 存放权限name
            roleDTO.setId(ro.getId());// 角色id
            roleDTO.setRoleName(ro.getRoleName()); // 角色名称
            roleDTO.setCreateTime(ro.getCreateTime());
            roleDTO.setUpdateTime(ro.getUpdateTime());
            List<MenuBO> menus = ro.getMenuBOS();// 角色对应的权限id
            for (int i=0;i<menus.size();i++ ) {
                menuName.add(menus.get(i).getMenuName());
            }
            boolean flag = menuIds.contains(",");
            if(flag) {
                String[] ids = menuIds.split(",");
                for (String id :
                        ids) {
                    menuId.add(Integer.parseInt(id));
                }
            }
            roleDTO.setMenuIds(menuId);
            roleDTO.setMenuNames(menuName);
            roleDTOS.add(roleDTO);
        }
        return roleDTOS;
    }

    /**
     * 根据角色名称查询对应的权限(模糊查询)
     * @param dimName 模糊查询的名字
     * @return 查询的权限信息
     */
    public List<MenuDTO> getDimRoleMenu(String dimName){
        return adminDAO.getDimRoleMenu(dimName);
    }

    /**
     * 根据角色id删除角色
     * @param roleIds 角色id
     */
    public boolean delRoleById(String roleIds){
        boolean status = roleIds.contains(",");
        if(status){
            String [] ids = roleIds.split(",");
            for (String id:ids ) {
               adminDAO.delRoleById(Integer.parseInt(id));
            }
        }else{
               adminDAO.delRoleById(Integer.parseInt(roleIds));
        }
        return true;
    }

    /**
     * 查询所有角色的权限
     * @return
     */
    public List<MenuDTO> getRoleMenu(String roleName){
        return adminDAO.getRoleMenu(roleName);
    }

    /**
     * 根据用户名查询对应的角色
     * @param userName
     * @return
     */
    public List<AdminRoleDTO> getAdminRoleByName(String userName){
        return adminDAO.getAdminRoleByName(userName);
    }


    /**
     * 根据用户id修改角色id
     * @param roleId 角色id
     * @param id  用户id
     * @return
     */
    public boolean editUserRole(Integer roleId,Integer id){
        return adminDAO.editUserRole(roleId, id);
    }

    /**
     * 根据用户id删除用户信息
     * @param Did
     * @return
     */
    public boolean delAdminById(String[] Did){
        return adminDAO.delAdminById(Did);
    }

    /**
     * 修改admin用户信息
     * @param param 用户信息
     * @return 是否修改成功
     */
    public boolean updateAdminUser(RegisterParam param){
        AdminBO adminBO = new AdminBO();
        AdminBO admin = new AdminBO();
        adminBO.setId(param.getId());
        List<AdminDTO> adminBOS = adminDAO.getAdminNoFenYe(adminBO);
        for (AdminDTO adminDTO:adminBOS) {
            if(param.getId()!=null){
                adminDTO.setId(param.getId());
                admin.setId(adminDTO.getId());
            }
            if(param.getGender()!=null){
                adminDTO.setGender(param.getGender());
                admin.setGender(adminDTO.getGender());
            }
            if(param.getPassword()!=null){
                adminDTO.setPassword(MD5Util.digest(param.getPassword()));
                admin.setPassword(adminDTO.getPassword());
            }
            if(param.getMobile()!=null){
                adminDTO.setMobile(param.getMobile());
                admin.setPhoneNumber(adminDTO.getMobile());
            }
            if(param.getName()!=null){
                // 判断前端传的名称是否相同
                if(adminDTO.getName().equals(param.getName())){
                    adminDTO.setName("");
                }else{
                    // 判断用户名是否存在
                    String name1= param.getName();
                    int name = adminDAO.selectCountByName(name1);
                    if(name > 0 ){
                        return false;
                    }
                    adminDTO.setName(param.getName());
                    admin.setName(adminDTO.getName());
                }
            }
            if(param.getEmail()!=null){
                adminDTO.setEmail(param.getEmail());
                admin.setEmail(adminDTO.getEmail());
            }
            if(param.getRoleId()!=null){
                adminDTO.setRoleId(param.getRoleId());
                admin.setRoleId(adminDTO.getRoleId());
            }
            admin.setUpdateTime(new Date());
            return adminDAO.updateAdminUser(admin);
        }
        return false;
    }

    /**
     * 查询用户信息不使用分页
     * @param adminBO
     * @return
     */
    public List<AdminDTO> getAdminNoFenYe(AdminBO adminBO){
        return adminDAO.getAdminNoFenYe(adminBO);
    }

    /**
     * 根据角色id查询角色信息
     * @param roleId
     * @return
     */
    public RoleBO getRoleById(Integer roleId){
        return adminDAO.getRoleById(roleId);
    }

    /**
     * 根据权限id查询权限信息
     * @param menuId
     * @return
     */
    public MenuBO getMenuById(Integer menuId){
        return adminDAO.getMenuById(menuId);
    }

    /**
     * 查询admin用户信息
     * @param map
     * @return
     */
    public List<AdminDTO> getAdmin(Map<String,Object> map){
        return adminDAO.getAdmin(map);
    }

    /**
     * 根据用户信息查询用户信息(封装到map中)
     * @param map
     * @return
     */
    public Integer getAdminCountByMap(Map<String,Object> map){
        return adminDAO.getAdminCountByMap(map);
    }

    /**
     * 根据角色名称查找对应的角色id
     * @param roleName
     * @return
     */
    public Integer getRoleIdByRoleName(String roleName){
        return adminDAO.getRoleIdByRoleName(roleName);
    }

    /**
     * 删除某个角色对应的id
     * @param roleId 角色id
     * @param menuId 权限id
     * @return 是否删除成功
     */
    public boolean delRoleMenu(Integer roleId,Integer menuId){
        return adminDAO.delRoleMenu(roleId,menuId);
    }

    /**
     * 根据角色id删除对应的权限
     * @param roleId
     */
    public void delRoleMenuByRoleId(Integer roleId){
        adminDAO.delRoleMenuByRoleId(roleId);
    }

    /**
     * 根据角色id修改角色名称
     * @param roleId 角色id
     * @param roleName 角色名称
     */
    public void updateRoleNameByRoleId(Integer roleId, String roleName, Date updateTime){
        adminDAO.updateRoleNameByRoleId(roleId,roleName,updateTime);
    }

    /**
     * 查询角色对应的菜单
     * @param roleName 角色名
     * @return
     */
    public List<RoleDTO> getRole(String roleName){
        List<RoleBO> ros = adminDAO.getRole(roleName);
        List<RoleDTO> roleDTOs = new ArrayList<RoleDTO>();

        for (RoleBO ro:ros) {
            RoleDTO roleDTO = new RoleDTO();
            List<Integer> menuIds = new ArrayList<Integer>();// 存放权限id
            List<String> menuName = new ArrayList<String>(); // 存放权限name
            roleDTO.setId(ro.getId());// 角色id
            roleDTO.setRoleName(ro.getRoleName()); // 角色名称
            roleDTO.setCreateTime(ro.getCreateTime());
            roleDTO.setUpdateTime(ro.getUpdateTime());
            List<MenuBO> menus = ro.getMenuBOS();// 角色对应的权限id
            for (int i=0;i<menus.size();i++ ) {
                menuIds.add(menus.get(i).getId());
                menuName.add(menus.get(i).getMenuName());
            }
            roleDTO.setMenuIds(menuIds);
            roleDTO.setMenuNames(menuName);
            roleDTOs.add(roleDTO);
        }


        return roleDTOs;
    }

    /**
     * 查询角色对应的菜单的总数
     * @param map 角色名
     * @return
     */
    public Integer getCountRole(Map map){
        return adminDAO.getCountRole(map);
    }

    /**
     * 根据当前登陆用户的角色id查询对应的权限
     * @param roleId 角色id
     * @return 登陆用户对应的权限
     */
    public List<RoleBO> getRoleMenuSuccess(Integer roleId){
        return adminDAO.getRoleMenuSuccess(roleId);
    }


    public String existsUser(String roleIds) {
        String[] split = roleIds.split(",");
        for(int i=0;i<split.length;i++){
            int count = adminDAO.existsUser(new Integer(split[i]));
            if(count>0){
                return split[i];
            }
        }
        return  null;
    }


    public List<LanguageChoiceBO> queryRoles() {
        return adminDAO.queryRoles();
    }
}
