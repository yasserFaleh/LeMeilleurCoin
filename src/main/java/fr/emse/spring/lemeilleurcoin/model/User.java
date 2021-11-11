package fr.emse.spring.lemeilleurcoin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class User {
    @Id
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable=false)
    private String phoneNum;

    @OneToMany(mappedBy = "user")
    Set<Offer> offers;

    @OneToMany(mappedBy = "user")
    Set<Product> products;

    @OneToMany(mappedBy = "viewer")
    Set<View> views;

    @OneToMany(mappedBy = "viewedUser")
    Set<View> ownViews;


    public User() {
    }

    public User(String email, String password, String fullName, String phoneNum) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
