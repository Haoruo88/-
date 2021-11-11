package com.view;

import com.dao.impl.GoodShowImpl;
import com.handler.ServiceHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Vector;

public class GoodsView extends JFrame{
    // 上面的容器是边界布局
    JPanel northPanel = new JPanel(); // 默认流布局
    JPanel southPanel = new JPanel();
    JButton addBtn = new JButton("入库");
    JButton delBtn = new JButton("出库");
    JComboBox cmb = new JComboBox(); // 下拉列表
    JButton searchBtn = new JButton("查询");
    JButton flushBtn = new JButton("刷新");
    JButton quitBtn = new JButton("退出");
    ServiceHandler serviceHandler;// 服务监听器

    Vector<String> columns;
    DefaultTableModel goodsModel;
    JTable jTable;
    Vector<Vector<Object>> data;
    // 下面的容器用来展示货物表格
    //JPanel southPanel = new JPanel();

    public GoodsView() throws HeadlessException {
        super("仓库管理系统");
        setLayout(new BorderLayout()); // 整体布局为边界布局

        serviceHandler = new ServiceHandler(this);

        cmb.addItem("-查询类型-");
        cmb.addItem("货物");
        cmb.addItem("入库单");
        cmb.addItem("出库单");
        // 放置北边的组件
        northPanel.add(addBtn);
        northPanel.add(delBtn);
        northPanel.add(cmb);
        northPanel.add(searchBtn);
        // 给按钮添加事件监听器
        addBtn.addActionListener(serviceHandler);
        delBtn.addActionListener(serviceHandler);
        searchBtn.addActionListener(serviceHandler);
        flushBtn.addActionListener(serviceHandler);
        quitBtn.addActionListener(serviceHandler);
        // 放置南边的组件
        FlowLayout flowLayout = new FlowLayout(FlowLayout.RIGHT); // 设置对齐方式为居右对齐
        southPanel.setLayout(flowLayout);
        southPanel.add(flushBtn);
        southPanel.add(quitBtn);
        // 放置中间的表格
        columns = new Vector<>();// 设置表头
        columns.addElement("编号");
        columns.addElement("名称");
        columns.addElement("数量");
        columns.addElement("仓库编号");
        columns.addElement("供应商编号");

        data = new Vector<>();// 添加数据
        GoodShowImpl goodShowImpl = new GoodShowImpl();
        data = goodShowImpl.returnGoods(); //
        goodsModel = new DefaultTableModel();
        jTable = new JTable(goodsModel);
        goodsModel.setDataVector(data,columns);
        // 设置表头
        JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.setFont(new Font(null,Font.BOLD,12));
        // 产生一个带滚动条的面板
        JScrollPane scrollPane = new JScrollPane(jTable);

        /*
        设置整体窗格为边界布局，上面的面板为(初始化默认为流布局)流布局,中间面板为scrollPane，并且传入jtable。
         */
        add(northPanel,BorderLayout.NORTH);
        add(scrollPane,BorderLayout.CENTER);
        add(southPanel,BorderLayout.SOUTH);
        /*设置窗口*/
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭退出
        setVisible(true);//可见

    }

    public void setData(Vector<Vector<Object>> data) {
        this.data = data;
    }

    public Vector<String> getColumns() {
        return columns;
    }

    public DefaultTableModel getGoodsModel() {
        return goodsModel;
    }

    public JComboBox getCmb() {
        return cmb;
    }
}

