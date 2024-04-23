package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;
import entity.Room;

import java.util.ArrayList;

public class ReservationManager {

    private ReservationDao reservationDao;

    public ReservationManager() {
        this.reservationDao = new ReservationDao();
    }
    //public boolean save(Reservation reservation) { return this.reservationDao.save(reservation);}

    public boolean save(Room room,Reservation reservation){
       return this.reservationDao.save(room,reservation);
    }

    public ArrayList<Reservation> findAll() { return this.reservationDao.findAll();}

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

    public  Reservation getById(int id) { return this.reservationDao.getById(id);}

    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.reservationDao.delete(id);
    }

    public  boolean update(Reservation reservation){
        if (this.getById(reservation.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.reservationDao.update(reservation);
    }


}
