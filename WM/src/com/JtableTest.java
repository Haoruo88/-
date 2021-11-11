package com;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.Vector;

public class JtableTest extends JFrame {


    public JtableTest() throws HeadlessException {
        super("测试JTable");

        // 添加数据
        Vector<Vector<Object>> data = new Vector<>();

        Vector<Object> rowVector1 = new Vector<>();
        rowVector1.addElement(1);
        rowVector1.addElement("手机");
        rowVector1.addElement(3);
        rowVector1.addElement(1);
        rowVector1.addElement(1);

        Vector<Object> rowVector2 = new Vector<>();
        rowVector2.addElement(1);
        rowVector2.addElement("电脑");
        rowVector2.addElement(3);
        rowVector2.addElement(1);
        rowVector2.addElement(1);

        Vector<Object> rowVector3 = new Vector<>();
        rowVector3.addElement(1);
        rowVector3.addElement("电视");
        rowVector3.addElement(3);
        rowVector3.addElement(1);
        rowVector3.addElement(1);

        data.addElement(rowVector1);
        data.addElement(rowVector2);
        data.addElement(rowVector3);


        Container container = getContentPane();
        // tablemodel存放表格jtable的数据
        //DefaultTableModel tableModel = new DefaultTableModel();
        FoodsModel foodsModel = FoodsModel.assembleModel(data);
        // 表格对象jTable的数据来源是tableModel
//        JTable jTable = new JTable(tableModel);
//        tableModel.setDataVector(data,columns);
        JTable jTable = new JTable(foodsModel);
        // 设置表头
        JTableHeader jTableHeader = jTable.getTableHeader();
        jTableHeader.setFont(new Font(null,Font.BOLD,12));
        // 产生一个带滚动条的面板
        JScrollPane scrollPane = new JScrollPane(jTable);

        container.add(scrollPane);
        /*设置窗口*/
        setSize(800,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭退出
        setVisible(true);//可见
    }

    public static void main(String[] args) {
        new JtableTest();
    }
}
// 自定义tablemodel
/*
太高了！熟练运用Java对象和类。
自定义Foods界面,因为表头不变，所以可以直接设置为静态的
 */
class FoodsModel extends DefaultTableModel{

    // 添加表头
    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("编号");
        columns.addElement("名称");
        columns.addElement("数量");
        columns.addElement("仓库编号");
        columns.addElement("供应商编号");
    }
    // 单例模式：从系统启动到终止，整个过程只会产生一个实例。
    // 饿汉式：类加载的时候就实例化，并且创建单例对象。
    private FoodsModel(){
        super(null,columns);
    }
    private static FoodsModel foodsModel = new FoodsModel();
    // 填入数据的方法
    public static FoodsModel assembleModel(Vector<Vector<Object>> data){
        foodsModel.setDataVector(data,columns);
        return foodsModel;

    }
    @Override// 表格不可编辑
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}

