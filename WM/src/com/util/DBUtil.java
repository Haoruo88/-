package com.util;

import java.sql.*;
/*
//数据库连接类，。、
 */
public class DBUtil {
    //驱动程序名
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    //URL指向要访问的数据库名WM
    private static final String URL ="jdbc:mysql://localhost:3306/WM?useSSL=false&serverTimezone=UTC";
    //MySQL配置时的用户名
    public static final String USER = "root";
    //MySQL配置时的密码
    public static final String PWD = "010828";

    static {
        try {
            //加载驱动
            Class.forName(DRIVER);
        }catch (ClassNotFoundException e){
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        }
    }

    public static Connection getConn(){  //连接数据库
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }
        return null;
    }

    public static void closeConn(Connection c){ // 关闭连接
        if(c!=null){
            try {
                c.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement Ps){ //
        if(Ps!=null){
            try {
                Ps.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet Rs){ //
        if(Rs!=null){
            try {
                Rs.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

}
