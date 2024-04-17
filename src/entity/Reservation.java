package entity;

public class Reservation {

    private  int id;

    private int roomId;

    private String custumerName;

    private  String custumerCN;

    private  String custumerAdress;

    private  String custumerMail;

    private  String custumerMpno;

    private  String  note;

    public Reservation(int id, String custumerName, String custumerCN, String custumerAdress, String custumerMail, String custumerMpno, String note) {
        this.id = id;
        this.custumerName = custumerName;
        this.custumerCN = custumerCN;
        this.custumerAdress = custumerAdress;
        this.custumerMail = custumerMail;
        this.custumerMpno = custumerMpno;
        this.note = note;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getCustumerName() {
        return custumerName;
    }

    public void setCustumerName(String custumerName) {
        this.custumerName = custumerName;
    }

    public String getCustumerCN() {
        return custumerCN;
    }

    public void setCustumerCN(String custumerCN) {
        this.custumerCN = custumerCN;
    }

    public String getCustumerAdress() {
        return custumerAdress;
    }

    public void setCustumerAdress(String custumerAdress) {
        this.custumerAdress = custumerAdress;
    }

    public String getCustumerMail() {
        return custumerMail;
    }

    public void setCustumerMail(String custumerMail) {
        this.custumerMail = custumerMail;
    }

    public String getCustumerMpno() {
        return custumerMpno;
    }

    public void setCustumerMpno(String custumerMpno) {
        this.custumerMpno = custumerMpno;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
