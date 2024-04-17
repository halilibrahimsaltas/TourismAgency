package view;

import business.HotelManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.User;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel conteiner;
    private JPanel pnl_header;
    private JLabel lbl_welcome;
    private JButton btn_logout;
    private JPanel pnl_employee_menu;
    private JTabbedPane tab_menu;
    private JScrollPane scl_hotel_menu;
    private JTable tbl_hotel_menu;

    private Object[] col_hotel;

    private User user;

    private UserManager userManager;

    private HotelManager hotelManager;

    public EmployeeView(User user) {
        this.add(conteiner);
        this.guiInitilaze(1000, 500);
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();

        this.lbl_welcome.setText("Welcome  " + this.user.getUsername() + " ! ");

    }
    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        col_hotel = new Object[]{"ID", "Name", "City", "District",  "Address", "Mail", "Phone", "Star", "Pension", "Park", "WiFi", "Pool", "Fitness", "Concierge", "SPA", "RoomSer"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.HotelManager.findAll());
        }
        this.createTable(this.tmdl_user, this.tbl_employee, col_user, userList);

    }
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
        btn_default.addActionListener(e -> {
            loadUserFilter();

        });


        this.tbl_employee.setComponentPopupMenu(employee_menu);


    }

    private void loadUserFilter() {
        this.cmb_role_search.setModel(new DefaultComboBoxModel<>(User.Role.values()));
        this.cmb_role_search.setSelectedItem(null);

    }
}
