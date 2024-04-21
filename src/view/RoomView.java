package view;

import business.HotelManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;

public class RoomView extends  Layout{
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
    private JComboBox<Pension.type> cmb_pension;
    private JComboBox<ComboItem> cmb_room;

    private final Room room;

    private final Hotel hotel;
    private final Pension pension;

    private final Season season;

    private final RoomManager roomManager;

    private final SeasonManager seasonManager;

    private final HotelManager hotelManager;


    public RoomView(Room room) {
        this.room=room;
        this.hotel=new Hotel();
        this.pension=new Pension();
        this.season= new Season();
        this.roomManager= new RoomManager();
        this.seasonManager= new SeasonManager();
        this.hotelManager=new HotelManager();

        this.add(container);
        this.guiInitilaze(500,700);

        this.cmb_pension.setModel(new DefaultComboBoxModel<>(Pension.type.values()));
        for (Hotel hotel: this.hotelManager.findAll()){
            this.cmb_hotel.addItem( new ComboItem(hotel.getId(), hotel.getName()));
        }
        for (Room room1: this.roomManager.findAll()){
            this.cmb_room.addItem( new ComboItem(room1.getId(), room1.getType().toString()));
        }

        for (Season season : this.seasonManager.findAll()){
            this.cmb_season.addItem(season.getComboItem());
        }

        if(this.room.getId() != 0){
            ComboItem selectedItem = hotel.getComboItem();
            ComboItem selectedItemp = pension.getComboItem();
            ComboItem selectedItems= season.getComboItem();
            this.cmb_hotel.setSelectedItem(selectedItem);
            this.cmb_season.setSelectedItem(selectedItems);
            this.cmb_pension.setSelectedItem(selectedItemp);
            this.cmb_room.setSelectedItem(this.room.getType());
            this.fld_bed_number.setText(Integer.toString(this.room.getBedNumber()));
            this.fld_size.setText(Integer.toString(this.room.getSize()));
            this.chk_tv.setSelected(this.room.isTv());
            this.chk_minibar.setSelected(this.room.isMinibar());
            this.chk_game_console.setSelected(this.room.isGameConsole());
            this.chk_chest.setSelected(this.room.isChest());
            this.chk_projection.setSelected(this.room.isProjection());
            this.fld_stock.setText(Integer.toString(this.room.getStock()));

        }

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
