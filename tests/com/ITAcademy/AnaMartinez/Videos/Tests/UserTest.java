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
        this.user = new User("UserID","UserName","UserSurname","UserPassword");
    }

    @Test
    public void theUserHasUsernameNameSurnamePasswordAndRegisterDate(){
        String userId = user.getUniqueUserId();
        assertEquals("UserID",userId);
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

    @Test (expected = InvalidParameterException.class)
    public void aNullStringAsUserIDProvokesAnException(){
        user= new User(null,"UserName","UserSurname","UserPassword");
    }

    @Test (expected = InvalidParameterException.class)
    public void anEmptyStringAsUserIDProvokesAnException(){
        user= new User("","UserName","UserSurname","UserPassword");
    }

    @Test
    public void twoUsersAreTheSameIfTheyHaveTheSameUserId(){
        User secondUser = new User("UserID","secondName","secondSurname","secondPassword");
        assertTrue(this.user.equals(secondUser));
    }

    @Test
    public void twoUsersAreDifferentIfTheyHaveADifferentUserId(){
        User secondUser = new User("UserID2","UserName","UserSurname","UserPassword");
        assertFalse(this.user.equals(secondUser));
    }

    @Test
    public void aUserIsEqualToItself(){
        assertTrue(this.user.equals(this.user));
    }

    @Test
    public void aUserIsNotEqualToANonUserObject(){
        assertFalse(this.user.equals(new Object()));
    }

    @Test
    public void usersWithTheSameUserIdHaveTheSameHashCode(){
        User secondUser =  new User("UserID","secondName","secondSurname","secondPassword");
        assertEquals(this.user.hashCode(),secondUser.hashCode());
    }

    @Test
    public void ICanCreateADummyUserWithOnlyTheUserID(){
        User user = User.createDummy("userId");
        assertNotNull(user);
    }
}