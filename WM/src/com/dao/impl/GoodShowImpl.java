package com.dao.impl;

import com.dao.GoodsShow;
import com.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
/*
该class是处理货物信息列表，主要处理返回货物信息二维vector，以实现GoodsView下jtableModel的设置
 */
public class GoodShowImpl implements GoodsShow {

    @Override
    public Vector<Vector<Object>> returnGoods(){
        Vector<Vector<Object>> data = new Vector<>();
        String sql = "select * from good";
        Connection conn = null;
        PreparedStatement ps = null; // sql语句执行句柄
        try {
            conn = DBUtil.getConn();
            if(conn == null){
                return null;
            }
            ps = conn.prepareStatement(sql);
            // parameterindex参数索引
            ResultSet resultSet = ps.executeQuery();// 执行sql语句
            while(resultSet.next()){ // 一行一行的读取
                Vector<Object> vector = new Vector<>();
                vector.addElement(resultSet.getString(1));
                vector.addElement(resultSet.getString(2));
                vector.addElement(resultSet.getString(3));
                vector.addElement(resultSet.getString(4));
                vector.addElement(resultSet.getString(5));
                data.addElement(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        return data;
    }
}
