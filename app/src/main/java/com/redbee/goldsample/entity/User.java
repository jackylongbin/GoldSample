package com.redbee.goldsample.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class User extends LitePalSupport {

    @Column(unique = true,nullable = false)
    private String name;
    private String password;
    private String number;
    private String email;
    private String address;

    public User() {
        super();
    }

    public User(String name, String password, String number, String email, String address) {
        this.name = name;
        this.password = password;
        this.number = number;
        this.email = email;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", number='" + number + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
