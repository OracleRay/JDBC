package 连接池.C3P0;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *演示
 */

public class C3P0Demo1 {
    public static void main(String[] args) throws SQLException {
        DataSource ds = new ComboPooledDataSource();          //创建数据库连接池对象
        Connection conn = ds.getConnection();                 //获取连接对象
        for(int i = 1;i <= 10;i++)                            //10个以内不会超时
            System.out.println(i + "：" + conn);
    }
}
