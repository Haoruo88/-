package com.view;

import com.dao.impl.Search;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Vector;

public class InWHView extends JFrame {
    Vector<String> columns;
    Vector<Vector<Object>> data;
    DefaultTableModel goodsModel;
    JTable jTable;
    public InWHView() throws HeadlessException {
        columns = new Vector<>();// 设置表头
        columns.addElement("单号");
        columns.addElement("货物编号");
        columns.addElement("仓库编号");
        columns.addElement("数量");
        columns.addElement("供应商编号");
        columns.addElement("入库时间");

        Search searchGood = new Search();
        data = searchGood.returnInWH();

        goodsModel = new DefaultTableModel();
        jTable = new JTable(goodsModel);
        goodsModel.setDataVector(data,columns);
        JScrollPane scrollPane = new JScrollPane(jTable);
        add(scrollPane);
        /*设置窗口*/
        setSize(660,400);
        setLocationRelativeTo(null);//设置窗体居中，#注意设置语句前后顺序不同，可能会不显示
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setVisible(true);//可见

    }
}
