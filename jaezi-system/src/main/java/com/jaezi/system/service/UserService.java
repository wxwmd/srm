package com.jaezi.system.service;

import com.jaezi.common.base.BaseException;
import com.jaezi.common.base.BaseService;
import com.jaezi.common.bean.JsonResult;
import com.jaezi.common.bean.ResponseEnum;
import com.jaezi.common.util.SHA256;
import com.jaezi.system.dao.SupplierDao;
import com.jaezi.system.dao.UserDao;
import com.jaezi.system.dto.SupplierDto;
import com.jaezi.system.dto.UserDto;
import com.jaezi.system.model.Supplier;
import com.jaezi.system.model.User;
import com.jaezi.system.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author iuyy
 * @version v1.0
 * @corporation Copyright by jaezi.com
 * @date 2020/4/15 15:49
 * @description 用户服务接口实现类
 */
@Service
public class UserService extends BaseService<User, UserVo> {

    private UserDao userDao;

    private SupplierDao supplierDao;

    @Autowired
    public void setBaseDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    @Autowired
    public void setBaseDao(SupplierDao supplierDao) {
        this.supplierDao = supplierDao;
    }

    /**
     * 供应商注册
     *
     * @param registerInfo 注册info
     * @return JsonResult
     */
    public JsonResult register(SupplierDto registerInfo) {
        Boolean s;
        try {
            if (ObjectUtils.isEmpty(userDao.getSupplierByUsername(registerInfo.getUsername()))) {
                //如果没有就是备选
                s = true;
            } else {
                s = false;
            }
        } catch (Exception e) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), "供应商注册失败,请联系管理员");
        }

        User user = new User();
        //属性复制
        BeanUtils.copyProperties(registerInfo, user);
        user.setUserType(1);
        //注册时将用户名作为真实名
        user.setRealName(registerInfo.getCompanyName() + "(" + user.getUsername() + ")");
        if (s) {
            user.setStatus(1);
            user.setRoleId(4);
        } else {
            user.setStatus(0);
            user.setRoleId(3);
        }
        try {
            int userRest = add(user);
            if (userRest == -1) {
                throw new BaseException("用户名重复");
            }
            if (userRest <= 0) {
                throw new BaseException("用户添加失败");
            }

            Supplier supplier = new Supplier();
            //属性复制
            BeanUtils.copyProperties(registerInfo, supplier);
            supplier.setExamineStatus(0);
            supplier.setUserId(user.getId());
            if (!s) {
                supplier.setExamineStatus(1);
            }
            int supplierRest = supplierDao.add(supplier);
            if (supplierRest <= 0) {
                throw new BaseException("供应商添加失败");
            }
        } catch (BaseException e) {
            return new JsonResult(ResponseEnum.FAILURE.getCode(), e.getMessage());
        }

        return new JsonResult(ResponseEnum.SUCCESS, s);
    }

    /**
     * 添加用户
     * 添加前判断用户是否存在
     *
     * @param user 用户
     * @return 添加是否成功
     */
    @Override
    public int add(User user) {
        if (null != userDao.getUserByUsernameAndStatus(user.getUsername(), null)) {
            return -1;
        }
        //添加用户写入创建时间时间戳
        user.setCreateTime(System.currentTimeMillis());
        user.setPassword(SHA256.hash(user.getPassword()));
        if (user.getUserType() != 1) {
            user.setRealName(user.getRealName() + "(" + user.getUsername() + ")");
        }
        return userDao.add(user);
    }

    /**
     * 根据用户名获取用户
     *
     * @param userName 用户名
     * @return 单条用户信息
     */
    public User getUserByUsername(String userName) {
        return userDao.getUserByUsernameAndStatus(userName, 0);
    }

    /**
     * 根据用户类型和用户名获取用户
     *
     * @param userName 用户名
     * @param userName 用户类型
     * @return 单条用户信息
     */
    public User getUser(String userName, Integer userType) {
        return userDao.getUser(userName, userType);
    }


    /**
     * 根据角色Id获取用户信息
     *
     * @param roleId 角色Id
     * @return 用户信息
     */
    public List<User> getUserByRoleId(Integer roleId) {
        return userDao.getUserByRoleId(roleId);
    }

    /**
     * 根据用户Id获取密码
     *
     * @param id 用户Id
     * @return 用户密码
     */
    public String getPassword(Integer id) {
        return userDao.getPassword(id);
    }

    /**
     * 根据用户名称获取用户
     *
     * @param userDto 对象
     * @return 单条用户信息
     */
    public User getUserByUsername(UserDto userDto) {
        return userDao.getUserByUsernameAndStatus(userDto.getUsername(), 0);
    }

    /**
     * 清空token
     *
     * @param
     * @return String
     */
    public Integer removeToken(Integer userId) {
        return userDao.removeToken(userId);
    }
}
