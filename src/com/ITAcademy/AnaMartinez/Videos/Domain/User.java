package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.time.LocalDate;

public class User {
    private String name;
    private String surname;
    private String password;
    private LocalDate registerDate;

    public User(){}

    public User(String name, String surname, String password){
        this.name = name;
        this.surname = surname;
        this.password = password;
        registerDate = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getRegisterDate(){
        return registerDate;
    }
}
