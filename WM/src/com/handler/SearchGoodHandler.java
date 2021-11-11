package com.handler;

import com.dao.impl.Search;
import com.entity.Goods;
import com.view.SearchGoodView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class SearchGoodHandler implements ActionListener {
    private SearchGoodView searchGoodView;
    public SearchGoodHandler(SearchGoodView searchGoodView) {
        this.searchGoodView = searchGoodView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nam = searchGoodView.getGnameTxt().getText();// 获取货物名字
        Goods goods = new Goods();
        goods.setGname(nam);
        Search searchGood = new Search();
        Vector<Vector<Object>> data = new Vector<>();
        data = searchGood.returnGoods(goods);
        Vector<String> columns = searchGoodView.getColumns();
        searchGoodView.getGoodsModel().setDataVector(data,columns);
    }
}
