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
    private final Connection con;

    private final HotelDao hotelDao= new HotelDao();

    public SeasonDao() {
        this.con= Db.getInstance();
    }
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
    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"season\" ORDER BY season_id ASC");
    }
    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setId(rs.getInt("season_id"));
        season.setHotelId(rs.getInt("season_hotel_id"));
        season.setStartDate(LocalDate.parse(rs.getString("season_start")));
        season.setFinishDate(LocalDate.parse(rs.getString("season_finish")));
        season.setHotel(this.hotelDao.getById(rs.getInt("season_hotel_id")));
        return season;
    }
    public boolean save(Season season) {
        String query = "INSERT INTO public.\"season\" " +
                "(" +
                "season_hotel_id," +
                "season_start," +
                "season_finish" +
                ")" +
                " VALUES (?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public boolean update(Season season) {
        String query = " UPDATE  public.\"season\" SET " +
                "season_hotel_id = ?, " +
                "season_start= ? ," +
                "season_finish = ? " +
                " WHERE season_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));
            pr.setInt(4,season.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }

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

}
