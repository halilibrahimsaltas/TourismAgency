package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Db {
    //Singleton Design Pattern
    private  static Db instance=null;
    private Connection connection= null;

    public Db() {
        try{
            Properties props = new Properties();
            FileInputStream in= new FileInputStream("C:\\Users\\TRON-V3\\IdeaProjects\\TourismAgency\\database.properties");
            props.load(in);
            in.close();
            String DB_URL = props.getProperty("db.url");
            String DB_USERNAME = props.getProperty("db.username");
            String DB_PASS = props.getProperty("db.password");
            this.connection=DriverManager.getConnection(DB_URL,DB_USERNAME,DB_PASS);

        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Connection getConnection(){
        return connection;
    }
    public  static  Connection getInstance(){
        try{
            if(instance==null || instance.getConnection().isClosed()){
                instance = new Db();
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return instance.getConnection();
    }

}