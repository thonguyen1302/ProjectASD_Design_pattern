package mum.asd.domain;

import javax.persistence.*;

@Entity
@Table(name = "Room")
public class Room {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String bedType;
    private int numberAdult;
    private int numberChildren;
    private float tax;
    private String status;
    private int roomNumber;
    private float price;
    @Enumerated
    private RoomType roomType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }

    public int getNumberAdult() {
        return numberAdult;
    }

    public void setNumberAdult(int numberAdult) {
        this.numberAdult = numberAdult;
    }

    public int getNumberChildren() {
        return numberChildren;
    }

    public void setNumberChildren(int numberChildren) {
        this.numberChildren = numberChildren;
    }

    public float getTax() {
        return tax;
    }

    public void setTax(float tax) {
        this.tax = tax;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
