package test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class myjdbc {
//
//    static String driverName="com.mysql.cj.jdbc.Driver";
//    static String dbURL="jdbc:mysql://localhost:3306/netmon?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8";
//    static String Name = "root";
//    static String Pwd="081210";
//    static Connection con;
//    ResultSet rs;
//    public static Connection getcon() {
//        try {
//            Class.forName(driverName);
//            con = DriverManager.getConnection(dbURL, Name, Pwd);
//            return con;
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
//        return null;
//    }
    /**
     * 测试连接
     * @author 温暖wk
     *
     */
        public static void main(String[] args) {
            //加载驱动类
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/netmon?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8","root","081210");
                System.out.println("连接成功");

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


//    public static void main(String[] args) {
//        //加载驱动类
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            //Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/netmon?characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8","root","081210");
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
}
