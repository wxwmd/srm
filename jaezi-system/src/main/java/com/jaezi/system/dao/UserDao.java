package com.jaezi.system.dao;


import com.jaezi.common.base.BaseDao;
import com.jaezi.system.model.User;
import com.jaezi.system.vo.UserVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/16 17:12
 * @description 用户数据访问对象
 */
@Repository
public interface UserDao extends BaseDao<User, UserVo> {

    /**
     * 根据用户名称获取用户
     *
     * @param username 用户名
     * @param status 状态 0：正常 1：禁用
     * @return 单条用户信息
     */
    User getUserByUsernameAndStatus(String username, Integer status);

    /**
     * 获取所有用户信息带角色名和状态名
     *
     * @return 用户列表
     */
    List<UserVo> getAllVos();

    /**
     * 根据角色Id获取用户信息
     *
     * @param roleId 角色Id
     * @return 用户列表
     */
    List<User> getUserByRoleId(Integer roleId);

    /**
     * 根据用户类型和用户名获取用户
     *
     * @param userName 用户名称
     * @param userType 角色类型
     * @return 用户列表
     */
    User getUser(String userName, Integer userType);

    /**
     * 根据用户Id获取密码
     *
     * @param id 用户Id
     * @return 用户密码
     */
    String getPassword(Integer id);

    /**
     * 获取token
     *
     * @param
     * @return String
     */
    String getToken(Integer userId);

    /**
     * 清空token
     *
     * @param userId 用户id
     * @return String
     */
    int removeToken(Integer userId);

    /**
     * 批量插入
     *
     * @param list 用户列表
     * @return int
     */
    int saveBath(List<User> list);

    /**
     * 获取供应商信息
     *
     * @param username 用户名称
     * @return String
     */
    String getSupplierByUsername(String username);

}
