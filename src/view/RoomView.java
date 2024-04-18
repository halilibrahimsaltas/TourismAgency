package view;

import business.RoomManager;
import core.Helper;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    private Room room;

    private RoomManager roomManager;

    public RoomView(Room room) {
        this.room=room;
        this.roomManager= new RoomManager();
        this.add(container);
        this.guiInitilaze(500,700);

        if(this.room.getId() != 0){
            this.fld_hotel_id.setText(Integer.toString(this.room.getHotelId()));
            this.fld_season_id.setText(Integer.toString(this.room.getSeasonId()));
            this.fld_pension_id.setText(Integer.toString(this.room.getPensionId()));
            this.fld_type.setText(this.room.getType());
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
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_id,this.fld_season_id,this.fld_pension_id,this.fld_type,this.fld_bed_number,this.fld_size,this.fld_stock})) {
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                this.room.setHotelId(Integer.parseInt(fld_hotel_id.getText()));
                this.room.setSeasonId(Integer.parseInt(fld_season_id.getText()));
                this.room.setPensionId(Integer.parseInt(fld_pension_id.getText()));
                this.room.setType(fld_type.getText());
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
