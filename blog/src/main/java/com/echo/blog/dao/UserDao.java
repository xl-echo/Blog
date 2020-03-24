package com.echo.blog.dao;

import com.echo.blog.po.UserPo;
import org.apache.ibatis.annotations.Param;

/**
 * @author echo
 */
public interface UserDao {

    int deleteByPrimaryKey(Long id);

    int insert(UserPo record);

    int insertSelective(UserPo record);

    UserPo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserPo record);

    int updateByPrimaryKey(UserPo record);

    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户名
     * @return UserPo
     */
    UserPo selectByUsername(@Param("username") String username);
}