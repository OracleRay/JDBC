package 连接池.Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * JDBC连接池工具类
 */


public class JDBCUtils {
    private static DataSource ds;
    static {
        Properties pro = new Properties();
        try {
            pro.load(JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties"));       //加载配置文件
            ds = DruidDataSourceFactory.createDataSource(pro);                                   //获取DataSource
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {                      //获取连接
        return ds.getConnection();
    }
    public static void getClose(Statement stmt,Connection conn){                    //归还连接
        if(stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void getClose(ResultSet rs,Statement stmt, Connection conn){              //归还连接
        if(rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        getClose(stmt,conn);
    }
    public static DataSource getDataSource(){                          //获取连接池方法
        return ds;
    }
}
