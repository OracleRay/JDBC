package JDBC;

/**
DriverManager:驱动管理器(注册驱动，获取数据库连接)
       获取数据库连接对象DriverManager.getConnection(String url,String user,String password);
           url语法：jdbc:mysql://ip地址(域名):端口号/数据库名称(若为主机，ip地址和端口号可以不写)
Connection:数据库连接对象
       功能：获取sql对象：Statement createStatement();
                       PreparedStatement preparedStatement(String sql);
           管理事务：开启事务：setAutoCommit(boolean autoCommit):调用方法设置参数为false，即开启事务
                     提交事务；commit();
                     回滚事务：rollback();
Statement:执行sql数据库
        获取sql的对象：
            boolean execute(String sql);   可以执行任意的sql （了解）
            int executeUpdate(String sql);   执行DML(insert,update,delete)、DDL(create,alter,drop)语句
                返回值影响行数，通过行数判断是否执行成功
            ResultSet executeQuery(String sql);   执行DDL(select)语句
ResultSet:结果集对象
        next();     游标向下一定一行
        getXxx();      获取数据，如：getInt(),getDouble();    代表列的数据类型
            参数：int 如getString(1);   代表列的编号
                 String 如 getDouble("balance");     代表列的名称
PrepareStatement:执行sql的对象
        sql注入问题：在拼接sql时，有一些SQL的特殊关键字参与字符串的拼接。会造成安全性问题。如输入密码：a' or 'a' = 'a
        预编译的sql：参数使用？作为占位符
            如：select * from user where username = ? and password = ?;
        给？赋值：setXxx(参数1，参数2)
            参数1：？的位置编号，从1开始
            参数2：？的值
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class JDBCDemo {
    public static void main(String[] args) throws Exception{
        //注册驱动
        //Class.forName("com.mysql.jdbc.Driver");               //JDBC5之后可以不用注册驱动
        //获取数据库连接对象
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db1","root","root");
        //定义sql语句
        String sql = "update account set balance = 1000 where id = 1";
        //获取执行sql的对象Statement
        Statement stmt = conn.createStatement();
        //执行sql
        int count = stmt.executeUpdate(sql);
        //处理结果
        System.out.println(count);
        //释放资源
        stmt.close();
        conn.close();
    }
}
