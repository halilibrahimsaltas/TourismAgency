package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class PensionManager {

    private PensionDao pensionDao;

    public PensionManager() {
        this.pensionDao = new PensionDao();
    }
    public  boolean save(Pension pension){
        return this.pensionDao.save(pension);
    }

    public ArrayList<Pension> findAll() { return this.pensionDao.findAll();}

    public ArrayList<Object[]> getForTable(int size,ArrayList<Pension> pensions) {
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension obj: pensions) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getHotel().getName();
            pensionObjList.add(rowObject);
        }
        return pensionObjList;
    }

    public  ArrayList<Pension> searchForTable(int pensionId ){
        String select = "SELECT * FROM public.\"pension\" ";
        ArrayList<String> whereList = new ArrayList<>();

        if (pensionId!= 0) {
            whereList.add("pension_id= "+ pensionId);
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()){
            query+= " WHERE "+ whereStr;
        }

        return  this.pensionDao.selectByQuery(query);
    }
    public Pension getById(int id) { return this.pensionDao.getById(id);}

    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.pensionDao.delete(id);
    }

    public  boolean update(Pension pension){
        if (this.getById(pension.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.pensionDao.update(pension);
    }

}
