package com.echo.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Blog应用启动类
 * 启用Spring Cache和事务管理
 * @author echo
 */
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@ComponentScan(basePackages = "com.echo.blog")
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
