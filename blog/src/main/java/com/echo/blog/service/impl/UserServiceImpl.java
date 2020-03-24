package com.echo.blog.service.impl;

import com.echo.blog.config.handler.GlobalExceptionHandler;
import com.echo.blog.config.result.Result;
import com.echo.blog.config.result.StatusCode;
import com.echo.blog.dao.UserDao;
import com.echo.blog.dto.UserDto;
import com.echo.blog.po.UserPo;
import com.echo.blog.service.UserService;
import com.echo.blog.utils.JwtTokenUtil;
import com.echo.blog.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

/**
 * @author echo
 * @date 2020-01-30 15:42:50
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDao userDao;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserDto login(String username, String password) {

        UserPo userPo = userDao.selectByUsername(username);
        if(ObjectUtils.isEmpty(userPo)) {
            throw new RuntimeException("用户名不存在！");
        }
        String md5Password = null;

        try {
            md5Password = Md5Util.encodeByMd5(password);
        } catch (Exception e) {
            LOGGER.error("md5解析错误!错误信息提示: {}",e.getMessage());
            return null;
        }
        if(md5Password==null || !md5Password.equals(password)){
            throw new RuntimeException("密码不正确！");
        }
        UserDto userDto = new UserDto();
        userDto.setId(userPo.getId());
        userDto.setUsername(userPo.getUsername());
        userDto.setToken(JwtTokenUtil.generateToken(username));
        return userDto;
    }

    @Override
    public void register(UserPo userPo) {
        UserPo userPo1 = userDao.selectByUsername(userPo.getUsername());
        if(!ObjectUtils.isEmpty(userPo1)) {
            throw new RuntimeException("用户名已经存在，请修改用户名！");
        }
        userPo.setCreateTime(new Date());
        userPo.setUpdateTime(new Date());


        String s = null;
        try {
            s = Md5Util.encodeByMd5(userPo.getPassword());
        } catch (Exception e) {
            LOGGER.error("md5解析错误!错误信息提示: {}",e.getMessage());
            return;
        }
        userPo.setPassword(s);
        userDao.insert(userPo);


    }

}
