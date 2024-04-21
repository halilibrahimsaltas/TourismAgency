package entity;


import business.HotelManager;
import core.ComboItem;

import java.time.LocalDate;

public class Season {
    private  int id;

    private  int hotelId;

    private LocalDate startDate;

    private LocalDate finishDate;

    private Hotel hotel;



    public Season(int id, LocalDate startDate, LocalDate finishDate) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.hotel= new Hotel();

    }

    public Season() {
    }
    public ComboItem getComboItem() {
        return new ComboItem(this.getId(),"Hotel ID: "+this.hotelId+" : "+this.getStartDate()+" ---- "+this.getFinishDate());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }
}
