package business;

import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager() {
        this.userDao = new UserDao();
    }

    public User findByLogin(String username, String password) {
        //
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<User> findAll(){
        return this.userDao.findAll();

    }

    public  ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user: this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++]=user.getId();
            rowObject[i++]=user.getUsername();
            rowObject[i++]=user.getPassword();
            rowObject[i++]=user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }
}
