package view;

import business.HotelManager;
import business.SeasonManager;
import business.UserManager;
import core.Helper;
import entity.Hotel;
import entity.Season;
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

    private DefaultTableModel tmdl_season = new DefaultTableModel();

    private Object[] col_hotel;

    private Object[] col_season;

    private User user;

    private JPopupMenu hotel_menu;

    private JPopupMenu season_menu;

    private UserManager userManager;

    private HotelManager hotelManager;

    private SeasonManager seasonManager;

    private HotelView hotelView;

    public EmployeeView(User user) {
        this.add(conteiner);
        this.guiInitilaze(1200, 500);
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.seasonManager= new SeasonManager();

        this.lbl_welcome.setText("Welcome  " + this.user.getUsername() + " ! ");

        loadHotelTable(null);
        loadHotelComponent();


        loadSeasonTable(null);
        loadSeasonComponent();

    }

    private void loadSeasonTable(ArrayList<Object[]> seasonList) {
        col_season = new Object[]{"ID","Hotel ID","Season Start Date","Season Finish Date"};
        if (seasonList == null){
            seasonList = this.seasonManager.getForTable(this.col_season.length, this.seasonManager.findAll());
        }
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        col_hotel = new Object[]{"ID", "Name", "City", "District", "Address", "Mail", "Phone", "Star", "Pension", "Park", "WiFi", "Pool", "Fitness", "Concierge", "SPA", "RoomSer"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel_menu, col_hotel, hotelList);

    }
    private void loadSeasonComponent() {
        tableRowSelected(this.tbl_season);
        this.season_menu = new JPopupMenu();
        this.season_menu.add("New").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(new Season());
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);

                }
            });
        });
        this.season_menu.add("Update").addActionListener(e -> {
            int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);
            SeasonView seasonView = new SeasonView(this.seasonManager.getById(selectSeasonId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null);

                }
            });

        });
        this.season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectSeasonId)) {
                    Helper.showMsg("done");
                    loadSeasonTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_season.setComponentPopupMenu(season_menu);

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
                int selectHotelId = this.getTableSelectedRow(tbl_hotel_menu, 0);
                if (this.hotelManager.delete(selectHotelId)) {
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
