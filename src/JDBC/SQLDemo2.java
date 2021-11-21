package JDBC;

import java.sql.*;

public class SQLDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
            String sql = "select * from account";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);                   //处理结果
            while(rs.next()) {                                     //让游标移动到下一行,true 表示有数据行,可以到下一行
                int id = rs.getInt(1);                    //获取id
                String name = rs.getString("name");             //获取姓名
                double balance = rs.getDouble(3);              //获取工资

                System.out.println("id:" + id + " 姓名：" + name + " 工资：" + balance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
//            if(rs != null){
//                try {
//                    rs.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(stmt != null){
//                try {
//                    stmt.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//            if(conn != null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
            JDBCUtils.close(rs,stmt,conn);              //使用JDBC工具类
        }
    }
}
