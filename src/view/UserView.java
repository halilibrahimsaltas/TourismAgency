package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserView  extends  Layout{

    // GUI components
    private JPanel container;
    private JPanel pnl_user_header;
    private JLabel lbl_user_header;
    private JLabel lbl_username;
    private JTextField fld_username;
    private JLabel lbl_password;
    private JTextField fld_password;
    private JLabel pnl_role;
    private JButton btn_save;
    private JComboBox<User.Role> cmb_role;
    private JTextField fld_role;


    // Instance variables
    private User user;

    private UserManager userManager;



    // Constructor for UserView with User object
    public UserView(User user) {
        this.add(container);
        this.userManager=new UserManager();
        this.guiInitilaze(300,500);
        this.user=user;

        // Populate role combo box with enum values
        this.cmb_role.setModel(new DefaultComboBoxModel<>(User.Role.values()));


        if(this.user.getId() != 0){
            ComboItem selectedItem = user.getComboItem();
            this.cmb_role.getModel().setSelectedItem(selectedItem);
        }


        // Populate fields with user details if it already exists
        if (user != null){
            this.fld_username.setText(user.getUsername());
            this.fld_password.setText(user.getPassword());
            this.cmb_role.setSelectedItem(user.getRole());
        }
        // Action listener for the save button
        btn_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_username)){
                Helper.showMsg("fill");
            }else {
                boolean result ;
                this.user.setUsername(this.fld_username.getText());
                this.user.setPassword(this.fld_password.getText());
                this.user.setRole((User.Role) this.cmb_role.getSelectedItem());
                if (this.user.getId() == 0){
                    result = this.userManager.save(this.user);

                }else {
                    this.user.setUsername(fld_username.getText());
                    result = this.userManager.update(this.user);
                }

                if (result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }


        });
    }


}
