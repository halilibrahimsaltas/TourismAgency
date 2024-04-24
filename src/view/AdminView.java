package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class AdminView extends Layout {
    private final User user;
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_role;
    private JButton btn_admin;
    private JButton btn_employee;
    private JTable tbl_employee;
    private JComboBox<User.Role> cmb_role_search;
    private JButton btn_role_search;

    private DefaultTableModel tmdl_user = new DefaultTableModel();


    private JPopupMenu admin_menu;

    private UserManager userManager;

    public AdminView(User user) {


        // Add container to the frame
        this.add(container);
        // Initialize GUI
        this.guiInitilaze(1000, 500);
        // Set the user
        this.user = user;
        // Create UserManager instance
        this.userManager = new UserManager();

        if (this.user == null) {
            dispose();

        }

        // Set welcome label text
        this.lbl_welcome.setText("Welcome  " + this.user.getUsername() + " ! ");


        // Check user role
        switch (this.user.getRole()){
            case admin -> {
                this.admin_menu = new JPopupMenu();
                AdministrationView administrationView= new AdministrationView(this.user);
                dispose();
            }
            case employee -> {
                EmployeeView employeeView = new EmployeeView(this.user);
                dispose();

            }

        }

    }

}
