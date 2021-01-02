package com.rotimijohnson.moderndashboardscreen.util;

public class UserHelper {
    String name,email, password, phoneNumber;

    public UserHelper(String name,String email, String password, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public UserHelper() {
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
