package fr.emse.spring.lemeilleurcoin.model;

import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String description;

    @Column(nullable = true)
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;

    @Column(nullable = false)
    @CreationTimestamp
    private Date date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private User user;

    public Offer() {
    }

    public Offer( String title,Double price , String description,Date date, OfferStatus offerStatus, Category category, User user) {
        this.title = title;
        this.date = date;
        this.price = price;
        this.description = description;
        this.offerStatus = offerStatus;
        this.category = category;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
