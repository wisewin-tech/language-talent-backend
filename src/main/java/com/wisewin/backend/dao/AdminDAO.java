package com.wisewin.backend.dao;

import com.wisewin.backend.entity.bo.AdminBO;
import com.wisewin.backend.entity.bo.MenuBO;
import com.wisewin.backend.entity.bo.RoleBO;
import com.wisewin.backend.entity.bo.RoleMenuBO;
import com.wisewin.backend.entity.dto.AdminDTO;
import com.wisewin.backend.entity.dto.AdminRoleDTO;
import com.wisewin.backend.entity.dto.MenuDTO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AdminDAO {

    /**
     * 通过手机号查找管理员信息
     *
     * @param mobile
     * @return UserDO
     */
    AdminBO queryAdminInfoByMobile(String mobile);



    /**
     * 查询用户拥有的接口
     * @param roleid 角色id
     * @return
     */
    List<String> queryAdminUrl(Integer roleid);

    /**
     * 注册管理员信息
     *
     * @param admin
     * @return
     */
    int adminRegister(AdminBO admin);

    /**
     * 判断角色名是否注册过
     *
     * @param roleName 角色名称
     * @return
     */
    Integer selectCountByRoleName(String roleName);

    /**
     * 查找用户手机号是否注册过
     *
     * @param mobile
     * @return
     */
    int selectCountByMobile(String mobile);

    /**
     * 查找用户名是否注册过
     *
     * @param name
     * @return
     */
    int selectCountByName(String name);

    /**
     * 根据角色id查询角色信息
     *
     * @param roleId
     * @return
     */
    RoleBO getRoleById(Integer roleId);

    /**
     * 通过权限id查询权限信息
     *
     * @param menuId
     * @return
     */
    MenuBO getMenuById(Integer menuId);

    /**
     * 添加角色信息
     *
     * @param roleBO
     * @return 返回受影响的条数(添加成功几条数据)
     */
    int addRole(RoleBO roleBO);

    /**
     * 查询所有角色
     *
     * @return 所有角色
     */
    List<RoleBO> getRoleList();

    /**
     * 查詢所有权限(菜单)
     *
     * @return 所有权限
     */
    List<MenuBO> getMenuList();

    /**
     * 向角色权限表中添加数据
     *
     * @param roleMenuBO
     * @return
     */
    int addRoleMenu(RoleMenuBO roleMenuBO);

    /**
     * 根据用户id查询所对应的权限
     *
     * @param userId
     * @return 返回权限信息
     */
    List<MenuBO> getAdminToMenu(Integer userId);

    /**
     * 根据父id向权限表中添加数据
     *
     * @param menuBO 添加的菜单信息
     * @return 受影响的行数
     */
    int addMenuByPid(MenuBO menuBO);

    /**
     * 根据id查找下面有没有子菜单 如果没有则删除，如果有不能删除
     *
     * @param id
     * @return
     */
    MenuBO getMenuByPid(Integer id);

    /**
     * 根据id删除菜单信息
     *
     * @param id
     * @return
     */
    boolean delMenuById(Integer id);

    /**
     * 根据权限名称查询权限名称是否存在
     *
     * @param menuName 权限名称
     * @return 是否存在
     */
    boolean selectCountMenuName(String menuName);

    /**
     * 根据权限id修改权限信息
     *
     * @param menuBO
     * @return
     */
    int updateMenuById(MenuBO menuBO);

    /**
     * 根据角色名称查找对应的权限
     *
     * @param map 角色名称
     * @return 返回对应的权限
     */
    List<RoleBO> selectRoleToMenu(Map<String, Object> map);

    /**
     * 根据角色名称查找对应的权限总数
     *
     * @param map
     * @return
     */
    Integer getCountRoleToMenu(Map<String, Object> map);

    /**
     * 根据角色名称查找对应的权限(模糊查询)
     *
     * @param dimName 角色名称
     * @return 返回对应的权限
     */
    List<MenuDTO> getDimRoleMenu(String dimName);

    /**
     * 根据角色名称查找对应的权限(模糊查询)
     *
     * @param roleId 角色名称
     * @return 返回对应的权限
     */
    List<RoleBO> selectRoleMenuById(Integer roleId);

    /**
     * 根据角色id删除角色信息
     *
     * @param roleId 角色id
     */
    boolean delRoleById(Integer roleId);

    /**
     * 查询所有角色对应的权限
     *
     * @return
     */
    List<MenuDTO> getRoleMenu(String roleName);

    // 根据pid查询子菜单
    List<MenuBO> getCh(Integer pid);

    /**
     * 根据用户名查询对应的角色
     *
     * @param userName
     * @return
     */
    List<AdminRoleDTO> getAdminRoleByName(String userName);

    /**
     * 根据用户id修改角色id
     *
     * @param roleId 角色id
     * @param id     用户id
     * @return
     */
    boolean editUserRole(@Param("roleId") Integer roleId, @Param("id") Integer id);

    /**
     * 根据用户id删除用户信息
     *
     * @param id 用户id
     * @return
     */
    boolean delAdminById(@Param("Did") String[] Did);

    /**
     * 修改admin用户信息
     *
     * @param adminBO 修改信息
     * @return 是否修改成功
     */
    boolean updateAdminUser(AdminBO adminBO);

    /**
     * 查询用户信息
     *
     * @param map
     * @return
     */
    List<AdminDTO> getAdmin(Map<String,Object> map);

    /**
     * 查询用户信息不使用分页
     *
     * @param adminBO
     * @return
     */
    List<AdminDTO> getAdminNoFenYe(AdminBO adminBO);

    /**
     * 根据用户信息查询用户(封装到map中)
     *
     * @param map
     * @return 用户总数
     */
    Integer getAdminCountByMap(Map<String,Object> map);

    /**
     * 根据角色名称查找对应的角色id
     *
     * @param roleName 角色名称
     * @return
     */
    Integer getRoleIdByRoleName(String roleName);

    /**
     * 根据角色名称查找对应的菜单
     *
     * @param roleName 角色名称
     * @return 角色对应菜单的集合
     */
    List<RoleBO> getRole(String roleName);

    /**
     * 根据角色名称查询对应的菜单的总数
     *
     * @param map
     * @return
     */
    Integer getCountRole(Map map);

    /**
     * 删除某个角色对应的id
     *
     * @param roleId 角色id
     * @param menuId 权限id
     * @return 是否删除成功
     */
    boolean delRoleMenu(@Param("roleId") Integer roleId, @Param("menuId") Integer menuId);


    /**
     * 根据角色id删除对应的权限
     *
     * @param roleId
     */
    void delRoleMenuByRoleId(Integer roleId);

    /**
     * 根据角色id修改角色名称
     *
     * @param roleId
     * @param roleName
     */
    void updateRoleNameByRoleId(@Param("roleId") Integer roleId, @Param("roleName") String roleName, @Param("updateTime") Date updateTime);

    /**
     * 根据当前登陆用户的角色id查询对应的权限
     *
     * @param roleId 角色id
     * @return
     */
    List<RoleBO> getRoleMenuSuccess(Integer roleId);


}
