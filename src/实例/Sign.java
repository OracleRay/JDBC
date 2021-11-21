package 实例;

import JDBC.JDBCUtils;

import java.sql.*;
import java.util.Scanner;

/**
 * 通过键盘录入用户名和密码
 * 判断用户是否登录成功
 */

public class Sign {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入用户名：");
        String username = in.next();
        System.out.println("请输入密码：");
        String password = in.next();
        boolean flag = new Sign().login(username,password);
        if(flag)
            System.out.println("登录成功！");
        else
            System.out.println("用户名或密码错误!");
    }
    public boolean login(String username,String password) {
        if (username == null || password == null)
            return false;
        Connection conn = null;
        PreparedStatement pstmt = null;                //防止sql注入问题
        ResultSet rs = null;
        try {
            conn = JDBCUtils.getConnection();                        //连接数据库
            String sql = "select * from user where username = ? and password = ?";
            pstmt = conn.prepareStatement(sql);                            //获取sql的对象
            pstmt.setString(1,username);          //给？赋值
            pstmt.setString(2,password);
            rs = pstmt.executeQuery();                          //执行查询
            return rs.next();                          //如果有下一行则返回true
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.close(rs,pstmt,conn);               //释放资源
        }
        return false;
    }
}
