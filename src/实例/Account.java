package 实例;

import JDBC.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * 张三给李四转账钱
 */

public class Account {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("请输入您的姓名：");
        String fromName = in.nextLine();
        System.out.println("请输入要转账人的姓名：");
        String toName = in.nextLine();
        System.out.println("请输入转账金额：");
        double money = in.nextDouble();
        boolean flag = new Account().Money(fromName,toName,money);
        if(flag)
            System.out.println(fromName + "(您)已成功向"+ toName + "转账" + money + "元");
        else
            System.out.println("转账失败");
    }
    public boolean Money(String fromName,String toName,double money){
        Connection conn = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;
        try {
            conn = JDBCUtils.getConnection();
            conn.setAutoCommit(false);                                                //开启事务
            String sql1 = "update account set balance = balance - ? where name = ?";
            String sql2 = "update account set balance = balance + ? where name = ?";
            preparedStatement1 = conn.prepareStatement(sql1);
            preparedStatement2 = conn.prepareStatement(sql2);
            preparedStatement1.setDouble(1,money);
            preparedStatement1.setString(2,fromName);
            preparedStatement2.setDouble(1,money);
            preparedStatement2.setString(2,toName);
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            conn.commit();                           //提交事务
        } catch (Exception e) {
            try {
                if(conn != null)
                    conn.rollback();                   //若发生异常则回滚事务
            } catch (SQLException ex) {
                ex.printStackTrace();
                return false;
            }
            e.printStackTrace();
            return false;
        }finally {
            JDBCUtils.close(preparedStatement1,conn);
            JDBCUtils.close(preparedStatement2,null);
        }
        return true;
    }
}
