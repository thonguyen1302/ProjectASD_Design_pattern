package mum.asd.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
public class Booking {
    @Id
    @GeneratedValue
    private int id;
    private int number;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany
    private List<Room> rooms;
    private boolean checkinStatus;
    private boolean checkoutStatus;

    public Booking() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public boolean isCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(boolean checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public boolean isCheckoutStatus() {
        return checkoutStatus;
    }

    public void setCheckoutStatus(boolean checkoutStatus) {
        this.checkoutStatus = checkoutStatus;
    }
}
