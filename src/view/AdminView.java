package view;

import business.UserManager;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class AdminView extends Layout {
    private final User user;
    private JPanel container;
    private JPanel pnl_top;
    private JLabel lbl_welcome;
    private JTabbedPane tb_menu;
    private JButton btn_logout;
    private JScrollPane scl_employee;
    private JTable tbl_employee;

    private DefaultTableModel tmdl_user = new DefaultTableModel();

    private UserManager userManager;

    public AdminView(User user) {


        this.add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;
        this.userManager= new UserManager();

        if (this.user == null) {
            dispose();

        }

        this.lbl_welcome.setText("Welcome  " + this.user.getUsername() + " ! ");

      loadUserTable();

    }
    public void loadUserTable() {
        Object[] col_user = {"User ID", "User Name","User Password", "User Role"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length);
        this.createTable(this.tmdl_user, this.tbl_employee, col_user, userList);

    }



}
