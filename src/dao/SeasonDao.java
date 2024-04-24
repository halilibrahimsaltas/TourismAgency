package dao;

import java.sql.Connection;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import core.Db;
import entity.Hotel;
import entity.Season;

public class SeasonDao {

    // Connection to the database
    private final Connection con;

    private final HotelDao hotelDao= new HotelDao();
    // Constructor
    public SeasonDao() {
        this.con= Db.getInstance();
    }

    // Method to select hotels based on a custom query
    public  ArrayList<Season> selectByQuery(String query){
        ArrayList<Season> seasonss = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonss.add(this.match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasonss;
    }

    // Method to find all seasons
    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"season\" ORDER BY season_id ASC");
    }
    // Method to create an  object from a ResultSet
    public Season match(ResultSet rs) throws SQLException {
        // Set properties of the object based on the ResultSet
        Season season = new Season();
        season.setId(rs.getInt("season_id"));
        season.setHotelId(rs.getInt("season_hotel_id"));
        season.setStartDate(LocalDate.parse(rs.getString("season_start")));
        season.setFinishDate(LocalDate.parse(rs.getString("season_finish")));
        season.setHotel(this.hotelDao.getById(rs.getInt("season_hotel_id")));
        season.setSeasonName(rs.getString("season_name"));
        return season;
    }
    // Method to save a new season record
    public boolean save(Season season) {
        String query = "INSERT INTO public.\"season\" " +
                "(" +
                "season_hotel_id," +
                "season_start," +
                "season_finish," +
                "season_name"+
                ")" +
                " VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            pr.setString(4,season.getSeasonName());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // Method to update a record
    public boolean update(Season season) {
        String query = " UPDATE  public.\"season\" SET " +
                "season_hotel_id = ?, " +
                "season_start= ? ," +
                "season_finish = ? ," +
                "season_name= ? " +
                " WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            pr.setString(4,season.getSeasonName());
            pr.setInt(5,season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
    // Method to find a season by its ID
    public Season getById(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.\"season\" WHERE season_id = ?";
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

    // Method to delete a season record by its ID
    public boolean delete(int seasonId) {
        String query = "DELETE FROM public.\"season\" WHERE season_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, seasonId);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
    public ArrayList<Season> getAllByHotelId(int hotelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.\"season\" WHERE season_hotel_id = ?";

        try (PreparedStatement preparedStatement = this.con.prepareStatement(query)) {
            preparedStatement.setInt(1, hotelId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    seasons.add(match(resultSet));
                }
            }
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return seasons;
    }


}
