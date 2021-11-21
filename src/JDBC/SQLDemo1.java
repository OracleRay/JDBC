package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDemo1 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            String sql = //"insert into account values(3,'王五',3000)";                  //增加数据
                    "update account set balance = 1500 where id = 2";               //修改数据
                    //"delete from account where id = 1";                             //删除数据
            conn = DriverManager.getConnection("jdbc:mysql:///db1", "root", "root");
            stmt = conn.createStatement();
            int count = stmt.executeUpdate(sql);
            if (count > 0)
                System.out.println("成功");
            else
                System.out.println("失败");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(stmt != null){
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
