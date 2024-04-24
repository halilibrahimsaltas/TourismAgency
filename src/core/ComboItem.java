package core;

import entity.Pension;
import entity.Season;

import java.security.cert.PolicyNode;
import java.time.LocalDate;

public class ComboItem {

    // Fields to hold key and value
    private  int key;
    private String value;
    private int key2;

    private Pension.type type;

    private ComboItem date;

    private String seasonName;

    // Constructor for key-value pairs
    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public ComboItem(int key,Pension.type type  ){
        this.key=key;
        this.type=type;
    }

    public ComboItem(int key ,ComboItem date){
        this.key=key;
        this.date=date;

    }

    public ComboItem(int key , int key2 ,String seasonName){
        this.key=key;
        this.key2=key2;
        this.seasonName=seasonName;
    }

    //Getter Setter
    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public  String toString(){
        return this.value;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }



}