package com.dao.impl;

import com.dao.AdminService;
import com.entity.Admin;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/*
在开发中，使用PreparedStatement代替Statement！
提高性能，代码可读性，安全性。
 */
public class AdminServiceImpl implements AdminService {
    @Override
    public boolean validateAdmin(Admin admin) {
        String sql = "select apassword from admin where aname = ?"; // ?占位符，
        Connection conn = null;
        PreparedStatement ps = null; // sql语句执行句柄
        try {
            conn = DBUtil.getConn();
            if(conn == null){
                return false;
            }
            ps = conn.prepareStatement(sql);
            // parameterindex参数索引
            ps.setString(1,admin.getAname());  //对指定的占位符进行设置为实际的值
            ResultSet resultSet = ps.executeQuery();// 执行sql语句
            while(resultSet.next()){ // 一行一行的读取
                String pwd = resultSet.getString(1);
                if(admin.getApassword().equals(pwd)){
                    return true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        return false;
    }
}
