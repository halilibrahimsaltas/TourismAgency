package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;

public class HotelView extends  Layout{

    // Components in the view
    private JPanel container;
    private JPanel pnl_header;
    private JLabel lbl_hotel_add;
    private JButton btn_logout;
    private JPanel pnl_body;
    private JLabel lbl_name;
    private JTextField fld_name;
    private JLabel lbl_city;
    private JTextField fld_city;
    private JLabel lbl_district;
    private JTextField fld_district;
    private JLabel lbl_address;
    private JTextField fld_address;
    private JLabel lbl_mail;
    private JTextField fld_mail;
    private JLabel lbl_phone;
    private JTextField fld_phone;
    private JLabel lbl_star;
    private JTextField fld_star;
    private JLabel lbl_pension;
    private JLabel pnl_facilities;

    private JCheckBox chk_park;
    private JCheckBox chk_wifi;
    private JCheckBox chk_pool;
    private JCheckBox chk_fitness;
    private JCheckBox chk_concierge;
    private JCheckBox chk_spa;
    private JCheckBox chk_service;
    private JButton btn_save;

    private JTextField fld_hotel_pension_type;

    private Hotel hotel;

    // Business logic manager for Hotel operations
    private HotelManager hotelManager;

    private Pension pension;


    // Constructor for HotelView
    public HotelView(Hotel hotel) {
        this.pension=new Pension();
        this.hotel=hotel;
        this.hotelManager= new HotelManager();
        this.add(container);
        this.guiInitilaze(550,600);


        // Fill form fields with hotel data if hotel exists (for update operation)
        if(this.hotel.getId() != 0){
            this.fld_name.setText(this.hotel.getName());
            this.fld_city.setText(this.hotel.getCity());
            this.fld_district.setText(this.hotel.getDistrict());
            this.fld_address.setText(this.hotel.getAddress());
            this.fld_mail.setText(this.hotel.getMail());
            this.fld_phone.setText(this.hotel.getPhone());
            this.fld_star.setText(Integer.toString(this.hotel.getStar()));
            this.fld_hotel_pension_type.setText(this.hotel.getPension());
            this.chk_park.setSelected(this.hotel.isPark());
            this.chk_wifi.setSelected(this.hotel.isWifi());
            this.chk_pool.setSelected(this.hotel.isPool());
            this.chk_fitness.setSelected(this.hotel.isFitness());
            this.chk_concierge.setSelected(this.hotel.isConcierge());
            this.chk_spa.setSelected(this.hotel.isSpa());
            this.chk_service.setSelected(this.hotel.isService());

        }

        // Action listener for the save button
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_name,this.fld_city,this.fld_district,this.fld_address,this.fld_mail,this.fld_phone,this.fld_star,this.fld_hotel_pension_type})) {
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                // Set hotel properties from the input fields
                this.hotel.setName(fld_name.getText());
                this.hotel.setCity(fld_city.getText());
                this.hotel.setDistrict(fld_district.getText());
                this.hotel.setAddress(fld_address.getText());
                this.hotel.setMail(fld_mail.getText());
                this.hotel.setPhone(fld_phone.getText());
                this.hotel.setStar(Integer.parseInt(fld_star.getText()));
                this.hotel.setPension(fld_hotel_pension_type.getText());
                this.hotel.setPark(chk_park.isSelected());
                this.hotel.setWifi(chk_wifi.isSelected());
                this.hotel.setPool(chk_pool.isSelected());
                this.hotel.setFitness(chk_fitness.isSelected());
                this.hotel.setConcierge(chk_concierge.isSelected());
                this.hotel.setSpa(chk_spa.isSelected());
                this.hotel.setService(chk_service.isSelected());

                // Save or update the hotel based on whether it already exists
                if (this.hotel.getId() !=0){
                    result= this.hotelManager.update(this.hotel);

                }else {
                    result = this.hotelManager.save(this.hotel);
                }
                if(result){
                    Helper.showMsg("done");
                    dispose();
                }else {
                    Helper.showMsg("error");
                }
            }

        });


    }

}
