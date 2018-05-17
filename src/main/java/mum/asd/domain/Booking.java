package mum.asd.domain;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.data.ldap.LdapDataAutoConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Booking")
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;
    private String bookingNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @OneToMany
    private List<Room> rooms = new ArrayList<Room>();
    private Boolean checkInStatus;
    private Boolean checkOutStatus;
    @OneToOne
    private Payment payment;
    
    public Booking(Date startDate, Date endDate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		
		// Generate booking number
		LocalDate lDate = LocalDate.now();
		this.bookingNumber = lDate.toString();
	}

	public String getBookingNumber() {
        return bookingNumber;
    }

    public void setBookingNumber(String bookingNumber) {
        this.bookingNumber = bookingNumber;
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

    public Boolean getCheckInStatus() {
        return checkInStatus;
    }

    public void setCheckInStatus(Boolean checkInStatus) {
        this.checkInStatus = checkInStatus;
    }

    public Boolean getCheckOutStatus() {
        return checkOutStatus;
    }

    public void setCheckOutStatus(Boolean checkOutStatus) {
        this.checkOutStatus = checkOutStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}