package business;
import core.Helper;

import dao.HotelDao;
import entity.Hotel;


import java.util.ArrayList;


public class HotelManager {

    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }
    public  boolean save(Hotel hotel){
        return this.hotelDao.save(hotel);
    }

    public ArrayList<Hotel> findAll() { return this.hotelDao.findAll();}

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
            rowObject[i++] = obj.isPark();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isService();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            hotelObjList.add(rowObject);
        }
        return hotelObjList;
    }
    public  ArrayList<Hotel> searchForTable(int hotelId ){
        String select = "SELECT * FROM public.\"hotel\" ";
        ArrayList<String> whereList = new ArrayList<>();

        if (hotelId!= 0) {
            whereList.add("hotel_id= "+ hotelId);
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()){
            query+= " WHERE "+ whereStr;
        }

        return  this.hotelDao.selectByQuery(query);
    }
    public  Hotel getById(int id) { return this.hotelDao.getById(id);}


    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.hotelDao.delete(id);
    }

    public  boolean update(Hotel hotel){
        if (this.getById(hotel.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.hotelDao.update(hotel);
    }


}
