package 连接池.Druid;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 演示
 */

public class DruidDemo1 {
    public static void main(String[] args) throws Exception {
        Properties pro = new Properties();
        InputStream is = DruidDemo1.class.getClassLoader().getResourceAsStream("druid.properties");
        pro.load(is);                  //加载配置文件

        DataSource ds = DruidDataSourceFactory.createDataSource(pro);               //获取连接池对象
        Connection conn = ds.getConnection();                      //获取连接
        System.out.println(conn);
    }
}
