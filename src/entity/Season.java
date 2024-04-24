package entity;


import business.HotelManager;
import core.ComboItem;

import java.time.LocalDate;

public class Season {

    // Fields
    private  int id;

    private  int hotelId;

    private LocalDate startDate;

    private LocalDate finishDate;

    private String seasonName;

    private Hotel hotel;


    // Constructor with parameters
    public Season(int id, LocalDate startDate, LocalDate finishDate,Hotel hotel,String seasonName) {
        this.id = id;
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.hotel=hotel;
        this.seasonName=seasonName;

    }



    public Season() {
    }
    // Method to create a ComboItem from hotel's  and name
    public ComboItem getComboItem() {
        return new ComboItem(this.getId(),this.getHotel().getName()+" : "+this.getSeasonName());
    }


    // Getters and setters for all fields
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }
}
