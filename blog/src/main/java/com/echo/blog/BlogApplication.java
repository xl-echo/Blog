package com.echo.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Blog应用启动类
 * 启用Spring Cache和事务管理
 * @author echo
 */
@SpringBootApplication(exclude = {org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration.class, org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration.class})
@EnableTransactionManagement
@ComponentScan(basePackages = "com.echo.blog")
@MapperScan(basePackages = "com.echo.blog.dao")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
        System.out.println("========================================");
        System.out.println("  Blog应用启动成功！");
        System.out.println("  Spring Cache已启用");
        System.out.println("  访问地址: http://localhost:10080");
        System.out.println("========================================");
    }
}




