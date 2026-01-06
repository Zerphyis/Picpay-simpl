package dev.Zerphyis.picpay.domain.entities;

import java.math.BigDecimal;

public class Users {

    private Long id;
    private String fullname;
    private String document;
    private String email;
    private String password;
    private BigDecimal balance;
    private UsersType usersType;

    public Users(UsersType usersType, BigDecimal balance, String password, String email, String document, String fullname) {
        this.usersType = usersType;
        this.balance = balance;
        this.password = password;
        this.email = email;
        this.document = document;
        this.fullname = fullname;
    }

    public Users(){

    }

    public Long getId() {
        return id;
    }

    public UsersType getUserType() {
        return usersType;
    }

    public void setUserType(UsersType usersType) {
        this.usersType = usersType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
