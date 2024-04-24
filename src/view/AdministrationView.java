package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdministrationView extends  Layout{
    private JPanel container;
    private JPanel pnl_header;
    private JLabel lbl_welcome;
    private JPanel pnl_user_table;
    private JTabbedPane tab_employee;
    private JScrollPane scl_employee;
    private JTable tbl_employee;
    private JPanel pnl_search;
    private JComboBox<User.Role> cmb_role_search;
    private JButton btn_role_search;
    private JButton btn_logout;
    private JButton btn_default;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private JPopupMenu employee_menu;

    private UserManager userManager;
    
    private Object[] col_user;

    private  User user;

    public AdministrationView(User user) {
        this.add(container);
        this.guiInitilaze(1000, 500);
        this.user = user;
        this.userManager = new UserManager();

        if (this.user == null) {
            dispose();

        }

        //Administration
        loadUserTable(null);
        loadUserComponent();
        loadUserFilter();



    }
    // Load user table
    public void loadUserTable(ArrayList<Object[]> userList) {
        col_user = new Object[]{"User ID", "User Name", "User Password", "User Role"};
        if (userList == null) {
            userList = this.userManager.getForTable(this.col_user.length, this.userManager.findAll());
        }
        this.createTable(this.tmdl_user, this.tbl_employee, col_user, userList);

    }
    // Load user components and set up actions

    public void loadUserComponent() {
        tableRowSelected(this.tbl_employee);
        this.employee_menu = new JPopupMenu();
        this.employee_menu.add("New").addActionListener(e -> {
            UserView userView = new UserView(new User());
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                    loadUserFilter();


                }
            });
        });
        this.employee_menu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_employee, 0);
            UserView userView = new UserView(this.userManager.getById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable(null);
                    loadUserFilter();

                }
            });

        });
        this.employee_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_employee, 0);
                if (this.userManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                    loadUserFilter();

                } else {
                    Helper.showMsg("error");
                }
            }

        });
        // Action listener for role search button
        btn_role_search.addActionListener(e -> {
           User.Role selectedRole = (User.Role) this.cmb_role_search.getSelectedItem();
            int userId = 0;

            ArrayList<User> userListBySearch = this.userManager.searchForTable(
                    userId,
                    selectedRole
            );

            ArrayList<Object[]> userRow= this.userManager.getForTable(this.col_user.length,userListBySearch);
            loadUserTable(userRow);

        });

        // Action listener for default button
        btn_default.addActionListener(e -> {
            loadUserFilter();

        });

       
        this.tbl_employee.setComponentPopupMenu(employee_menu);


    }

    // Load user filter
    private void loadUserFilter() {
        this.cmb_role_search.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_role_search.setSelectedItem(null);

    }

}
