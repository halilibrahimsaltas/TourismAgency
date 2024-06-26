package view;

import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationView extends  Layout{
    // Components in the view
    private JPanel container;
    private JPanel pnl_header;
    private JLabel lbl_header;
    private JPanel lbl_body;
    private JLabel lbl_hotel_info;
    private JLabel lbl_hotel_name;
    private JTextField fld_hotel_name;
    private JLabel lbl_hotel_city;
    private JTextField fld_hotel_city;
    private JLabel lbl_hotel_star;
    private JTextField fld_star;
    private JCheckBox chk_wifi;
    private JCheckBox chk_concierge;
    private JCheckBox chk_otopark;
    private JCheckBox chk_fitness;
    private JCheckBox chk_spa;
    private JCheckBox chk_service;
    private JCheckBox chk_pool;
    private JPanel pnl_room;
    private JLabel lbl_room_info;
    private JLabel lbl_room_type;
    private JTextField fld_room_type;
    private JLabel lbl_bed_cap;
    private JTextField fld_bed_capacity;
    private JLabel lbl_pension_type;
    private JTextField fld_pension_type;
    private JLabel lbl_room_size;
    private JTextField fld_room_size;
    private JLabel lbl_start_date;
    private JTextField fld_start_date;
    private JLabel lbl_finish_date;
    private JTextField fld_finish_date;
    private JLabel lbl_price;
    private JTextField fld_price;
    private JCheckBox chk_tv;
    private JCheckBox chk_minibar;
    private JCheckBox chk_game_console;
    private JCheckBox chk_chest;
    private JCheckBox chk_projection;
    private JPanel lbl_custumer_info;
    private JLabel lbl_customer_info;
    private JLabel lbl_total_guest;
    private JTextField fld_total_guest;
    private JLabel lbl_customer_name;
    private JTextField fld_customerName;
    private JLabel lbl_customer_citizenId;
    private JTextField fld_customer_citizenId;
    private JLabel lbl_customer_mail;
    private JTextField fld_mail;
    private JLabel lbl_phone;
    private JTextField fld_mpno;
    private JButton btn_save;
    private JButton btn_logout;
    private JLabel lbl_child;
    private JTextField fld_child_guest;
    private JLabel lbl_total_price;



    private Reservation reservation;

    private Room room;

    private RoomManager roomManager;
    private ReservationManager reservationManager;

    // Constructor for ReservationView with Reservation object
    public ReservationView(Reservation reservation) {
        this.reservation=reservation;
        this.room=new Room();
        this.reservationManager = new ReservationManager();
        this.roomManager=new RoomManager();

        this.add(container);
        this.guiInitilaze(900,700);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");


        if(this.reservation.getId()!=0){
            this.fld_hotel_name.setText(this.reservation.getRoom().getHotel().getName());
            this.fld_hotel_city.setText(this.reservation.getRoom().getHotel().getCity());
            this.fld_star.setText(Integer.toString(this.reservation.getRoom().getHotel().getStar()));
            this.chk_otopark.setSelected(this.reservation.getRoom().getHotel().isPark());
            this.chk_spa.setSelected(this.reservation.getRoom().getHotel().isSpa());
            this.chk_concierge.setSelected(this.reservation.getRoom().getHotel().isConcierge());
            this.chk_fitness.setSelected(this.reservation.getRoom().getHotel().isFitness());
            this.chk_pool.setSelected(this.reservation.getRoom().getHotel().isPool());
            this.chk_service.setSelected(this.reservation.getRoom().getHotel().isService());
            this.chk_wifi.setSelected(this.reservation.getRoom().getHotel().isWifi());
            this.fld_room_type.setText(this.reservation.getRoom().getType().toString());
            this.fld_pension_type.setText(this.reservation.getRoom().getPension().getType().toString());
            this.fld_bed_capacity.setText(Integer.toString(this.reservation.getRoom().getBedNumber()));
            this.fld_room_size.setText(Integer.toString(this.reservation.getRoom().getSize()));
            this.fld_start_date.setText(this.reservation.getCheckIn().format(formatter));
            this.fld_finish_date.setText(this.reservation.getCheckOut().format(formatter));
            this.fld_price.setText(Double.toString(this.reservation.getTotalPrice()));
            this.chk_tv.setSelected(this.reservation.getRoom().isTv());
            this.chk_minibar.setSelected(this.reservation.getRoom().isMinibar());
            this.chk_game_console.setSelected(this.reservation.getRoom().isGameConsole());
            this.chk_chest.setSelected(this.reservation.getRoom().isChest());
            this.chk_projection.setSelected(this.reservation.getRoom().isProjection());
            this.fld_total_guest.setText(Integer.toString(this.reservation.getTotalGuest()));
            this.fld_customerName.setText(this.reservation.getCustomerName());
            this.fld_customer_citizenId.setText(this.reservation.getCustomerCN());
            this.fld_mail.setText(this.reservation.getCustomerMail());
            this.fld_mpno.setText(this.reservation.getCustomerMpno());

        }


        // Action listener for the save button
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_customerName,this.fld_customer_citizenId,this.fld_mail,this.fld_mpno,this.fld_total_guest,this.fld_start_date,this.fld_finish_date})) {
                Helper.showMsg("fill");
            }else{
                int childCount=0;
                if (!fld_child_guest.getText().isEmpty()) {
                    try {
                        childCount = Integer.parseInt(fld_child_guest.getText());
                    } catch (NumberFormatException t) {
                        t.printStackTrace();
                    }
                }
                double totalPrice=((((Integer.parseInt(fld_total_guest.getText())-childCount)*this.reservation.getRoom().getAdultPrice())+(this.reservation.getRoom().getChildPrice()*childCount))* ChronoUnit.DAYS.between(LocalDate.parse(this.fld_start_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalDate.parse(this.fld_finish_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                boolean result=false;
                this.reservation.setCustomerName(fld_customerName.getText());
                this.reservation.setCustomerCN(fld_customer_citizenId.getText());
                this.reservation.setCustomerMail(fld_mail.getText());
                this.reservation.setCustomerMpno(fld_mpno.getText());
                this.reservation.setTotalGuest(Integer.parseInt(fld_total_guest.getText()));
                this.reservation.setCheckIn(LocalDate.parse(fld_start_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                this.reservation.setCheckOut(LocalDate.parse(fld_finish_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                this.reservation.setTotalPrice(totalPrice);
                if (this.reservation.getId() !=0){
                    result= this.reservationManager.update(this.reservation);

                }
                if(result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });
        // Add DocumentListener to fld_total_guest
        fld_total_guest.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPrice();
            }
        });

        // Add DocumentListener to fld_child_guest
        fld_child_guest.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPrice();
            }
        });



    }
    public ReservationView(Room room, Reservation reservation) {
        this.room=room;
        this.reservation=new Reservation();
        this.reservationManager = new ReservationManager();
        this.roomManager=new RoomManager();

        this.add(container);
        this.guiInitilaze(900,700);

        if (this.room.getId() !=0){
            this.fld_hotel_name.setText(this.room.getHotel().getName());
            this.fld_hotel_city.setText(this.room.getHotel().getCity());
            this.fld_star.setText(Integer.toString(this.room.getHotel().getStar()));
            this.chk_otopark.setSelected(this.room.getHotel().isPark());
            this.chk_spa.setSelected(this.room.getHotel().isSpa());
            this.chk_concierge.setSelected(this.room.getHotel().isConcierge());
            this.chk_fitness.setSelected(this.room.getHotel().isFitness());
            this.chk_pool.setSelected(this.room.getHotel().isPool());
            this.chk_service.setSelected(this.room.getHotel().isService());
            this.chk_wifi.setSelected(this.room.getHotel().isWifi());
            this.fld_room_type.setText(this.room.getType().toString());
            this.fld_pension_type.setText(this.room.getPension().getType().toString());
            this.fld_bed_capacity.setText(Integer.toString(this.room.getBedNumber()));
            this.fld_room_size.setText(Integer.toString(this.room.getSize()));
            this.chk_tv.setSelected(this.room.isTv());
            this.chk_minibar.setSelected(this.room.isMinibar());
            this.chk_game_console.setSelected(this.room.isGameConsole());
            this.chk_chest.setSelected(this.room.isChest());
            this.chk_projection.setSelected(this.room.isProjection());
        }

        // Action listener for the save button
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_customerName,this.fld_customer_citizenId,this.fld_mail,this.fld_mpno,this.fld_total_guest,this.fld_start_date,this.fld_finish_date})) {
                Helper.showMsg("fill");
            }else{
                int childCount=0;
                if (!fld_child_guest.getText().isEmpty()) {
                    try {
                        childCount = Integer.parseInt(fld_child_guest.getText());
                    } catch (NumberFormatException t) {
                        t.printStackTrace();
                    }
                }
                double totalPrice=((((Integer.parseInt(fld_total_guest.getText())-childCount)*this.room.getAdultPrice())+(this.room.getChildPrice()*childCount))* ChronoUnit.DAYS.between(LocalDate.parse(this.fld_start_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")),LocalDate.parse(this.fld_finish_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                boolean result=false;
                this.reservation.setCustomerName(fld_customerName.getText());
                this.reservation.setCustomerCN(fld_customer_citizenId.getText());
                this.reservation.setCustomerMail(fld_mail.getText());
                this.reservation.setCustomerMpno(fld_mpno.getText());
                this.reservation.setTotalGuest(Integer.parseInt(fld_total_guest.getText()));
                this.reservation.setCheckIn(LocalDate.parse(fld_start_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                this.reservation.setCheckOut(LocalDate.parse(fld_finish_date.getText(),DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                this.reservation.setTotalPrice(totalPrice);
                this.room.setStock((this.room.getStock()-Integer.parseInt(fld_total_guest.getText())));
                if (this.reservation.getId() !=0){
                    result= this.reservationManager.update(this.reservation);

                }else {
                    result = this.reservationManager.save(this.room,this.reservation);
                    this.roomManager.update(this.room);
                }
                if(result){
                    Helper.showMsg("done");

                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });
        // Add DocumentListener to fld_total_guest
        fld_total_guest.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPriceForRoom();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPriceForRoom();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPriceForRoom();
            }
        });

        // Add DocumentListener to fld_child_guest
        fld_child_guest.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTotalPriceForRoom();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTotalPriceForRoom();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTotalPriceForRoom();
            }
        });



    }
    // Method to update total price
    private void updateTotalPrice() {
        try {
        int totalGuest = fld_total_guest.getText().isEmpty() ? 0 : Integer.parseInt(fld_total_guest.getText());
        int childGuest = fld_child_guest.getText().isEmpty() ? 0 : Integer.parseInt(fld_child_guest.getText());
        LocalDate startDate = LocalDate.parse(this.fld_start_date.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate finishDate = LocalDate.parse(this.fld_finish_date.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        double totalPrice = ((((totalGuest - childGuest) * this.reservation.getRoom().getAdultPrice()) +
                (this.reservation.getRoom().getChildPrice() * childGuest)) *
                ChronoUnit.DAYS.between(startDate, finishDate));

        this.lbl_total_price.setText("Total Price: " + totalPrice + " TL");
        } catch (NumberFormatException | java.time.format.DateTimeParseException ex) {
            // Handle the case where parsing fails or date format is invalid
            lbl_total_price.setText("Total Price: Invalid input");
        }
    }
    // Method to update total price
    private void updateTotalPriceForRoom() {
        try {
            int totalGuest = fld_total_guest.getText().isEmpty() ? 0 : Integer.parseInt(fld_total_guest.getText());
            int childGuest = fld_child_guest.getText().isEmpty() ? 0 : Integer.parseInt(fld_child_guest.getText());
            LocalDate startDate = LocalDate.parse(this.fld_start_date.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            LocalDate finishDate = LocalDate.parse(this.fld_finish_date.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            double totalPrice = ((((totalGuest - childGuest) * this.room.getAdultPrice()) +
                    (this.room.getChildPrice() * childGuest)) *
                    ChronoUnit.DAYS.between(startDate, finishDate));

            this.lbl_total_price.setText("Total Price: " + totalPrice + " TL");
        } catch (NumberFormatException | java.time.format.DateTimeParseException ex) {
            // Handle the case where parsing fails or date format is invalid
            lbl_total_price.setText("Total Price: Invalid input");
        }
    }


}
