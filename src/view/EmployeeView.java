package view;

import business.*;
import core.Helper;
import dao.RoomDao;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    private JPanel pnl_room;
    private JScrollPane scl_room;
    private JTable tbl_room;
    private JTable tbl_pension;
    private JPanel pnl_pension;
    private JScrollPane scl_pension;
    private JPanel pnl_reservation;
    private JTable tbl_reservation;
    private JScrollPane scl_reservation;
    private JPanel pnl_search;
    private JLabel lbl_namecity_search;
    private JTextField fld_namecity_search;
    private JLabel lbl_checkin;
    private JTextField fld_checkin;
    private JLabel lbl_checkout;
    private JTextField fld_checkout;
    private JLabel lbl_guestnumber;
    private JTextField fld_guestinfo;
    private JButton btn_search;
    private JPanel pnl_reservation_room;
    private JScrollPane scl_reservation_room;
    private JTable tbl_reservation_room;
    private JPanel pnl_reservation_header;
    private JLabel lbl_reservation_headers;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();

    private DefaultTableModel tmdl_season = new DefaultTableModel();

    private DefaultTableModel tmdl_room= new DefaultTableModel();

    private DefaultTableModel tmdl_pension= new DefaultTableModel();

    private DefaultTableModel tmdl_reservation= new DefaultTableModel();


    private Object[] col_hotel;

    private Object[] col_season;

    private Object[] col_room;

    private Object[] col_pension;

    private Object[] col_reservation;

    private Object[] getCol_reservation_room;

    private User user;

    private JPopupMenu hotel_menu;

    private JPopupMenu season_menu;

    private JPopupMenu room_menu;

    private  JPopupMenu pension_menu;

    private JPopupMenu reservation_menu;

    private UserManager userManager;

    private HotelManager hotelManager;

    private SeasonManager seasonManager;

    private RoomManager roomManager;

    private PensionManager pensionManager;

    private ReservationManager reservationManager;

    private RoomDao roomDao;

    private HotelView hotelView;

    private RoomView roomView;

    private PensionView pensionView;





    public EmployeeView(User user) {
        this.add(conteiner);
        this.guiInitilaze(1200, 500);
        this.user = user;
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.seasonManager= new SeasonManager();
        this.roomManager= new RoomManager();
        this.pensionManager= new PensionManager();
        this.reservationManager= new ReservationManager();

        this.lbl_welcome.setText("Welcome  " + this.user.getUsername() + " ! ");

        //Hotel İnfo
        loadHotelTable(null);
        loadHotelComponent();

        //Season İnfo
        loadSeasonTable(null);
        loadSeasonComponent();

        //Room İnfo
        loadRoomTable(null);
        loadRoomComponent();

        //Pension İnfo
        loadPensionTable(null);
        loadPensionComponent();

        //Reservation İnfo
        loadReservationTable(null);
        loadReservationComponent();

        //ReservationRoom İnfo
        loadReservationRoomTable(null);
        loadReservationRoomComponent();

    }


    private void loadReservationRoomTable(ArrayList<Object[]> reservationRoomList) {
        getCol_reservation_room = new Object[]{"Room ID", "Hotel ", "City ", "Season", "Pension Type", "Adult Price", "Child Price", "Stock"};
        if (reservationRoomList == null){
            reservationRoomList = this.reservationManager.getForRoomTable(this.getCol_reservation_room.length, this.reservationManager.findAll());
        }
        this.createTable(this.tmdl_room, this.tbl_reservation_room, getCol_reservation_room, reservationRoomList);
    }


    private void loadReservationTable(ArrayList<Object[]> reservationList) {
        col_reservation=new Object[]{"ID","Hotel","Customer Name","Customer CN","C. Address","C.Mail","C.Mpno","Note","Check-In","Check-Out","Price","T.Guest"};
        if(reservationList== null){
            reservationList= this.reservationManager.getForTable(this.col_reservation.length, this.reservationManager.findAll());
        }
        this.createTable(this.tmdl_reservation,this.tbl_reservation,col_reservation,reservationList);

    }


    private void loadPensionTable(ArrayList<Object[]> pensionList) {
        col_pension= new  Object[]{"ID","Pension Type","Hotel "};
        if(pensionList== null){
            pensionList= this.pensionManager.getForTable(this.col_pension.length, this.pensionManager.findAll());
        }
        this.createTable(this.tmdl_pension,this.tbl_pension,col_pension, pensionList);

    }


    private void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel ", "Season ", "Pension ", "Type", "Bed Number", "Size", "TV", "Minibar", "GameCons.", "Chest", "Projection", "Stock"};
        if (roomList == null){
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }

    private void loadSeasonTable(ArrayList<Object[]> seasonList) {
        col_season = new Object[]{"ID","Hotel ","Season Start Date","Season Finish Date"};
        if (seasonList == null){
            seasonList = this.seasonManager.getForTable(this.col_season.length, this.seasonManager.findAll());
        }
        this.createTable(this.tmdl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        col_hotel = new Object[]{"ID", "Name", "City", "District", "Address", "Mail", "Phone", "Star", "Pension", "Park", "WiFi", "Pool", "Fitness", "Concierge", "SPA", "RoomSer","Adult Price","Child Price"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.hotelManager.findAll());
        }
        this.createTable(this.tmdl_hotel, this.tbl_hotel_menu, col_hotel, hotelList);

    }
    private void loadPensionComponent() {
        tableRowSelected(this.tbl_pension);
        this.pension_menu = new JPopupMenu();
        this.pension_menu.add("New").addActionListener(e -> {
            PensionView pensionView1 = new PensionView(new Pension());
            pensionView1.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable(null);
                    loadSeasonTable(null);

                }
            });
        });
        this.pension_menu.add("Update").addActionListener(e -> {
            int selectPensionId = this.getTableSelectedRow(tbl_pension, 0);
            PensionView pensionView1 = new PensionView(this.pensionManager.getById(selectPensionId));
            pensionView1.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);

                }
            });

        });
        this.pension_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectPensionId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.seasonManager.delete(selectPensionId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);
                    loadPensionTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_pension.setComponentPopupMenu(pension_menu);


    }
    private void loadReservationComponent() {
        tableRowSelected(this.tbl_reservation);
        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("Update").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);
                    loadRoomTable(null);

                }
            });

        });
        this.reservation_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
                this.reservationManager.getById(selectReservationId).getRoom().setStock((this.reservationManager.getById(selectReservationId).getRoom().getStock()+this.reservationManager.getById(selectReservationId).getTotalGuest()));
                this.roomManager.update(this.reservationManager.getById(selectReservationId).getRoom());
                if (this.reservationManager.delete(selectReservationId)) {
                    Helper.showMsg("done");
                    loadReservationTable(null);
                    loadRoomTable(null);
                    loadReservationRoomTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_reservation.setComponentPopupMenu(reservation_menu);


    }
    private void loadReservationRoomComponent() {
        tableRowSelected(this.tbl_reservation_room);
        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("New").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation_room, 0);
            ReservationView reservationView = new ReservationView(this.roomManager.getById(selectReservationId),new Reservation());
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationRoomTable(null);
                    loadReservationTable(null);
                    loadRoomTable(null);

                }
            });
        });

        btn_search.addActionListener(e -> {
            try{ArrayList<Room> roomList = this.roomManager.searchForReservation(
                    fld_namecity_search.getText(),
                    fld_checkin.getText(),
                    Integer.parseInt(fld_guestinfo.getText())

            );
                ArrayList<Object[]> roomReservationRow= this.roomManager.getForTable(this.col_room.length,roomList);
                loadReservationRoomTable(roomReservationRow);

            }catch (NumberFormatException ex) {

                ex.printStackTrace();
            } catch (DateTimeParseException ex) {

                ex.printStackTrace();
            }
        });

        this.tbl_reservation_room.setComponentPopupMenu(reservation_menu);

    }

    private void loadRoomComponent() {
        tableRowSelected(this.tbl_room);
        this.room_menu = new JPopupMenu();
        this.room_menu.add("New").addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);

                }
            });
        });
        this.room_menu.add("Update").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
            RoomView roomView = new RoomView(this.roomManager.getById(selectRoomId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);

                }
            });

        });
        this.room_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectSeasonId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_room.setComponentPopupMenu(room_menu);

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
                    loadHotelTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);

                }
            });

        });
        this.season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectSeasonId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectSeasonId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);

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
                    loadRoomTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);
                    loadPensionTable(null);

                }
            });

        });
        this.hotel_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectHotelId = this.getTableSelectedRow(tbl_hotel_menu, 0);
                if (this.hotelManager.delete(selectHotelId)) {
                    Helper.showMsg("done");
                    loadHotelTable(null);
                    loadRoomTable(null);
                    loadSeasonTable(null);
                    loadRoomTable(null);
                    loadPensionTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }

        });

        this.tbl_hotel_menu.setComponentPopupMenu(hotel_menu);

    }



}
