package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import com.dao.GoodsShow;
import com.entity.Goods;
import com.util.DBUtil;

public class Search {

    public Vector<Vector<Object>> returnGoods(Goods goods) {// 查询货物，返回其数据
        Vector<Vector<Object>> data = new Vector<>();
        String sql = "select * from good where gname = ?";
        Connection conn = null;
        PreparedStatement ps = null; // sql语句执行句柄
        try {
            conn = DBUtil.getConn();
            if(conn == null){
                return null;
            }
            ps = conn.prepareStatement(sql);
            // parameterindex参数索引
            ps.setString(1,goods.getGname());  //对指定的占位符进行设置为实际的值
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

    public Vector<Vector<Object>> returnInWH(){ // 返回所有的入库单
        Vector<Vector<Object>> data = new Vector<>();
        String sql = "select * from inwarehouse";
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
                vector.addElement(resultSet.getString(6));
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

    public Vector<Vector<Object>> returnOutWH(){ // 返回所有的出库单
        Vector<Vector<Object>> data = new Vector<>();
        String sql = "select * from outwarehouse";
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
