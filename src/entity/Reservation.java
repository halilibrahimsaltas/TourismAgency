package entity;

import java.time.LocalDate;

public class Reservation {

    //Fields

    private  int id;

    private int roomId;

    private String customerName;

    private  String customerCN;

    private  String customerAdress;

    private  String customerMail;

    private  String customerMpno;

    private  String  note;

    private LocalDate checkIn;

    private  LocalDate checkOut;

    private double totalPrice;

    private  int totalGuest;

    private Room room;
    // Constructor with parameters
    public Reservation(int id, String customerName, String customerCN, String customerAdress, String customerMail, String customerMpno, String note,LocalDate checkIn,LocalDate checkOut,double totalPrice,int totalGuest, Room room) {
        this.id = id;
        this.customerName = customerName;
        this.customerCN = customerCN;
        this.customerAdress = customerAdress;
        this.customerMail = customerMail;
        this.customerMpno = customerMpno;
        this.note = note;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
        this.totalPrice=totalPrice;
        this.totalGuest=totalGuest;
        this.room=room;
    }

    public Reservation() {
    }
    // Getters and setters for all fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCN() {
        return customerCN;
    }

    public void setCustomerCN(String customerCN) {
        this.customerCN = customerCN;
    }

    public String getCustomerAdress() {
        return customerAdress;
    }

    public void setCustomerAdress(String customerAdress) {
        this.customerAdress = customerAdress;
    }

    public String getCustomerMail() {
        return customerMail;
    }

    public void setCustomerMail(String customerMail) {
        this.customerMail = customerMail;
    }

    public String getCustomerMpno() {
        return customerMpno;
    }

    public void setCustomerMpno(String customerMpno) {
        this.customerMpno = customerMpno;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalGuest() {
        return totalGuest;
    }

    public void setTotalGuest(int totalGuest) {
        this.totalGuest = totalGuest;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

}
