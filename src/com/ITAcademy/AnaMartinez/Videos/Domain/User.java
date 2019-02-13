package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.security.InvalidParameterException;
import java.util.Date;

public class User {

    private String name;
    private String surname;
    private String password;
    private Date registrationDate;

    public User(String userName, String userSurname, String userPassword) {
        setName(userName);
        setSurname(userSurname);
        setPassword(userPassword);
        this.registrationDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(isANullOrEmptyString(name)) throw new InvalidParameterException();
        this.name = name;
    }

    private boolean isANullOrEmptyString(String string) {
        return (string == null)||(string.equals(""));
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(isANullOrEmptyString(surname)) throw new InvalidParameterException();
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(isANullOrEmptyString(password)) throw new InvalidParameterException();
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

}
