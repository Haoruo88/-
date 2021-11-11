package com.view;

import com.handler.AddHandler;

import java.awt.*;
import javax.swing.*;

public class AddView extends ADD {
    JButton addBtn = new JButton("入库");
    AddHandler addHandler;

    public AddView() throws HeadlessException {
        super();
        super.jPanel.add(addBtn);
        addHandler = new AddHandler(this);
        addBtn.addActionListener(addHandler);
    }
}

