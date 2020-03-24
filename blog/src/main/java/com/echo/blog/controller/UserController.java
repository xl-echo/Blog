package com.echo.blog.controller;

import com.echo.blog.config.result.Result;
import com.echo.blog.dto.UserDto;
import com.echo.blog.po.UserPo;
import com.echo.blog.service.UserService;
import com.echo.blog.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author echo
 * @date 2020-01-30 15:23:10
 */
@RestController
@RequestMapping("/userController")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Result<UserDto>
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<UserDto> login(@RequestParam(value = "username") String username,
                                 @RequestParam(value = "password") String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("用户名或者密码为空!");
        }
        UserDto userDto = userService.login(username, password);
        Result<UserDto> result = new Result<>();
        Result.packageResultMethod(result, userDto);
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<String> register(@RequestBody UserPo userPo) {
        userService.register(userPo);
        Result<String> result = new Result<>();
        Result.packageResultMethod(result, "success");
        return result;
    }

}
