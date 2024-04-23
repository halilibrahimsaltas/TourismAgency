package dao;

import core.Db;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;

    private final HotelDao hotelDao = new HotelDao();

    private final SeasonDao seasonDao= new SeasonDao();

    private final PensionDao pensionDao= new PensionDao();


    public RoomDao() {
        this.con= Db.getInstance();
    }
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                rooms.add(this.match(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }
    public Room match(ResultSet rs) throws SQLException {
        Room room= new Room();
        room.setId(rs.getInt("room_id"));
        room.setHotelId(rs.getInt("room_hotel_id"));
        room.setSeasonId(rs.getInt("room_season_id"));
        room.setPensionId(rs.getInt("room_pension_id"));
        room.setType(Room.type.valueOf(rs.getString("room_type")));
        room.setBedNumber(rs.getInt("room_bed_number"));
        room.setSize(rs.getInt("room_size"));
        room.setTv(rs.getBoolean("room_tv"));
        room.setMinibar(rs.getBoolean("room_minibar"));
        room.setGameConsole(rs.getBoolean("room_game_console"));
        room.setChest(rs.getBoolean("room_chest"));
        room.setProjection(rs.getBoolean("room_projection"));
        room.setStock(rs.getInt("room_stock"));
        room.setHotel(this.hotelDao.getById(rs.getInt("room_hotel_id")));
        room.setSeason(this.seasonDao.getById(rs.getInt("room_season_id")));
        room.setPension(this.pensionDao.getById(rs.getInt("room_pension_id")));
        return room;
    }
    public boolean update(Room room) {
        String query = " UPDATE  public.\"room\" SET " +
                "room_hotel_id = ?, " +
                "room_season_id= ? ," +
                "room_pension_id = ? ," +
                "room_type= ? ," +
                "room_bed_number= ? ," +
                "room_size= ? ," +
                "room_tv= ? ," +
                "room_minibar= ? ," +
                "room_game_console= ? ," +
                "room_chest= ? ," +
                "room_projection= ? ," +
                "room_stock= ? " +
                " WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getSeasonId());
            pr.setInt(3, room.getPensionId());
            pr.setString(4, room.getType().toString());
            pr.setInt(5,room.getBedNumber() );
            pr.setInt(6, room.getSize());
            pr.setBoolean(7, room.isTv());
            pr.setBoolean(8, room.isMinibar());
            pr.setBoolean(9, room.isGameConsole());
            pr.setBoolean(10, room.isChest());
            pr.setBoolean(11, room.isProjection());
            pr.setInt(12, room.getStock());
            pr.setInt(13,room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }
    public boolean save(Room room) {
        String query = "INSERT INTO public.\"room\" " +
                "(" +
                "room_hotel_id," +
                "room_season_id," +
                "room_pension_id," +
                "room_type," +
                "room_bed_number," +
                "room_size," +
                "room_tv," +
                "room_minibar," +
                "room_game_console," +
                "room_chest," +
                "room_projection," +
                "room_stock" +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getSeasonId());
            pr.setInt(3, room.getPensionId());
            pr.setString(4, room.getType().toString());
            pr.setInt(5, room.getBedNumber());
            pr.setInt(6, room.getSize());
            pr.setBoolean(7, room.isTv());
            pr.setBoolean(8, room.isMinibar());
            pr.setBoolean(9, room.isGameConsole());
            pr.setBoolean(10, room.isChest());
            pr.setBoolean(11, room.isProjection());
            pr.setInt(12, room.getStock());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.\"room\" ORDER BY room_id ASC");
    }
    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.\"room\" WHERE room_id = ?";
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
    public boolean delete(int roomId) {
        String query = "DELETE FROM public.\"room\" WHERE room_id = ?";

        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, roomId);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return true;
    }

}
