package view;

import business.HotelManager;
import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView  extends Layout{
    private JPanel container;
    private JPanel pnl_header;
    private JLabel lbl_header;
    private JButton btn_logout;
    private JPanel pnl_body;
    private JLabel lbl_hotel_id;
    private JTextField fld_hotel_id;
    private JLabel lbl_start_date;
    private JLabel lbl_finish_date;
    private JTextField fld_finish_date;
    private JTextField fld_start_date;
    private JButton bnt_save;

    private final SeasonManager seasonManager;

    private final Season season;

    public SeasonView(Season season) {
        this.season=season;
        this.seasonManager= new SeasonManager();
        this.add(container);
        this.guiInitilaze(400,400);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if(this.season.getId() != 0){
            this.fld_hotel_id.setText(Integer.toString(this.season.getHotelId()));
            this.fld_start_date.setText(this.season.getStartDate().format(formatter));
            this.fld_finish_date.setText(this.season.getFinishDate().format(formatter));

        }



        bnt_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_id,this.fld_start_date,this.fld_finish_date})) {
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                this.season.setHotelId(Integer.parseInt(fld_hotel_id.getText()));
                this.season.setStartDate(LocalDate.parse(fld_start_date.getText(), formatter));
                this.season.setFinishDate(LocalDate.parse(fld_finish_date.getText(), formatter));
                if (this.season.getId() !=0){
                    result= this.seasonManager.update(this.season);

                }else {
                    result = this.seasonManager.save(this.season);
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
