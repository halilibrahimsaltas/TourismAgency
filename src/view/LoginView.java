package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginView extends  Layout{
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_header;
    private JLabel lbl_header2;
    private JPanel pnl_bottom;
    private JTextField fld_username;
    private JTextField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;

    private  final UserManager userManager;

    public  LoginView(){

        this.userManager= new UserManager();

        add(container);
        this.guiInitilaze(400,400);
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = { this.fld_username, this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else{
                User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_password.getText());
                if(loginUser== null){
                    Helper.showMsg("notFound");
                }else {
                    new AdminView(loginUser);
                    dispose();
                }
            }

        });
    }



}



