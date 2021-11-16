package fr.emse.spring.lemeilleurcoin.dto;

import fr.emse.spring.lemeilleurcoin.model.Category;
import fr.emse.spring.lemeilleurcoin.model.Product;
import fr.emse.spring.lemeilleurcoin.model.ProductStatus;

import java.util.Date;

public class ProductDto {
    private Long id;
    private String title;
    private Double price;
    private String description;
    private ProductStatus productStatus;
    private Category category;
    private Date date;
    private String userEmail;

    public ProductDto(){

    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.date = product.getDate();
        this.productStatus = product.getProductStatus();
        this.category = product.getCategory();
        this.userEmail = product.getUser().getEmail();
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

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
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
