package com.ITAcademy.AnaMartinez.Videos.Tests;

import com.ITAcademy.AnaMartinez.Videos.Controller.Controller;
import com.ITAcademy.AnaMartinez.Videos.Domain.User;
import com.ITAcademy.AnaMartinez.Videos.Domain.Video;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.UUID;

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
        boolean returnBoolean = addUserToController("UserID","UserName","UserSurname","UserPassword");
        assertTrue(returnBoolean);
        assertEquals(1, controller.getNumberOfUsers());
    }

    private boolean addUserToController(String userId, String name, String surname, String password) {
        User user = new User(userId,name,surname,password);
        return controller.addUser(user);
    }

    @Test
    public void ICanGetTheNumberOfUsers(){
        assertEquals(0,controller.getNumberOfUsers());
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        assertEquals(1, controller.getNumberOfUsers());
    }

    @Test
    public void ICannotAddAUserWithTheSameUsername(){
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        boolean returnBoolean = addUserToController("UserID","UserName","UserSurname","UserPassword");
        assertFalse(returnBoolean);
        assertEquals(1,controller.getNumberOfUsers());
    }

    @Test
    public void ICanAddAVideoFromAUserThatExists(){
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        Video video = new Video("myURL.com","my Video", "UserID");
        controller.addVideo(video);
        assertEquals(1, controller.getNumberOfVideos());
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToAddAVideoForAUserThatDoesNotExistThrowsAnException(){
        Video video = new Video("myURL.com","my Video", "userId");
        controller.addVideo(video);
    }

    @Test
    public void ICanListAllTheVideosFromAUserThatExist(){
        //the user has no videos
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        List<Video> videosFromUser = controller.getAllVideosFromUser("UserID");
        assertNotNull(videosFromUser);
        assertEquals(0,videosFromUser.size());
        //the user has two videos
        Video[] videos = new Video[2];
        videos[0] = new Video("myURL.com","my Video", "UserID");
        videos[1] = new Video("myURL2.com","my Video2", "UserID");
        controller.addVideo(videos[0]);
        controller.addVideo(videos[1]);
        videosFromUser = controller.getAllVideosFromUser("UserID");
        assertNotNull(videosFromUser);
        assertEquals(2,videosFromUser.size());
        assertArrayEquals(videosFromUser.toArray(new Video [2]), videos);
    }

    @Test (expected = InvalidParameterException.class)
    public void listingTheVideosFromAUserThatDoesNotExistThrowsAnException(){
        controller.getAllVideosFromUser("UserId");
    }

    @Test
    public void ICanKnowIfAVideoHasTags(){
        //the video has no tags
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        Video video = new Video("myURL.com","my Video", "UserID");
        controller.addVideo(video);
        boolean isTheTagListEmpty = controller.getNumberOfTagsFromVideo(video.getUUID());
        assertTrue(isTheTagListEmpty);
        //the video has tags
        video = new Video("myURL2.com","my Video2", "UserID");
        video.addTag("Tag1");
        video.addTag("This is tag 2");
        controller.addVideo(video);
        isTheTagListEmpty = controller.getNumberOfTagsFromVideo(video.getUUID());
        assertFalse(isTheTagListEmpty);
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToKnowIfAVideoHasTagsAndItDoesNotExistsThrowsAnException(){
        controller.getNumberOfTagsFromVideo(UUID.randomUUID());
    }


   /* @Test
    public void addATagToAVideo(){
        User user = new User("UserID","UserName","UserSurname","UserPassword");
        controller.addUserToController(user);
        Video video = new Video("myURL.com","my Video", "UserID");
        controller.addVideo(video);
        controller.addTagToVideo(video.getUUID(), "myTag");

    }*/
}