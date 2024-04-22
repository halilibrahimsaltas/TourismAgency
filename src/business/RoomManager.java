package business;

import core.Helper;
import dao.HotelDao;
import dao.RoomDao;
import dao.SeasonDao;
import entity.Hotel;
import entity.Room;
import entity.Pension;
import entity.Season;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {

    private RoomDao roomDao;

    private SeasonDao seasonDao;

    private HotelDao hotelDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
        this.seasonDao=new SeasonDao();
        this.hotelDao= new HotelDao();
    }
    public boolean save(Room room) { return this.roomDao.save(room);}

    public ArrayList<Room> findAll() { return this.roomDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Room> rooms) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj: rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getSeason().getComboItem();
            rowObject[i++] = obj.getPension().getType();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getBedNumber();
            rowObject[i++] = obj.getSize();
            rowObject[i++] = obj.isTv();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGameConsole();
            rowObject[i++] = obj.isChest();
            rowObject[i++] = obj.isProjection();
            rowObject[i++] = obj.getStock();
            roomObjList.add(rowObject);
        }
        return roomObjList;
    }
    public  ArrayList<Room> searchForTable(int roomId ){
        String select = "SELECT * FROM public.\"room\" ";
        ArrayList<String> whereList = new ArrayList<>();

        if (roomId!= 0) {
            whereList.add("room_id= "+ roomId);
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()){
            query+= " WHERE "+ whereStr;
        }

        return  this.roomDao.selectByQuery(query);
    }
    public  Room getById(int id) { return this.roomDao.getById(id);}

    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.roomDao.delete(id);
    }

    public  boolean update(Room room){
        if (this.getById(room.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.roomDao.update(room);
    }
    public  ArrayList<Room> searchForReservation( String search,String season_start,int guestCount){
        String query= "SELECT * FROM public.\"room\" as c LEFT JOIN public.\"hotel\" as m";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> seasonWhere = new ArrayList<>();
        ArrayList<String> isSmall= new ArrayList<>();

        joinWhere.add("c.room_hotel_id = m.hotel_id");

        season_start = LocalDate.parse(season_start).toString();

        if (search != null) {
            where.add("(m.hotel_city = '" + search + "' OR m.hotel_district = '" + search + "' OR m.hotel_name = '" + search + "')");
        }

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND " , joinWhere);

        if (!joinStr.isEmpty()){
            query += " ON " + joinStr;
        }

        if (!whereStr.isEmpty()){
            query+= " WHERE " + whereStr;
        }

        seasonWhere.add("'" + season_start + "' BETWEEN season_start AND season_finish");

        String seasonWhereStr = String.join(" AND ", seasonWhere);
        String seasonQuery = "SELECT * FROM public.\"season\" WHERE " + seasonWhereStr;

        ArrayList<Season> seasonsList = this.seasonDao.selectByQuery(seasonQuery);
        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);




        return  this.roomDao.selectByQuery(query);

    }


}
