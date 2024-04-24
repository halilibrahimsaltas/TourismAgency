package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class PensionManager {

    private PensionDao pensionDao;

    // Constructor initializing PensionDao
    public PensionManager() {
        this.pensionDao = new PensionDao();
    }

    // Method to save a pension
    public  boolean save(Pension pension){
        return this.pensionDao.save(pension);
    }

    // Method to retrieve all pensions

    public ArrayList<Pension> findAll() { return this.pensionDao.findAll();}

    // Method to convert a list of pensions into a list suitable for display in a table
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
    // Method to retrieve a pension by ID

    public Pension getById(int id) { return this.pensionDao.getById(id);}

    // Method to delete a pension by ID

    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }

        return  this.pensionDao.delete(id);
    }
    // Method to update a pension

    public  boolean update(Pension pension){
        if (this.getById(pension.getId())== null){
            Helper.showMsg("notFound");
        }
        return this.pensionDao.update(pension);
    }

}
