package com.echo.blog.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 数据库初始化工具类
 * 用于执行 SQL 脚本初始化数据库
 */
public class DatabaseInitializer {

    public static void main(String[] args) {
        // 数据库连接配置
        String url = "jdbc:mysql://192.168.31.180:3306/pay_settle_1?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
        String username = "ztoopgts";
        String password = "eqYV8Qr9S4AlOc2MCq9z";
        String sqlFilePath = "D:\\codework\\Blog\\blog\\doc\\数据库数据\\pay_settle_1_init.sql";

        try {
            // 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 建立连接
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();

            // 读取 SQL 文件
            BufferedReader reader = new BufferedReader(new FileReader(sqlFilePath));
            StringBuilder sqlBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                // 跳过注释和空行
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--") || line.startsWith("/*") || line.startsWith("*")) {
                    continue;
                }
                sqlBuilder.append(line).append("\n");

                // 如果遇到分号，执行 SQL
                if (line.endsWith(";")) {
                    String sql = sqlBuilder.toString();
                    sqlBuilder = new StringBuilder();

                    try {
                        stmt.execute(sql);
                        System.out.println("执行成功: " + sql.substring(0, Math.min(50, sql.length())) + "...");
                    } catch (Exception e) {
                        System.err.println("执行失败: " + sql.substring(0, Math.min(50, sql.length())) + "...");
                        System.err.println("错误: " + e.getMessage());
                    }
                }
            }

            reader.close();
            stmt.close();
            conn.close();

            System.out.println("数据库初始化完成！");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
