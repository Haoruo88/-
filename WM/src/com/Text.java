package com;
import java.sql.*;
import javax.swing.*;
public class Text {
    public static void main(String[] args) {

        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.cj.jdbc.Driver";
        //URL指向要访问的数据库名university
        String url = "jdbc:mysql://localhost:3306/university?useSSL=false&serverTimezone=UTC";
        //MySQL配置时的用户名
        String user = "root";
        //MySQL配置时的密码
        String password = "010828";
        //遍历查询结果集
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            //要执行的SQL语句
            String sql = "select * from student";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("--------------------------------------");
            System.out.println("执行结果如下所示:");
            System.out.println("------------------------");
            System.out.println("学号" + "\t" + "姓名" + "\t" + "性别" + "\t" + "年龄");
            System.out.println("--------------------------------------");
            String name= null;
            String id = null;
            String sex = null;
            String age = null;
            while(rs.next()){
                //获取sno这列数据
                id = rs.getString("sno");
                //获取sname这列数据
                name = rs.getString("sname");
                //获取sex这列数据
                sex = rs.getString("ssex");
                //获取age这列数据
                age = rs.getString("sage");
                //输出结果
                System.out.println(id + "\t" + name + "\t" + sex + "\t" + age);
            }
            rs.close();
            con.close();
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            System.out.println("Sorry,can`t find the Driver!");
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }
        finally{
            System.out.println("数据库数据成功获取！！");
        }

    }

}
