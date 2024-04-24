package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;

import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;

    private RoomManager roomManager;

    // Constructor initializing ReservationDao
    public ReservationManager() {
        this.reservationDao = new ReservationDao();
        this.roomManager= new RoomManager();


    }
    //public boolean save(Reservation reservation) { return this.reservationDao.save(reservation);}

    // Method to save a reservation along with its associated room
    public boolean save(Room room,Reservation reservation){
       return this.reservationDao.save(room,reservation);
    }

    // Method to retrieve all reservations
    public ArrayList<Reservation> findAll() { return this.reservationDao.findAll();}

    // Method to convert a list of reservations into a list suitable for display in a table
    public ArrayList<Object[]> getForTable(int size,ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Reservation obj: reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoom().getHotel().getName();
            rowObject[i++] = obj.getCustomerName();
            rowObject[i++] = obj.getCustomerCN();
            rowObject[i++] = obj.getCustomerAdress();
            rowObject[i++] = obj.getCustomerMail();
            rowObject[i++] = obj.getCustomerMpno();
            rowObject[i++] = obj.getNote();
            rowObject[i++] = obj.getCheckIn().toString();
            rowObject[i++] = obj.getCheckOut().toString();
            rowObject[i++] = obj.getTotalPrice();
            rowObject[i++] = obj.getTotalGuest();
            reservationObjList.add(rowObject);
        }
        return reservationObjList;
    }
    // Method to convert a list of reservations into a list suitable for display in a room table
    public ArrayList<Object[]> getForRoomTable(int size,ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Reservation obj: reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getRoom().getId();
            rowObject[i++] = obj.getRoom().getHotel().getName();
            rowObject[i++] = obj.getRoom().getHotel().getCity();
            rowObject[i++] = obj.getRoom().getSeason().getComboItem();
            rowObject[i++] = obj.getRoom().getPension().getType().toString();
            rowObject[i++] = obj.getRoom().getHotel().getAdultPrice();
            rowObject[i++] = obj.getRoom().getHotel().getChildPrice();
            rowObject[i++] = obj.getRoom().getStock();
            reservationObjList.add(rowObject);
        }
        return reservationObjList;
    }
    // Method to convert a list of reservations into a list suitable for display in a room table
    public ArrayList<Object[]> getForRoomTableS(int size,ArrayList<Room> rooms) {
        ArrayList<Object[]> reservationObjList = new ArrayList<>();
        for (Room obj: rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getHotel().getCity();
            rowObject[i++] = obj.getSeason().getComboItem();
            rowObject[i++] = obj.getPension().getType().toString();
            rowObject[i++] = obj.getHotel().getAdultPrice();
            rowObject[i++] = obj.getHotel().getChildPrice();
            rowObject[i++] = obj.getStock();
            reservationObjList.add(rowObject);
        }
        return reservationObjList;
    }

    // Method to retrieve a reservation by its ID
    public  Reservation getById(int id) { return this.reservationDao.getById(id);}

    // Method to delete a reservation by its ID
    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.reservationDao.delete(id);
    }

    // Method to update a reservation
    public  boolean update(Reservation reservation){
        if (this.getById(reservation.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.reservationDao.update(reservation);
    }


}
