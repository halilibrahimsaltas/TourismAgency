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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.PropertyResourceBundle;

public class RoomManager {

    private RoomDao roomDao;

    private SeasonDao seasonDao;


    // Constructor initializing RoomDao
    public RoomManager() {
        this.roomDao = new RoomDao();
        this.seasonDao= new SeasonDao();
    }
    // Method to save a room
    public boolean save(Room room) { return this.roomDao.save(room);}

    // Method to retrieve all rooms
    public ArrayList<Room> findAll() { return this.roomDao.findAll();}

    // Method to convert a list of rooms into a list suitable for display in a table
    public ArrayList<Object[]> getForTable(int size,ArrayList<Room> rooms) {
        ArrayList<Object[]> roomObjList = new ArrayList<>();
        for (Room obj: rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getSeason().getSeasonName();
            rowObject[i++] = obj.getPension().getType();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getBedNumber();
            rowObject[i++] = obj.getSize();
            rowObject[i++] = obj.isTv() ? "Yes" : "No";
            rowObject[i++] = obj.isMinibar() ? "Yes" : "No";
            rowObject[i++] = obj.isGameConsole() ? "Yes" : "No";
            rowObject[i++] = obj.isChest() ? "Yes" : "No";
            rowObject[i++] = obj.isProjection() ? "Yes" : "No";
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            roomObjList.add(rowObject);
        }
        return roomObjList;
    }

    // Method to retrieve a room by ID
    public  Room getById(int id) { return this.roomDao.getById(id);}
    // Method to delete a room by ID
    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.roomDao.delete(id);
    }
    // Method to update a room
    public  boolean update(Room room){
        if (this.getById(room.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.roomDao.update(room);
    }

    // Method to search for rooms based on various criteria
    public ArrayList<Room> searchForReservation(String search, String season_start, String guestCount) {
        String query = "SELECT * FROM public.\"room\" AS c LEFT JOIN public.\"hotel\" AS m";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> seasonOrWhere = new ArrayList<>();


        joinWhere.add("c.room_hotel_id = m.hotel_id");

        if (search != null && !search.isEmpty()) {
            where.add("(m.hotel_city = '" + search + "' OR m.hotel_district = '" + search + "' OR m.hotel_name = '" + search + "')");
        }

        if (guestCount != null && !guestCount.isEmpty()) {
            int guest = Integer.parseInt(guestCount);
            where.add("c.room_stock >= " + guest);
        }

        String whereStr = String.join(" AND ", where);
        String joinStr = String.join(" AND ", joinWhere);

        if (!joinStr.isEmpty()) {
            query += " ON " + joinStr;
        }

        if (!whereStr.isEmpty()) {
            query += " WHERE " + whereStr;
        }

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        if (season_start != null && !season_start.isEmpty()) {
            try {
            String seasonQuery = " Select * FROM public.\"season\" WHERE ('" + season_start + "' BETWEEN season_start AND season_start)";
            ArrayList<Season> seasonsList =this.seasonDao.selectByQuery(seasonQuery);
            ArrayList<Integer>  busyRoomId= new ArrayList<>();
            for (Season season : seasonsList){
                busyRoomId.add(season.getId());
            }
            searchedRoomList.removeIf(room -> !busyRoomId.contains(room.getSeasonId()));
            } catch(DateTimeParseException e) {
                // Handle parsing error
                e.printStackTrace();
            }
        }


        return searchedRoomList;
    }

}
