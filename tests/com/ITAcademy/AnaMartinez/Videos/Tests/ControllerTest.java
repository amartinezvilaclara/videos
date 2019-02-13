package com.ITAcademy.AnaMartinez.Videos.Tests;

import com.ITAcademy.AnaMartinez.Videos.Controller.Controller;
import com.ITAcademy.AnaMartinez.Videos.Domain.Video;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.List;

import static org.junit.Assert.*;

public class ControllerTest {
//TODO add tags to videos
    private Controller controller;

    @Before
    public void setUp(){
        controller = new Controller();
    }

    @Test
    public void ICanAddANewUser(){
       boolean returnBoolean = controller.addUser("UserID","UserName","UserSurname","UserPassword");
       assertTrue(returnBoolean);
       assertEquals(1, controller.getNumberOfUsers());
    }

    @Test
    public void ICanGetTheNumberOfUsers(){
        assertEquals(0,controller.getNumberOfUsers());
        controller.addUser("UserID","UserName","UserSurname","UserPassword");
        assertEquals(1, controller.getNumberOfUsers());
    }

    @Test
    public void ICannotAddAUserWithTheSameUsername(){
        controller.addUser("UserID","UserName","UserSurname","UserPassword");
        boolean returnBoolean = controller.addUser("UserID","secondName","secondSurname","secondPassword");
        assertFalse(returnBoolean);
        assertEquals(1,controller.getNumberOfUsers());
    }

    @Test
    public void ICanAddAVideo(){
        controller.addUser("UserID","UserName","UserSurname","UserPassword");
        controller.addVideo("myURL.com","my Video", "UserID");
        assertEquals(1, controller.getNumberOfVideos());
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToAddAVideoForAUserThatDoesNotExistThrowsAnException(){
        controller.addVideo("myURL.com","my Video", "wrongUserId");
    }

    @Test
    public void ICanListAllTheVideosFromAUser(){
        controller.addUser("UserID","UserName","UserSurname","UserPassword");
        controller.addVideo("myURL.com","my Video", "UserID");
        controller.addVideo("myURL2.com","my Video2", "UserID");
        List<Video> videosFromUser = controller.getAllVideosFromUser("UserID");
        assertNotNull(videosFromUser);
        assertEquals(2,videosFromUser.size());
        videosFromUser.toString();
    }

}