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
        addVideoToController("myURL.com","my Video", "UserID");
        assertEquals(1, controller.getNumberOfVideos());
    }

    private UUID addVideoToController(String URL, String title, String userId, String... tags) {
        Video video = new Video(URL,title, userId);
        for(String s: tags){
            video.addTag(s);
        }
        controller.addVideo(video);
        return video.getUUID();
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToAddAVideoForAUserThatDoesNotExistThrowsAnException(){
        addVideoToController("myURL.com","my Video", "UserID");
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
        UUID videoId = addVideoToController("myURL.com","my Video", "UserID");
        boolean isTheTagListEmpty = controller.getNumberOfTagsFromVideo(videoId);
        assertTrue(isTheTagListEmpty);
        //the video has tags
        videoId = addVideoToController("myURL2.com","my Video2", "UserID", "Tag1", "This is Tag 2");
        isTheTagListEmpty = controller.getNumberOfTagsFromVideo(videoId);
        assertFalse(isTheTagListEmpty);
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToKnowIfAVideoHasTagsAndItDoesNotExistsThrowsAnException(){
        controller.getNumberOfTagsFromVideo(UUID.randomUUID());
    }

    @Test
    public void ICanGetTheTagsFromAVideo(){
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        UUID videoId = addVideoToController("myURL.com","my Video", "UserID", "Tag1", "this is tag 2", "taaaag 3");
        List<String> listOfTags = controller.getListOfTagsFromVideo(videoId);
        String[] tags= new String[]{"Tag1", "this is tag 2", "taaaag 3"};
        assertEquals(3,listOfTags.size());
        assertArrayEquals(tags, listOfTags.toArray(new String[0]));
        //retrieving the list from a video that has no tags
        videoId = addVideoToController("myURL2.com","my Video2", "UserID");
        listOfTags = controller.getListOfTagsFromVideo(videoId);
        assertEquals(0,listOfTags.size());
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToGettheTagsFromAVideoThatDoesNotExistsThrowsAnException(){
        controller.getListOfTagsFromVideo(UUID.randomUUID());
    }

    @Test
    public void addATagToAVideo(){
        addUserToController("UserID","UserName","UserSurname","UserPassword");
        UUID videoId = addVideoToController("myURL.com","my Video", "UserID", "Tag1", "this is tag 2", "taaaag 3");
        controller.addTagToVideo(videoId, "myTag");
        String[] tags= new String[]{"Tag1", "this is tag 2", "taaaag 3","myTag"};
        List<String> listOfTags = controller.getListOfTagsFromVideo(videoId);
        assertEquals(4,listOfTags.size());
        assertArrayEquals(tags, listOfTags.toArray(new String[0]));
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToAddATagToAVideoThatDoesNotExistsThrowsAnException(){
        controller.addTagToVideo(UUID.randomUUID(), "myTag");
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToAddAnEmptyTagToAVideoThrowsAnException(){
        controller.addTagToVideo(UUID.randomUUID(), "");
    }

    @Test (expected = InvalidParameterException.class)
    public void tryingToAddANullTagToAVideoThrowsAnException(){
        controller.addTagToVideo(UUID.randomUUID(), null);
    }

}