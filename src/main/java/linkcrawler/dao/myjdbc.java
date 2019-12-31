package linkcrawler.dao;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myjdbc {
    public static Connection getcon() {
        //加载驱动类
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/netmon?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8","root","");
//            System.out.println("连接成功");
            return conn;

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
