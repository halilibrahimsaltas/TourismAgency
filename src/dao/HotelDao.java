package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class HotelDao {

    // Connection to the database
    private final Connection con;

    public HotelDao() {// Constructor

        this.con= Db.getInstance();
    }
    // Method to select hotels based on a custom query
    public  ArrayList<Hotel> selectByQuery(String query){
        ArrayList<Hotel> hotels = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                hotels.add(this.match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotels;
    }
    // Method to create an  object from a ResultSet
    public Hotel match(ResultSet rs) throws SQLException {
        // Set properties of the hotel object based on the ResultSet
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("hotel_id"));
        hotel.setName(rs.getString("hotel_name"));
        hotel.setCity(rs.getString("hotel_city"));
        hotel.setDistrict(rs.getString("hotel_district"));
        hotel.setAddress(rs.getString("hotel_address"));
        hotel.setMail(rs.getString("hotel_mail"));
        hotel.setPhone(rs.getString("hotel_phone"));
        hotel.setStar(rs.getInt("hotel_star"));
        hotel.setPension(rs.getString("hotel_pension_type"));
        hotel.setPark(rs.getBoolean("hotel_park"));
        hotel.setWifi(rs.getBoolean("hotel_wifi"));
        hotel.setPool(rs.getBoolean("hotel_pool"));
        hotel.setFitness(rs.getBoolean("hotel_fitness"));
        hotel.setConcierge(rs.getBoolean("hotel_concierge"));
        hotel.setSpa(rs.getBoolean("hotel_spa"));
        hotel.setService(rs.getBoolean("hotel_room_service"));
        return hotel;
    }

    // Method to update a hotel record
    public boolean update(Hotel hotel) {
        String query = " UPDATE  public.\"hotel\" SET " +
                "hotel_name = ?, " +
                "hotel_city= ? ," +
                "hotel_district = ? ," +
                "hotel_address= ? ," +
                "hotel_mail= ? ," +
                "hotel_phone= ? ," +
                "hotel_star= ? ," +
                "hotel_pension_type= ? ," +
                "hotel_park= ? ," +
                "hotel_wifi= ? ," +
                "hotel_pool= ? ," +
                "hotel_fitness= ? ," +
                "hotel_concierge= ? ," +
                "hotel_spa= ? ," +
                "hotel_room_service= ? " +
                " WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getCity());
            pr.setString(3, hotel.getDistrict());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getMail());
            pr.setString(6, hotel.getPhone());
            pr.setInt(7, hotel.getStar());
            pr.setString(8, hotel.getPension());
            pr.setBoolean(9, hotel.isPark());
            pr.setBoolean(10, hotel.isWifi());
            pr.setBoolean(11, hotel.isPool());
            pr.setBoolean(12, hotel.isFitness());
            pr.setBoolean(13, hotel.isConcierge());
            pr.setBoolean(14, hotel.isSpa());
            pr.setBoolean(15, hotel.isService());
            pr.setInt(16,hotel.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }

    // Method to find all hotels

    public ArrayList<Hotel> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"hotel\" ORDER BY hotel_id ASC");
    }
    // Method to find a hotel by its ID
    public Hotel getById(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.\"hotel\" WHERE hotel_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return obj;
    }

    // Method to save a new hotel record
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.\"hotel\" " +
                "(" +
                "hotel_name," +
                "hotel_city," +
                "hotel_district," +
                "hotel_address," +
                "hotel_mail," +
                "hotel_phone," +
                "hotel_star," +
                "hotel_pension_type," +
                "hotel_park," +
                "hotel_wifi," +
                "hotel_pool," +
                "hotel_fitness," +
                "hotel_concierge," +
                "hotel_spa," +
                "hotel_room_service" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getCity());
            pr.setString(3, hotel.getDistrict());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getMail());
            pr.setString(6, hotel.getPhone());
            pr.setInt(7, hotel.getStar());
            pr.setString(8, hotel.getPension());
            pr.setBoolean(9, hotel.isPark());
            pr.setBoolean(10, hotel.isWifi());
            pr.setBoolean(11, hotel.isPool());
            pr.setBoolean(12, hotel.isFitness());
            pr.setBoolean(13, hotel.isConcierge());
            pr.setBoolean(14, hotel.isSpa());
            pr.setBoolean(15, hotel.isService());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to delete a delete record by its ID
    public boolean delete(int hotelId) {
        String query = "DELETE FROM public.\"hotel\" WHERE hotel_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, hotelId);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
}
