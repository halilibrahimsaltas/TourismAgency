package entity;

import core.ComboItem;

public class Room {

    //Fields

    private int id;

    private int hotelId;

    private int seasonId;

    private  int pensionId;

    private Room.type type;

    private  int bedNumber;

    private  int size;

    private  boolean tv;

    private  boolean minibar;

    private  boolean gameConsole;
    private boolean chest;
    private boolean projection;
    private  int stock;

    private Hotel hotel;
    private Season season;

    private Pension pension;

    private Reservation reservation;


    // Constructor with parameters
    public Room(int id, Room.type type, int bedNumber, int size, boolean tv, boolean minibar, boolean gameConsole, boolean chest, boolean projection, int stock, Hotel hotel,Season season,Pension pension,Reservation reservation) {
        this.id = id;
        this.type = type;
        this.bedNumber = bedNumber;
        this.size = size;
        this.tv = tv;
        this.minibar = minibar;
        this.gameConsole = gameConsole;
        this.chest = chest;
        this.projection = projection;
        this.stock = stock;
        this.hotel=hotel;
        this.season=season;
        this.pension= pension;
        this.reservation=reservation;
    }

    public enum type{
        SingleRoom,
        DoubleRoom,
        JuniorSuite,
        Suite

    }

    public Room() {
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

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public int getPensionId() {
        return pensionId;
    }

    public void setPensionId(int pensionId) {
        this.pensionId = pensionId;
    }

    public Room.type getType() {
        return type;
    }

    public void setType(Room.type type) {
        this.type = type;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public void setBedNumber(int bedNumber) {
        this.bedNumber = bedNumber;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean isMinibar() {
        return minibar;
    }

    public void setMinibar(boolean minibar) {
        this.minibar = minibar;
    }

    public boolean isGameConsole() {
        return gameConsole;
    }

    public void setGameConsole(boolean gameConsole) {
        this.gameConsole = gameConsole;
    }

    public boolean isChest() {
        return chest;
    }

    public void setChest(boolean chest) {
        this.chest = chest;
    }

    public boolean isProjection() {
        return projection;
    }

    public void setProjection(boolean projection) {
        this.projection = projection;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
