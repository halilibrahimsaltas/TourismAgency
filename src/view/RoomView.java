package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RoomView extends  Layout{

    // Components in the view
    private JPanel container;
    private JPanel pnl_header;
    private JLabel lbl_header;
    private JButton btn_logout;
    private JPanel pnl_body;
    private JLabel lbl_hotel_id;
    private JTextField fld_hotel_id;
    private JLabel lbl_season_id;
    private JTextField fld_season_id;
    private JLabel lbl_pension_id;
    private JTextField fld_pension_id;
    private JLabel lbl_type;
    private JTextField fld_type;
    private JLabel lbl_bed_number;
    private JTextField fld_bed_number;
    private JLabel lbl_size;
    private JTextField fld_size;
    private JLabel lbl_room_extra;
    private JCheckBox chk_tv;
    private JCheckBox chk_minibar;
    private JCheckBox chk_game_console;
    private JCheckBox chk_chest;
    private JCheckBox chk_projection;
    private JButton btn_save;
    private JLabel lbl_stock;
    private JTextField fld_stock;
    private JComboBox<ComboItem> cmb_season;
    private JComboBox<ComboItem> cmb_hotel;
    private JComboBox<ComboItem> cmb_pension;
    private JComboBox<Room.type> cmb_room;
    private JLabel lbl_adultPrice;
    private JTextField fld_adult_price;
    private JLabel lbl_childPrice;
    private JTextField fld_child_price;

    private final Room room;



    // Instance variables
    private final RoomManager roomManager;

    private final SeasonManager seasonManager;

    private final HotelManager hotelManager;

    private final PensionManager pensionManager;


    // Constructor for RoomView with Room object
    public RoomView(Room room) {
        this.room=room;
        this.roomManager= new RoomManager();
        this.seasonManager= new SeasonManager();
        this.hotelManager=new HotelManager();
        this.pensionManager= new PensionManager();

        this.add(container);
        this.guiInitilaze(600,700);


         cmb_season.removeAllItems();
         cmb_pension.removeAllItems();

        this.cmb_room.setModel(new DefaultComboBoxModel<>(Room.type.values()));


        cmb_hotel.addActionListener(e -> {
            cmb_season.removeAllItems();
            cmb_pension.removeAllItems();
            ComboItem  selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();

            for (Season season : seasonManager.getAllByHotelId(selectedHotel.getKey())){
                this.cmb_season.addItem( new ComboItem(season.getId(), season.getSeasonName()));
            }
            for(Pension pension: this.pensionManager.getAllByHotelId(selectedHotel.getKey())){
                this.cmb_pension.addItem(new ComboItem(pension.getId(),pension.getType().toString()));
            }

        });



        // Populate combo boxes with hotel data
        for (Hotel hotel: this.hotelManager.findAll()){
            this.cmb_hotel.addItem( new ComboItem(hotel.getId(), hotel.getName()));
        }




        // Populate combo boxes with season data


        // Method to populate room fields with room details
        if(this.room.getId() != 0){
            ComboItem defaultHotel = new ComboItem(this.room.getHotel().getId(),this.room.getHotel().getName());
            this.cmb_hotel.getModel().setSelectedItem(defaultHotel);
            this.cmb_season.getModel().setSelectedItem(this.room.getSeason().getSeasonName());
            this.cmb_pension.getModel().setSelectedItem(this.room.getPension().getType());
            this.cmb_room.getModel().setSelectedItem(this.room.getType());
            this.fld_bed_number.setText(Integer.toString(this.room.getBedNumber()));
            this.fld_size.setText(Integer.toString(this.room.getSize()));
            this.chk_tv.setSelected(this.room.isTv());
            this.chk_minibar.setSelected(this.room.isMinibar());
            this.chk_game_console.setSelected(this.room.isGameConsole());
            this.chk_chest.setSelected(this.room.isChest());
            this.chk_projection.setSelected(this.room.isProjection());
            this.fld_stock.setText(Integer.toString(this.room.getStock()));
            this.fld_adult_price.setText(Double.toString(this.room.getAdultPrice()));
            this.fld_child_price.setText(Double.toString(this.room.getChildPrice()));

        }

        // Method to save or update room
        btn_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_bed_number,this.fld_size,this.fld_stock})) {
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                ComboItem  selectedHotel = (ComboItem) cmb_hotel.getSelectedItem();
                ComboItem  selectedSeason = (ComboItem) cmb_season.getSelectedItem();
                ComboItem  selectedPension = (ComboItem) cmb_pension.getSelectedItem();
                this.room.setHotelId(selectedHotel.getKey());
                this.room.setSeasonId(selectedSeason.getKey());
                this.room.setPensionId(selectedPension.getKey());
                this.room.setType((Room.type) cmb_room.getSelectedItem());
                this.room.setBedNumber(Integer.parseInt(fld_bed_number.getText()));
                this.room.setSize(Integer.parseInt(fld_size.getText()));
                this.room.setTv(chk_tv.isSelected());
                this.room.setMinibar(chk_minibar.isSelected());
                this.room.setGameConsole(chk_game_console.isSelected());
                this.room.setChest(chk_chest.isSelected());
                this.room.setProjection(chk_projection.isSelected());
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setAdultPrice(Double.parseDouble(fld_adult_price.getText()));
                this.room.setChildPrice(Double.parseDouble(fld_child_price.getText()));
                if (this.room.getId() !=0){
                    result= this.roomManager.update(this.room);

                }else {
                    result = this.roomManager.save(this.room);
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
