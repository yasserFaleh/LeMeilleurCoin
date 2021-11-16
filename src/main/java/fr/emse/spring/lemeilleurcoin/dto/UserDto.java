package fr.emse.spring.lemeilleurcoin.dto;

import fr.emse.spring.lemeilleurcoin.model.User;

public class UserDto {
    private String email;
    private String password;
    private String fullName;
    private String phoneNum;

    public UserDto() {
    }
    public UserDto(User user) {
        this.email = user.getEmail();
        this.fullName = user.getFullName();
        this.password = user.getPassword();
        this.phoneNum = user.getPhoneNum();
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
