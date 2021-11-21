package JDBCTemplate;

import org.springframework.jdbc.core.JdbcTemplate;
import 连接池.Druid.JDBCUtils;

/**
 * JdbcTemplate 依赖于 DataSource
 * update():执行DML语句，增删查改
 * queryForMap():查询结果将结果集封装为map集合
 * queryForList():查询结果将结果封装为List集合
 * query():查询结果，将结果封装为JavaBean对象
 * queryForObject():查询结果，将结果封装为对象
 */

public class TemplateDemo1 {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());               //创建JDBCTemplate对象
        String sql = "update account set balance = 5000 where id = ?";
        int count = template.update(sql, 3);
        System.out.println(count);
    }
}
