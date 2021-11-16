package fr.emse.spring.lemeilleurcoin.dto;

import fr.emse.spring.lemeilleurcoin.model.Category;
import fr.emse.spring.lemeilleurcoin.model.Offer;
import fr.emse.spring.lemeilleurcoin.model.OfferStatus;

import java.util.Date;

public class OfferDto {
    private Long id;
    private Double price;
    private String title;
    private String description;
    private OfferStatus offerStatus;
    private Date date;
    private Category category;
    private String userEmail;

    public OfferDto() {
    }

    public OfferDto(Offer offer){
        this.id = offer.getId();
        this.title = offer.getTitle();
        this.price = offer.getPrice();
        this.date = offer.getDate();
        this.description = offer.getDescription();
        this.offerStatus = offer.getOfferStatus();
        this.category = offer.getCategory();
        this.userEmail = offer.getUser().getEmail();
    }

    public Long getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
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

    public void setOfferStatus(OfferStatus productStatus) {
        this.offerStatus = productStatus;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
