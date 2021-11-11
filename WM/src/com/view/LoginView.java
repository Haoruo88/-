package com.view;

import com.handler.LoginHandler;

import javax.swing.*;
import java.awt.*;
/*
登录界面
449 120
 */

public class LoginView extends JFrame{
    JLabel nameLabel = new JLabel("仓库管理系统",SwingConstants.CENTER);
    
    SpringLayout springLayout = new SpringLayout();//弹簧布局
    JPanel centerPanel = new JPanel(springLayout);//!@设置中间面板为弹簧布局
    JLabel userLabel = new JLabel("用户名：");
    JTextField userTxt = new JTextField();
    JLabel pwdLabel = new JLabel("密码：");
    JTextField pwdTxt = new JTextField();
    JButton loginBtn = new JButton("登录");
    JButton resetBtn = new JButton("重置");
    LoginHandler loginHandler;

    public LoginView() throws HeadlessException {//构造函数
        super("仓库管理系统");
        loginHandler = new LoginHandler(this);
        //获取当前窗口的内容窗格
        Container contentPane = getContentPane();
        nameLabel.setFont(new Font("华文行楷",Font.PLAIN,30));//设置字体
        nameLabel.setPreferredSize(new Dimension(0,80));
        Font font = new Font("楷体",Font.PLAIN,20);
        userLabel.setFont(font);
        userTxt.setPreferredSize(new Dimension(200,30));
        pwdLabel.setFont(font);
        pwdTxt.setPreferredSize(new Dimension(200,30));
        loginBtn.setFont(font);
        resetBtn.setFont(font);
        //将组件加入中间面板
        centerPanel.add(userLabel);
        centerPanel.add(userTxt);
        centerPanel.add(pwdLabel);
        centerPanel.add(pwdTxt);
        /* ——————给按钮增加事件——————*/
        /*
        1.写一个监听器类实现ActionListener接口
        2.创建监听器对象
        3.将监听器交给Button ，当点击时，自动调用处理方法
         */
        loginBtn.addActionListener(loginHandler);
        getRootPane().setDefaultButton(loginBtn);// 将loginBtn设置为默认按钮
        loginBtn.addKeyListener(loginHandler); // 添加按键监听器
        resetBtn.addActionListener(loginHandler);
        //————————————————————————————
        centerPanel.add(loginBtn);
        centerPanel.add(resetBtn);
        layoutCenter();//这里将布局过程写到私有函数里

        //整个容器分成两块，整体为边界布局，下部分为弹簧布局
        //contentPane.add(nameLabel,BorderLayout.NORTH);
        contentPane.add(centerPanel);
        /*设置窗口*/
        setSize(660,400);
        setLocationRelativeTo(null);//设置窗体居中，#注意设置语句前后顺序不同，可能会不显示
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭退出
        setVisible(true);//可见

    }

    private void layoutCenter() {
        //先布局：//登录界面布局，弹簧布局（先定义一个组件为参照物，其他组件参照即可！）
        /*
        SpringLayout :布局管理器
        SpringLayout.Constraints:使用弹簧布局的容器里面的组件的布局约束，每个组件对应一个。
        Spring：理解为一个能够进行四则运算的整数
         */
        // 布局userLabel
        //userLabel为参照物
        SpringLayout.Constraints userLabelC = springLayout.getConstraints(userLabel);
        userLabelC.setX(Spring.constant(150));
        userLabelC.setY(Spring.constant(120));
        // 设置userTxt , 西边和userLabel的东边多20px，北边和它对齐 (要设置的边界，要设置的组件，距离，参考的边界，参考物）
        springLayout.putConstraint(SpringLayout.WEST,userTxt,10,SpringLayout.EAST,userLabel);
        springLayout.putConstraint(SpringLayout.NORTH,userTxt,0,SpringLayout.NORTH,userLabel);
        // 设置pwdLabel ,
        springLayout.putConstraint(SpringLayout.NORTH,pwdLabel,10,SpringLayout.SOUTH,userLabel);
        springLayout.putConstraint(SpringLayout.EAST,pwdLabel,0,SpringLayout.EAST,userLabel);
        // 设置pwdTxt ,
        springLayout.putConstraint(SpringLayout.WEST,pwdTxt,10,SpringLayout.EAST,pwdLabel);
        springLayout.putConstraint(SpringLayout.NORTH,pwdTxt,0,SpringLayout.NORTH,pwdLabel);
        // 设置loginBtn ,
        SpringLayout.Constraints loginBtnC = springLayout.getConstraints(loginBtn);
        loginBtnC.setX(Spring.constant(210));
        loginBtnC.setY(Spring.constant(220));
        // 设置resetBtn
        springLayout.putConstraint(SpringLayout.WEST,resetBtn,20,SpringLayout.EAST,loginBtn);
        springLayout.putConstraint(SpringLayout.NORTH,resetBtn,0,SpringLayout.NORTH,loginBtn);
    }

    public JTextField getUserTxt() {
        return userTxt;
    }

    public JTextField getPwdTxt() {
        return pwdTxt;
    }

}
