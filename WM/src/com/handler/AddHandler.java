package com.handler;

import com.entity.Goods;
import com.util.DBUtil;
import com.view.AddView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;


public class AddHandler implements ActionListener {
    private AddView addView;

    public AddHandler(AddView addView) {
        this.addView = addView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // 读取要入库的货物信息
        Goods good = new Goods();
        good = addView.buildGood();
        // 连接数据库进行 增 操作
        StringBuilder sql = new StringBuilder();
        sql.append("insert into good(gnumber,gname,amount,wnumber,pnumber) ");
        sql.append("values(?,?,?,?,?)");
        Connection conn = null;
        PreparedStatement ps = null; // sql语句执行句柄
        PreparedStatement ps2 = null;
        StringBuilder sql2 = new StringBuilder();
        sql2.append("insert into inwarehouse(gnumber,wnumber,amount,pnumber,intime) ");
        sql2.append("values(?,?,?,?,?)");
        try {
            conn = DBUtil.getConn();
            if(conn == null){
                return;
            }
            ps = conn.prepareStatement(sql.toString());
            ps2 = conn.prepareStatement(sql2.toString());
            // parameterindex参数索引
            ps.setInt(1,good.getGnumber());  //对指定的占位符进行设置为实际的值
            ps.setString(2,good.getGname());
            ps.setInt(3,good.getAmount());
            ps.setInt(4,good.getWnumber());
            ps.setInt(5,good.getPnumber());
            ps.executeUpdate();
            // 添加入库记录
            ps2.setInt(1,good.getGnumber());
            ps2.setInt(2,good.getWnumber());
            ps2.setInt(3,good.getAmount());
            ps2.setInt(4,good.getPnumber());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String time = df.format(new Date());// new Date()为获取当前系统时间
            // String转换为sql包中的date
            java.sql.Date sqlDate = null;// sql包中的Date
            java.util.Date utildate = df.parse(time);
            sqlDate = new java.sql.Date(utildate.getTime());
            ps2.setDate(5,sqlDate);
            ps2.executeUpdate();

        } catch (Exception s) {
            s.printStackTrace();
        } finally {
            DBUtil.closeConn(conn);
            DBUtil.closePs(ps);
        }

        // 添加完成，销毁添加界面
        JOptionPane.showMessageDialog(addView,"货物入库成功！");//（所属父容器，message），模态
        addView.dispose();

    }
}
