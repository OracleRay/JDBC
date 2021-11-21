package 连接池.Druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 使用JDBC工具类
 */

public class DruidDemo2 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "insert into account values(4,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,"赵六");
            pstmt.setDouble(2,1500);
            int count = pstmt.executeUpdate();
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JDBCUtils.getClose(pstmt,conn);
        }
    }
}
