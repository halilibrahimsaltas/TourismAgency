package dao;

import core.Db;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    // Connection to the database
    private final Connection con;

    private final RoomDao roomDao= new RoomDao();

    public ReservationDao() {
        this.con= Db.getInstance();
    }// Constructor

    // Method to select hotels based on a custom query
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservations.add(this.match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Method to create an  object from a ResultSet
    public Reservation match(ResultSet rs) throws SQLException {
        // Set properties of the object based on the ResultSet
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("reservation_id"));
        reservation.setRoomId(rs.getInt("reservation_room_id"));
        reservation.setCustomerName(rs.getString("customer_name"));
        reservation.setCustomerCN(rs.getString("customer_citizen_id"));
        reservation.setCustomerAdress(rs.getString("customer_address"));
        reservation.setCustomerMail(rs.getString("customer_mail"));
        reservation.setCustomerMpno(rs.getString("customer_mpno"));
        reservation.setNote(rs.getString("reservation_note"));
        reservation.setCheckIn(LocalDate.parse(rs.getString("check_indate")));
        reservation.setCheckOut(LocalDate.parse(rs.getString("check_outdate")));
        reservation.setTotalPrice(rs.getDouble("total_price"));
        reservation.setTotalGuest(rs.getInt("guest_count"));
        reservation.setRoom(this.roomDao.getById(rs.getInt("reservation_room_id")));
        return reservation;
    }

    // Method to update a record
    public boolean update(Reservation reservation) {
        String query = " UPDATE  public.\"reservation\" SET " +
                "reservation_room_id = ?, " +
                "customer_name= ? ," +
                "customer_citizen_id = ? ," +
                "customer_address= ? ," +
                "customer_mail= ? ," +
                "customer_mpno= ? ," +
                "reservation_note= ? ," +
                "check_indate= ? ," +
                "check_outdate= ? ," +
                "total_price= ? ," +
                "guest_count= ? " +
                " WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setString(2, reservation.getCustomerName());
            pr.setString(3, reservation.getCustomerCN());
            pr.setString(4, reservation.getCustomerAdress());
            pr.setString(5,reservation.getCustomerMail());
            pr.setString(6, reservation.getCustomerMpno());
            pr.setString(7, reservation.getNote());
            pr.setDate(8, Date.valueOf(reservation.getCheckIn()));
            pr.setDate(9, Date.valueOf(reservation.getCheckOut()));
            pr.setDouble(10,reservation.getTotalPrice());
            pr.setInt(11, reservation.getTotalGuest());
            pr.setInt(12,reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
    // Method to save a new reservation record
    public boolean save(Room room,Reservation reservation) {
        String query = "INSERT INTO public.\"reservation\" " +
                "(" +
                "reservation_room_id," +
                "customer_name," +
                "customer_citizen_id," +
                "customer_address," +
                "customer_mail," +
                "customer_mpno," +
                "reservation_note," +
                "check_indate," +
                "check_outdate," +
                "total_price," +
                "guest_count" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getId());
            pr.setString(2, reservation.getCustomerName());
            pr.setString(3, reservation.getCustomerCN());
            pr.setString(4, reservation.getCustomerAdress());
            pr.setString(5, reservation.getCustomerMail());
            pr.setString(6, reservation.getCustomerMpno());
            pr.setString(7, reservation.getNote());
            pr.setDate(8, Date.valueOf(reservation.getCheckIn()));
            pr.setDate(9, Date.valueOf(reservation.getCheckOut()));
            pr.setDouble(10, reservation.getTotalPrice());
            pr.setInt(11, reservation.getTotalGuest());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to find all reservations
    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"reservation\" ORDER BY reservation_id ASC");
    }
    // Method to find a reservations by its ID
    public Reservation getById(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.\"reservation\" WHERE reservation_id = ?";
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
    // Method to delete a reservation record by its ID
    public boolean delete(int reservationId) {
        String query = "DELETE FROM public.\"reservation\" WHERE reservation_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservationId);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }

}
