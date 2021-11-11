package com.view;

import com.dao.impl.GoodShowImpl;
import com.handler.SearchGoodHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class SearchGoodView extends JFrame {
    Vector<String> columns;
    Vector<Vector<Object>> data;
    DefaultTableModel goodsModel;
    JTable jTable;
    JLabel gnameLabel = new JLabel("货物名称：");
    JTextField gnameTxt = new JTextField(10);
    JButton btn = new JButton("查询");
    SearchGoodHandler searchGoodHandler;
    public SearchGoodView() throws HeadlessException {

        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.add(gnameLabel);
        panel.add(gnameTxt);
        searchGoodHandler = new SearchGoodHandler(this);
        btn.addActionListener(searchGoodHandler);
        panel.add(btn);
        columns = new Vector<>();// 设置表头
        columns.addElement("编号");
        columns.addElement("名称");
        columns.addElement("数量");
        columns.addElement("仓库编号");
        columns.addElement("供应商编号");


//        data = new Vector<>();// 添加数据
//        GoodShowImpl goodShowImpl = new GoodShowImpl();
//        data = goodShowImpl.returnGoods(); //
        goodsModel = new DefaultTableModel();
        jTable = new JTable(goodsModel);
        goodsModel.setDataVector(null,columns);
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(panel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        /*设置窗口*/
        setSize(660,400);
        setLocationRelativeTo(null);//设置窗体居中，#注意设置语句前后顺序不同，可能会不显示
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);//可见
    }

    public DefaultTableModel getGoodsModel() {
        return goodsModel;
    }

    public Vector<String> getColumns() {
        return columns;
    }

    public JTextField getGnameTxt() {
        return gnameTxt;
    }

}
