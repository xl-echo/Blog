package com.echo.blog.service.impl;

import com.echo.blog.dao.UserDao;
import com.echo.blog.dto.UserDto;
import com.echo.blog.po.UserPo;
import com.echo.blog.service.UserService;
import com.echo.blog.utils.JwtTokenUtil;
import com.echo.blog.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 * @author echo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据ID获取用户
     * 使用Spring Cache缓存
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public UserPo getUserById(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    /**
     * 根据用户名获取用户
     * 使用Spring Cache缓存
     * @param username 用户名
     * @return 用户信息
     */
    @Override
    public UserPo getUserByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    @Override
    public UserPo createUser(UserPo user) {
        userDao.insert(user);
        return user;
    }

    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新后的用户
     */
    @Override
    public UserPo updateUser(UserPo user) {
        userDao.updateByPrimaryKey(user);
        return user;
    }

    /**
     * 删除用户
     * @param id 用户ID
     */
    @Override
    public void deleteUser(Long id) {
        userDao.deleteByPrimaryKey(id);
    }

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByUsername(username) > 0;
    }

    /**
     * 清空所有用户缓存
     */
    public void clearAll$1Cache() { }

    /**
     * 用户登录
     */
    @Override
    public UserDto login(String username, String password) {
        // 查询用户
        UserPo user = userDao.selectByUsername(username);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }

        // 验证密码
        String encryptedPassword = Md5Util.encrypt(password);
        if (!encryptedPassword.equals(user.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        // 生成token
        String token = JwtTokenUtil.generateToken(username);

        // 构造返回对象
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setNickname(user.getNickname());
        userDto.setToken(token);

        return userDto;
    }

    /**
     * 用户注册
     */
    @Override
    public void register(UserPo userPo) {
        // 检查用户名是否已存在
        if (existsByUsername(userPo.getUsername())) {
            throw new RuntimeException("用户名已存在");
        }

        // 加密密码
        userPo.setPassword(Md5Util.encrypt(userPo.getPassword()));

        // 保存用户
        userDao.insert(userPo);
    }
}





