package com.handler;

import com.dao.impl.AdminServiceImpl;
import com.entity.Admin;
import com.view.GoodsView;
import com.view.LoginView;  // 要想实现操控loginView ，只能传入LoginView对象

import javax.swing.*;
import java.awt.event.*;

public class LoginHandler extends KeyAdapter implements ActionListener {  //
    private LoginView loginView;

    public LoginHandler(LoginView loginView) {
        this.loginView = loginView;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jButton = (JButton) e.getSource();
        String str = jButton.getText();
        if("登录".equals(str)){  // 比较String用equal
            login();
        }
        else if("重置".equals(str)){
            loginView.getUserTxt().setText("");
            loginView.getPwdTxt().setText("");
        }
    }

    private void login() {
        String user = loginView.getUserTxt().getText();
        String pwd = loginView.getPwdTxt().getText();
        // 进行校验，如果为空不能登录
        if(user == null || "".equals(user.trim()) || pwd == null || "".equals(pwd.trim())){
            JOptionPane.showMessageDialog(loginView,"用户名或密码不能为空！");
        }
        // 查询DB
        Admin admin = new Admin();
        admin.setAname(user);
        admin.setApassword(pwd);
        AdminServiceImpl adminService = new AdminServiceImpl();
        boolean flag = adminService.validateAdmin(admin);
        if(flag){
            //跳转到货物界面，同时销毁登录界面
            new GoodsView();
            loginView.dispose();
        }else{
            JOptionPane.showMessageDialog(loginView,"用户名或密码错误！");//（所属父容器，message），模态
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.VK_ENTER == e.getKeyCode()){
            login();
        }
    }
}
