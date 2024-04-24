package view;

import business.SeasonManager;
import core.Helper;
import entity.Season;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView  extends Layout{
    // Components in the view
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
    private JLabel lbl_seasoon_name;
    private JTextField fld_season_name;

    private final SeasonManager seasonManager;

    private final Season season;

    // Constructor for SeasonView with Season object
    public SeasonView(Season season) {
        this.season=season;
        this.seasonManager= new SeasonManager();
        this.add(container);
        this.guiInitilaze(400,400);

        // Set date formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Populate fields with season details if it already exists
        if(this.season.getId() != 0){
            this.fld_hotel_id.setText(Integer.toString(this.season.getHotelId()));
            this.fld_start_date.setText(this.season.getStartDate().format(formatter));
            this.fld_finish_date.setText(this.season.getFinishDate().format(formatter));
            this.fld_season_name.setText(this.season.getSeasonName());

        }


        // Method to save or update season
        bnt_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_hotel_id,this.fld_start_date,this.fld_finish_date,this.fld_season_name})) {
                Helper.showMsg("fill");
            }else{
                boolean result=false;
                this.season.setHotelId(Integer.parseInt(fld_hotel_id.getText()));
                this.season.setStartDate(LocalDate.parse(fld_start_date.getText(), formatter));
                this.season.setFinishDate(LocalDate.parse(fld_finish_date.getText(), formatter));
                this.season.setSeasonName(fld_season_name.getText());
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
