package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.security.InvalidParameterException;
import java.util.Date;

public class User {

    private String uniqueUserId;
    private String name;
    private String surname;
    private String password;
    private Date registrationDate;

    private User(String userId){
        setUniqueUserId(userId);
        this.name = null;
        this.surname = null;
        this.password = null;
        this.registrationDate = null;
    }

    public User(String uniqueUserId, String name, String surname, String password) {
        setUniqueUserId(uniqueUserId);
        setName(name);
        setSurname(surname);
        setPassword(password);
        this.registrationDate = new Date();
    }

    private void setUniqueUserId(String uniqueUserId) throws InvalidParameterException{
        if(isANullOrEmptyString(uniqueUserId)) throw new InvalidParameterException();
        this.uniqueUserId = uniqueUserId;
    }

    public String getUniqueUserId() {
        return uniqueUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidParameterException{
        if(isANullOrEmptyString(name)) throw new InvalidParameterException();
        this.name = name;
    }

    private boolean isANullOrEmptyString(String string) {
        return (string == null)||(string.equals(""));
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws InvalidParameterException{
        if(isANullOrEmptyString(surname)) throw new InvalidParameterException();
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws InvalidParameterException{
        if(isANullOrEmptyString(password)) throw new InvalidParameterException();
        this.password = password;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        else {
            User user = (User) o;
            return this.uniqueUserId.equals(user.getUniqueUserId());
        }
    }

    @Override
    public int hashCode() {
        return this.uniqueUserId.hashCode();
    }

    public static User createDummy(String userId) {
        return new User(userId);
    }
}
