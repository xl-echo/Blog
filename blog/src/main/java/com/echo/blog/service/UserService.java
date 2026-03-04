package com.echo.blog.service;

import com.echo.blog.dto.UserDto;
import com.echo.blog.po.UserPo;

/**
 * 用户服务接口
 * @author echo
 */
public interface UserService {

    /**
     * 根据ID获取用户
     * @param id 用户ID
     * @return 用户信息
     */
    UserPo getUserById(Long id);

    /**
     * 根据用户名获取用户
     * @param username 用户名
     * @return 用户信息
     */
    UserPo getUserByUsername(String username);

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    UserPo createUser(UserPo user);

    /**
     * 更新用户
     * @param user 用户信息
     * @return 更新后的用户
     */
    UserPo updateUser(UserPo user);

    /**
     * 删除用户
     * @param id 用户ID
     */
    void deleteUser(Long id);

    /**
     * 检查用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    boolean existsByUsername(String username);

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 用户信息
     */
    UserDto login(String username, String password);

    /**
     * 用户注册
     * @param userPo 用户信息
     */
    void register(UserPo userPo);
}



