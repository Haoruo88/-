package com.view;

import com.entity.Goods;
import com.handler.DeleteHandler;

import javax.swing.*;
import java.awt.*;

public class DeleteView extends JFrame {
    JLabel gnumbertLabel = new JLabel("货物编号");
    JTextField gnnumTxt = new JTextField(10);
    JLabel wnumbertLabel = new JLabel("仓库编号");
    JTextField wnumTxt = new JTextField(10);
    JLabel ganmeLabel = new JLabel("货物名称");
    JTextField gnameTxt = new JTextField(10);
    JLabel amountLabel = new JLabel("出库数量");
    JTextField amountTxt = new JTextField(10);
    JButton delBtn = new JButton("出库");
    DeleteHandler deleteHandler;
    public DeleteView() throws HeadlessException {

        deleteHandler = new DeleteHandler(this);
        delBtn.addActionListener(deleteHandler);

        JPanel jPanel = new JPanel();
        jPanel.add(gnumbertLabel);
        jPanel.add(gnnumTxt);
        jPanel.add(ganmeLabel);
        jPanel.add(gnameTxt);
        jPanel.add(wnumbertLabel);
        jPanel.add(wnumTxt);
        jPanel.add(amountLabel);
        jPanel.add(amountTxt);
        jPanel.add(delBtn);
        add(jPanel);
        /*设置窗口*/
        setSize(500,150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);//可见
    }

    public JTextField getGnameTxt() {
        return gnameTxt;
    }

    public JTextField getAmountTxt() {
        return amountTxt;
    }

    public Goods buildGood(){
        Goods goods = new Goods();
        goods.setGnumber(Integer.valueOf(gnnumTxt.getText()).intValue());
        goods.setGname(gnameTxt.getText());
        goods.setAmount(Integer.valueOf(amountTxt.getText()).intValue());
        goods.setWnumber(Integer.valueOf(wnumTxt.getText()).intValue());
        return goods;
    }
}
