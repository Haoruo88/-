package com.handler;

import com.dao.GoodsShow;
import com.dao.impl.GoodShowImpl;
import com.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class ServiceHandler implements ActionListener {
    private GoodsView goodsView;

    public ServiceHandler(GoodsView goodsView) {
        this.goodsView = goodsView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String str = jButton.getText();
        if("入库".equals(str)){
            new AddView();
        }
        else if("出库".equals(str)){
            new DeleteView();
        }
        else if("查询".equals(str)){
            String cm = (String)goodsView.getCmb().getSelectedItem();
            if(cm.equals("货物")){
                new SearchGoodView();
            }
            else if(cm.equals("入库单")){
                new InWHView();
            }
            else if(cm.equals("出库单")){
                new OutWHView();
            }
        }
        else if("刷新".equals(str)){
            // 重新获取good表数据，并设置到GoodView的gtablemodel中
            GoodShowImpl goodShow = new GoodShowImpl();
            Vector<Vector<Object>> data = goodShow.returnGoods();
            Vector<String> columns = goodsView.getColumns();
            goodsView.getGoodsModel().setDataVector(data,columns);
        }
        else if("退出".equals(str)){
            System.exit(0);
        }
    }
}
