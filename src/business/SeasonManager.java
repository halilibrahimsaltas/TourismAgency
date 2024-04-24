package business;

import core.Helper;


import dao.SeasonDao;
import entity.Hotel;
import entity.Season;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class SeasonManager {

    private final SeasonDao seasonDao;

    // Constructor initializing SeasonDao
    public SeasonManager() {
        this.seasonDao=new SeasonDao();
    }

    // Method to save a season
    public  boolean save(Season season){
        return this.seasonDao.save(season);
    }

    // Method to retrieve all seasons
    public ArrayList<Season> findAll() { return this.seasonDao.findAll();}

    // Method to convert a list of seasons into a list suitable for display in a table
    public ArrayList<Object[]> getForTable(int size,ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (Season obj: seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getStartDate().format(formatter);
            rowObject[i++] = obj.getFinishDate().format(formatter);
            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }

    // Method to retrieve a season by ID
    public  Season getById(int id) { return this.seasonDao.getById(id);}

    // Method to delete a season by ID
    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.seasonDao.delete(id);
    }

    // Method to update a season
    public  boolean update(Season season){
        if (this.getById(season.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.seasonDao.update(season);
    }


}
