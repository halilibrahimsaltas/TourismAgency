package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import view.Layout;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PensionDao extends Layout {
    // Connection to the database
    private final Connection con;

    private final HotelDao hotelDao= new HotelDao();

    public PensionDao() {
        this.con= Db.getInstance();
    }// Constructor

    // Method to select hotels based on a custom query
    public ArrayList<Pension> selectByQuery(String query){
        ArrayList<Pension> pensions = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pensions.add(this.match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }
    // Method to find all pensions
    public ArrayList<Pension> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"pension_type\" ORDER BY pension_id ASC");
    }

    // Method to create an  object from a ResultSet
    public Pension match(ResultSet rs) throws SQLException {
        // Set properties of the object based on the ResultSet
        Pension pension = new Pension();
        pension.setId(rs.getInt("pension_id"));
        pension.setPensionHotelId(rs.getInt("pension_hotel_id"));
        pension.setType(Pension.type.valueOf(rs.getString("pension_type")));
        pension.setHotel(this.hotelDao.getById(rs.getInt("pension_hotel_id")));
        return pension;
    }
    // Method to save a new pension record
    public boolean save(Pension pension) {
        String query = "INSERT INTO public.\"pension_type\" " +
                "(" +
                "pension_type," +
                "pension_hotel_id" +
                ")" +
                " VALUES (?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1,pension.getType().toString());
            pr.setInt(2, pension.getPensionHotelId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    // Method to update a record
    public boolean update(Pension pension) {
        String query = " UPDATE  public.\"pension_type\" SET " +
                "pension_type= ? ," +
                "pension_hotel_id = ? " +
                " WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,pension.getType().toString());
            pr.setInt(2,pension.getPensionHotelId());
            pr.setInt(3,pension.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
    // Method to find a pension by its ID
    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.\"pension_type\" WHERE pension_id = ?";
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
    // Method to delete a pension record by its ID
    public boolean delete(int pensionId) {
        String query = "DELETE FROM public.\"pension_type\" WHERE pension_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pensionId);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
    public ArrayList<Pension> getAllByHotelId(int hotelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.\"pension_type\" WHERE pension_hotel_id = ?";

        try (PreparedStatement preparedStatement = this.con.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    pensions.add(match(resultSet));
                }
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return pensions;
    }

}
