package business;

import core.Helper;


import dao.SeasonDao;
import entity.Hotel;
import entity.Season;


import java.util.ArrayList;


public class SeasonManager {

    private final SeasonDao seasonDao;

    public SeasonManager() {
        this.seasonDao=new SeasonDao();
    }
    public  boolean save(Season season){
        return this.seasonDao.save(season);
    }

    public ArrayList<Season> findAll() { return this.seasonDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Season> seasons) {
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for (Season obj: seasons) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getStartDate().toString();
            rowObject[i++] = obj.getFinishDate().toString();
            seasonObjList.add(rowObject);
        }
        return seasonObjList;
    }

    public  ArrayList<Season> searchForTable(int seasonId ){
        String select = "SELECT * FROM public.\"season\" ";
        ArrayList<String> whereList = new ArrayList<>();

        if (seasonId!= 0) {
            whereList.add("season_id= "+ seasonId);
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()){
            query+= " WHERE "+ whereStr;
        }

        return  this.seasonDao.selectByQuery(query);
    }
    public  Season getById(int id) { return this.seasonDao.getById(id);}

    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.seasonDao.delete(id);
    }

    public  boolean update(Season season){
        if (this.getById(season.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.seasonDao.update(season);
    }


}
