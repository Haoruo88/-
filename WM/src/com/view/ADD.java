package com.view;

import com.entity.Goods;

import javax.swing.*;
import java.awt.*;

public class ADD extends JFrame {
    JLabel gnumLabel = new JLabel("货物编号");
    JTextField gnumTxt = new JTextField(10);
    JLabel gnameLabel = new JLabel("货物名称");
    JTextField gnameTxt = new JTextField(10);
    JLabel gamountLabel = new JLabel("数量");
    JTextField gmountTxt = new JTextField(10);
    JLabel wnumLabel = new JLabel("仓库编号");
    JTextField wnumTxt = new JTextField(10);
    JLabel pnumLabel = new JLabel("供应商编号");
    JTextField pnumTxt = new JTextField(10);
    //JButton serchBtn = new JButton("入库");
    JPanel jPanel = new JPanel();
    public ADD() throws HeadlessException {

        jPanel.add(gnumLabel);
        jPanel.add(gnumTxt);
        jPanel.add(gnameLabel);
        jPanel.add(gnameTxt);
        jPanel.add(gamountLabel);
        jPanel.add(gmountTxt);
        jPanel.add(wnumLabel);
        jPanel.add(wnumTxt);
        jPanel.add(pnumLabel);
        jPanel.add(pnumTxt);
        //jPanel.add(serchBtn);
        add(jPanel);
        /*设置窗口*/
        setSize(500,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);//可见
    }

    public Goods buildGood(){
        Goods goods = new Goods();
        goods.setGnumber(Integer.valueOf(gnumTxt.getText()).intValue());
        goods.setGname(gnameTxt.getText());
        goods.setAmount(Integer.valueOf(gmountTxt.getText()).intValue());
        goods.setWnumber(Integer.valueOf(wnumTxt.getText()).intValue());
        goods.setPnumber(Integer.valueOf(pnumTxt.getText()).intValue());
        return goods;
    }

}
