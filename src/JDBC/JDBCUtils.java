package JDBC;
/**
 * JDBC工具类
 */
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class JDBCUtils {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;
    static {
        Properties pro = new Properties();              //创建Properties集合类
        try {
//            ClassLoader classLoader = JDBCUtils.class.getClassLoader();            //获取src路径下的文件的方式
//            URL res = classLoader.getResource("jdbc.properties");
//            String path = res.getPath();
//            System.out.println(path);
//            pro.load(new FileReader(path));

            pro.load(new FileReader("D:\\雷东宸\\IdeaProjects\\Java项目\\src\\JDBC\\jdbc.properties"));

            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
            driver = pro.getProperty("driver");
            //Class.forName(driver);
        } catch (IOException e) {
            e.printStackTrace();
        } //catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
    public static Connection getConnection() throws SQLException {           //获取连接
        return DriverManager.getConnection(url,user,password);
    }
    public static void close(Statement stmt,Connection conn){              //释放资源
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
    public static void close(ResultSet rs,Statement stmt, Connection conn){             //释放资源
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
