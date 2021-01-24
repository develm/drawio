package com.sql.er.service;

import com.sql.er.dao.mapper.UserMapper;
import com.sql.er.dao.model.User;
import com.sql.er.common.utils.BCrypt;
import com.sql.er.common.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public Result<Long> registerUser(User user) {
        if (null == user || StringUtils.isEmpty(user.getUserPhone())) {
            return Result.fail("手机号不可为空");
        }
        if (StringUtils.isEmpty(user.getUserName())) {
            return Result.fail("userName is empty");
        }
        user.setIsActivity(1);
        user.setIsDelete(1);
        user.setPassword("");
        user.setCreateTime(new Date());
        user.setUpdatetime(new Date());
        user.setUserLevel(0);
        user.setUserScore(0);
        userMapper.insert(user);
        return Result.createSuccess(user.getId());
    }

    /**
     * 保存用户信息
     *
     * @param user
     * @return
     */
    public Result<Long> saveUserInfo(User user) {
        if (null == user || null == user.getId()) {
            return Result.fail("");
        }

        // 密码要单独设置
        user.setPassword(null);
        user.setUpdatetime(new Date());
        userMapper.updateByPrimaryKey(user);
        return Result.createSuccess(user.getId());
    }

    /**
     * 修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @param userId
     * @return
     */
    public Result<Long> changePassword(String oldPassword, String newPassword, Long userId) {
        if (StringUtils.isEmpty(oldPassword)) {
            return Result.fail("请输入旧密码");
        }
        if (StringUtils.isEmpty(newPassword)) {
            return Result.fail("请输入新密码");
        }
        if (null == userId) {
            return Result.fail("用户id不可为空");
        }

        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user || 1 == user.getIsDelete()) {
            return Result.fail("用户不存在");
        }
        if (BCrypt.checkpw(oldPassword, user.getPassword())) {
            User update = new User();
            update.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            update.setUpdatetime(new Date());
            update.setId(userId);
            userMapper.updateByPrimaryKey(update);
        } else {
            return Result.fail("密码不正确");
        }
        return Result.createSuccess(userId);
    }

    /**
     * 初始化密码
     *
     * @param password
     * @param userId
     * @return
     */
    public Result<Long> initPassword(String password, Long userId) {
        if (null == userId) {
            return Result.fail("用户id不可为空");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user || 1 == user.getIsDelete()) {
            return Result.fail("用户不存在");
        }
        User update = new User();
        update.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        update.setUpdatetime(new Date());
        update.setId(userId);
        userMapper.updateByPrimaryKey(update);
        return Result.createSuccess(userId);
    }

    public Result<User> getUserInfo(Long userId) {
        if (null == userId) {
            return Result.fail("用户id不可为空");
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (null == user || 1 == user.getIsDelete()) {
            return Result.fail("用户不存在");
        }
        return Result.createSuccess(user);
    }
}
