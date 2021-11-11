package com.handler;

import com.entity.Goods;
import com.util.DBUtil;
import com.view.DeleteView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class DeleteHandler implements ActionListener {
    private DeleteView deleteView;
    int amount_c;

    public DeleteHandler(DeleteView deleteView) {
        this.deleteView = deleteView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Goods goods = new Goods();
        goods = deleteView.buildGood();

        StringBuilder sql = new StringBuilder();
        sql.append("select amount from good where gnumber = ? ");

        Connection conn = null;
        PreparedStatement ps = null; // sql语句执行句柄
        PreparedStatement ps2 = null;
        PreparedStatement ps3 = null;

        StringBuilder sql2 = new StringBuilder();
        StringBuilder sql3 = new StringBuilder();
        sql3.append("insert into outwarehouse(gnumber,wnumber,amount,outtime) ");
        sql3.append("values(?,?,?,?)");
        try {
            conn = DBUtil.getConn();
            if(conn == null){
                return;
            }
            ps = conn.prepareStatement(sql.toString());

            ps3 = conn.prepareStatement(sql3.toString());
            // parameterindex参数索引
            ps.setInt(1,goods.getGnumber());  // 对指定的占位符进行设置为实际的值
            ResultSet resultSet = ps.executeQuery(); // 执行sql查询语句
            while(resultSet.next()){ // 读取
                amount_c = resultSet.getInt(1);// 得到现有库存数
            }
            if((amount_c - goods.getAmount())> 0){ // 出库后还剩些库存,更新库存
                int am = amount_c - goods.getAmount();
                sql2.append("update good set amount = ? where gname = ?");
                ps2 = conn.prepareStatement(sql2.toString());
                ps2.setInt(1,am);
                ps2.setString(2, goods.getGname());
            }else{  // 出库后没有库存剩余则删除货物
                sql2.append("delete from good where gname = ?");
                ps2 = conn.prepareStatement(sql2.toString());
                ps2.setString(1, goods.getGname());
            }
            ps2.executeUpdate();
            // 添加出库记录
            ps3.setInt(1,goods.getGnumber());
            ps3.setInt(2,goods.getWnumber());
            ps3.setInt(3,goods.getAmount());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());// new Date()为获取当前系统时间
            // String转换为sql包中的date
            java.sql.Date sqlDate = null;// sql包中的Date
            java.util.Date utildate = df.parse(time);
            sqlDate = new java.sql.Date(utildate.getTime());
            ps3.setDate(4,sqlDate);
            ps3.executeUpdate();

        } catch (Exception s) {
            s.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }
        // 添加完成，销毁添加界面
        JOptionPane.showMessageDialog(deleteView,"货物出库成功！");//（所属父容器，message），模态
        deleteView.dispose();
    }
}
