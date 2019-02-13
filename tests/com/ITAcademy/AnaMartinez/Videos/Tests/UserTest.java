package com.ITAcademy.AnaMartinez.Videos.Tests;

import com.ITAcademy.AnaMartinez.Videos.Domain.User;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.Date;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp(){
        this.user = new User("UserName","UserSurname","UserPassword");
    }

    @Test
    public void theUserHasNameSurnamePasswordAndRegisterDate(){
        String name = user.getName();
        assertEquals("UserName", name);
        String surname = user.getSurname();
        assertEquals("UserSurname", surname);
        String password = user.getPassword();
        assertEquals("UserPassword", password);
        Date date = user.getRegistrationDate();
        System.out.println(date.toString());
        assertNotNull(date);
    }

    @Test (expected = InvalidParameterException.class)
    public void aNullStringAsUserNameProvokesAnException(){
        user.setName(null);
    }

    @Test (expected = InvalidParameterException.class)
    public void anEmptyStringAsUserNameProvokesAnException(){
        user.setName("");
    }

    @Test (expected = InvalidParameterException.class)
    public void aNullStringAsUserSurnameProvokesAnException(){
        user.setSurname(null);
    }

    @Test (expected = InvalidParameterException.class)
    public void anEmptyStringAsUserSurnameProvokesAnException(){
        user.setSurname("");
    }
    @Test (expected = InvalidParameterException.class)
    public void aNullStringAsPasswordProvokesAnException(){
        user.setPassword(null);
    }

    @Test (expected = InvalidParameterException.class)
    public void anEmptyStringAsPasswordProvokesAnException(){
        user.setPassword("");
    }
}