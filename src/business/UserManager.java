package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    // Constructor initializing UserDao

    public UserManager() {
        this.userDao = new UserDao();
    }

    // Method to find a user by login credentials
    public User findByLogin(String username, String password) {

        return this.userDao.findByLogin(username, password);
    }
    // Method to retrieve all users
    public ArrayList<User> findAll(){
        return this.userDao.findAll();

    }
    // Method to convert a list of users into a list suitable for display in a table

    public  ArrayList<Object[]> getForTable(int size,ArrayList<User> userList){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (User user: userList){
            int i =0;
            Object[] rowObject = new Object[size];
            rowObject[i++]=user.getId();
            rowObject[i++]=user.getUsername();
            rowObject[i++]=user.getPassword();
            rowObject[i++]=user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }
    // Method to save a user

    public  boolean save (User user){
        if( user.getId() != 0){
            Helper.showMsg("error");
        }
        return this.userDao.save(user);
    }
    // Method to retrieve a user by ID

    public  User getById(int id){
        return this.userDao.getById(id);
    }
    // Method to update a user

    public  boolean update(User user){
        if (this.getById(user.getId()) == null){
            Helper.showMsg("notFound");
        }
        return this.userDao.update(user);
    }
    // Method to delete a user by ID

    public  boolean delete(int id){
        if(this.getById(id)== null){
            Helper.showMsg("notFound");
            return false;
        }


        return  this.userDao.delete(id);
    }
    // Method to search for users based on user ID and role

    public  ArrayList<User> searchForTable(int userId, User.Role role ){
        String select = "SELECT * FROM public.\"user\" ";
        ArrayList<String> whereList = new ArrayList<>();

        if (userId!= 0) {
            whereList.add("user_id= "+ userId);
        }
        if (role != null) {
            whereList.add("user_role ='"+ role.toString()+"'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (!whereStr.isEmpty()){
            query+= " WHERE "+ whereStr;
        }

        return  this.userDao.selectByQuery(query);
    }
    // Method to retrieve users by a list of user IDs
    public  ArrayList<User> getByListUserId(int userId){
        return this.userDao.getByListBrandId(userId);
    }
}
