package entity;

public class Pension {

    private int id;

    private String type;

    private int pensionHotelId;

    private  int stock;

    public Pension(int id, String type, int stock) {
        this.id = id;
        this.type = type;
        this.stock = stock;
    }

    public Pension() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPensionHotelId() {
        return pensionHotelId;
    }

    public void setPensionHotelId(int pensionHotelId) {
        this.pensionHotelId = pensionHotelId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
