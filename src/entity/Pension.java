package entity;

import core.ComboItem;

public class Pension {

    private int id;

    private Pension.type type;

    private int pensionHotelId;



    public Pension(int id, Pension.type type) {
        this.id = id;
        this.type = type;
    }

    public Pension() {
    }

    public enum type{
        UltraAllInclusive,
        AllInclusive,
        RoomBreakfast,
        FullOPension,
        HalfBoard,
        JustBed,
        ExcludingAlcoholFullCredit

    }
    public ComboItem getComboItem() {
        return new ComboItem(this.getId(),this.getType());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pension.type getType() {
        return type;
    }

    public void setType(Pension.type type) {
        this.type = type;
    }

    public int getPensionHotelId() {
        return pensionHotelId;
    }

    public void setPensionHotelId(int pensionHotelId) {
        this.pensionHotelId = pensionHotelId;
    }

}
