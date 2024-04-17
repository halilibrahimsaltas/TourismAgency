package view;

import business.HotelManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
    private JPanel pnl_season;
    private JScrollPane scl_season;
    private JTable tbl_season;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();

    private Object[] col_hotel;

    private User user;

    private JPopupMenu hotel_menu;

    private UserManager userManager;

    private HotelManager hotelManager;

    private HotelView hotelView;

    public EmployeeView(User user) {
        this.add(conteiner);
        this.guiInitilaze(1200, 500);
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();

        this.lbl_welcome.setText("Welcome  " + this.user.getUsername() + " ! ");

        loadHotelTable(null);
        loadHotelComponent();

    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        col_hotel = new Object[]{"ID", "Name", "City", "District", "Address", "Mail", "Phone", "Star", "Pension", "Park", "WiFi", "Pool", "Fitness", "Concierge", "SPA", "RoomSer"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel_menu, col_hotel, hotelList);

    }

    public void loadHotelComponent() {
        tableRowSelected(this.tbl_hotel_menu);
        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("New").addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);

                }
            });
        });
        this.hotel_menu.add("Update").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_hotel_menu, 0);
            HotelView hotelView = new HotelView(this.hotelManager.getById(selectUserId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);

                }
            });

        });
        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_hotel_menu, 0);
                if (this.userManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_hotel_menu.setComponentPopupMenu(hotel_menu);

    }



}
