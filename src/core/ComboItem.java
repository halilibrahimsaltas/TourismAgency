package core;

import entity.Pension;

import java.security.cert.PolicyNode;
import java.time.LocalDate;

public class ComboItem {

    // Fields to hold key and value
    private  int key;
    private String value;

    private Pension.type type;

    private ComboItem date;

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
}