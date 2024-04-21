package dao;

import core.Db;
import entity.Pension;
import entity.Season;
import view.Layout;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class PensionDao extends Layout {
    private final Connection con;

    public PensionDao() {
        this.con= Db.getInstance();
    }
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
    public ArrayList<Pension> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"pension\" ORDER BY pension_id ASC");
    }
    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setId(rs.getInt("pension_id"));
        pension.setPensionHotelId(rs.getInt("pension_hotel_id"));
        pension.setType(Pension.type.valueOf(rs.getString("pension_type")));
        return pension;
    }
    public boolean save(Pension pension) {
        String query = "INSERT INTO public.\"pension\" " +
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
    public boolean update(Pension pension) {
        String query = " UPDATE  public.\"pension\" SET " +
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

    public Pension getById(int id) {
        Pension obj = null;
        String query = "SELECT * FROM public.\"pension\" WHERE pension_id = ?";
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
    public boolean delete(int pensionId) {
        String query = "DELETE FROM public.\"pension\" WHERE pension_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pensionId);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }

}
