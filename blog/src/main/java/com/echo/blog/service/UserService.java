package com.echo.blog.service;

import com.echo.blog.dto.UserDto;
import com.echo.blog.po.UserPo;

/**
 * @author echo
 * @date 2020-01-30 15:28:25
 */
public interface UserService {

    /**
     * 登录
     * @param username 用户名
     * @param password 密码
     * @return UserDto
     */
    UserDto login(String username, String password);

    /**
     * 注册账户
     * @param userPo 账户信息
     */
    void register(UserPo userPo);
}
