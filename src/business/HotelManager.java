package business;
import core.Helper;

import dao.HotelDao;
import entity.Hotel;


import java.util.ArrayList;


public class HotelManager {

    private final HotelDao hotelDao;

    // Constructor initializing HotelDao
    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    // Method to save a hotel
    public  boolean save(Hotel hotel){
        return this.hotelDao.save(hotel);
    }

    // Method to retrieve all hotels
    public ArrayList<Hotel> findAll() { return this.hotelDao.findAll();}

    // Method to convert a list of hotels into a list suitable for display in a table
    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelObjList = new ArrayList<>();
        for (Hotel obj: hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getCity();
            rowObject[i++] = obj.getDistrict();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.getPension();
            rowObject[i++] = obj.isPark() ? "Yes" : "No";
            rowObject[i++] = obj.isWifi() ? "Yes" : "No";
            rowObject[i++] = obj.isPool() ? "Yes" : "No";
            rowObject[i++] = obj.isFitness() ? "Yes" : "No";
            rowObject[i++] = obj.isConcierge() ? "Yes" : "No";
            rowObject[i++] = obj.isSpa() ? "Yes" : "No";
            rowObject[i++] = obj.isService() ? "Yes" : "No";
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            hotelObjList.add(rowObject);
        }
        return hotelObjList;
    }

    // Method to retrieve a hotel by ID
    public  Hotel getById(int id) { return this.hotelDao.getById(id);}


    // Method to delete a hotel by ID
    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.hotelDao.delete(id);
    }

    // Method to update a hotel
    public  boolean update(Hotel hotel){
        if (this.getById(hotel.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.hotelDao.update(hotel);
    }


}
