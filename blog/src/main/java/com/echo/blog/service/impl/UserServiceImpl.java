package com.echo.blog.service.impl;

import com.echo.blog.dao.UserDao;
import com.echo.blog.po.UserPo;
import com.echo.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 * 使用Spring Cache替代Redis
 * @author echo
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    /**
     * 根据ID获取用户
     * 使用Spring Cache缓存
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    @Cacheable(value = "users", key = "#id", unless = "#result == null")
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
    @Cacheable(value = "users", key = "'username:' + #username", unless = "#result == null")
    public UserPo getUserByUsername(String username) {
        return userDao.selectByUsername(username);
    }

    /**
     * 创建用户
     * @param user 用户信息
     * @return 创建的用户
     */
    @Override
    @CachePut(value = "users", key = "#result.id")
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
    @CachePut(value = "users", key = "#user.id")
    public UserPo updateUser(UserPo user) {
        userDao.updateByPrimaryKey(user);
        return user;
    }

    /**
     * 删除用户
     * @param id 用户ID
     */
    @Override
    @CacheEvict(value = "users", key = "#id")
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
    @CacheEvict(value = "users", allEntries = true)
    public void clearAllUserCache() {
        // 清空缓存
    }
}
