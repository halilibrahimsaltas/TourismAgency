package entity;

import core.ComboItem;

public class Pension {
    // Fields

    private int id;

    private Pension.type type;

    private int pensionHotelId;

    private Hotel hotel;


    // Constructor with parameters
    public Pension(int id, Pension.type type,Hotel hotel) {
        this.id = id;
        this.type = type;
        this.hotel=hotel;
    }

    public Pension() {
    }

    public enum type{
        UltraAllInclusive,
        AllInclusive,
        RoomBreakfast,
        FullOPension,
        HalfBoard,
        JustBed,
        ExcludingAlcoholFullCredit

    }
    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pension.type getType() {
        return type;
    }

    public void setType(Pension.type type) {
        this.type = type;
    }

    public int getPensionHotelId() {
        return pensionHotelId;
    }

    public void setPensionHotelId(int pensionHotelId) {
        this.pensionHotelId = pensionHotelId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
