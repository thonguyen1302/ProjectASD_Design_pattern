package mum.asd.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Card {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private String cardNumber;
    private String pinNumber;
    private String holdername;
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }
    
    public String getHolderName() {
		return holdername;
	}

	public void setHoldername(String holderName) {
		holdername = holderName;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public void transaction(){

    }
    public void validation(){

    }
}
