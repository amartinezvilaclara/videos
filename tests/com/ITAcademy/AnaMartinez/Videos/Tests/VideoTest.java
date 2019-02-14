package com.ITAcademy.AnaMartinez.Videos.Tests;

import com.ITAcademy.AnaMartinez.Videos.Domain.Video;
import org.junit.Before;
import org.junit.Test;

import java.security.InvalidParameterException;

import static org.junit.Assert.*;

public class VideoTest {

    private Video video;

    @Before
    public void setUp() {
        this.video = new Video("myURL.com","my Video", "userId");
    }

    @Test
    public void theVideoHasUrlAndTitleAndUUID(){
        assertEquals("myURL.com", video.getURL());
        assertEquals("my Video", video.getTitle());
        assertNotNull(video.getUUID());
    }

    @Test
    public void theVideoIsCreatedWithAnEmptyTagList(){
        boolean isEmpty = video.isTagListEmpty();
        assertTrue(isEmpty);
    }

    @Test
    public void ICanAddOneTagToTheList(){
       video.addTag("myTag");
       boolean isEmpty = video.isTagListEmpty();
       assertFalse(isEmpty);
    }

    @Test
    public void ICanGetTheListOfTags(){
        video.addTag("myTag");
        String[] listOfTags = video.getTags();
        assertEquals("myTag",listOfTags[0]);
    }

    @Test
    public void ICanAddTwoTagsToTheList(){
        video.addTag("myTag");
        video.addTag("myTag2");
        String[] listOfTags = video.getTags();
        assertEquals(2,listOfTags.length);
    }

    @Test (expected = NullPointerException.class)
    public void TryingToGetAnEmptyListOfTagsThrowsAnException(){
        video.getTags();
    }

    @Test
    public void theListOfTagsContainsNoRepeatedTags(){
        video.addTag("myTag");
        video.addTag("myTag");
        String[] listOfTags = video.getTags();
        assertEquals(1,listOfTags.length);
    }

    @Test
    public void ICanRemoveATagFromTheList(){
        video.addTag("myTag");
        assertFalse(video.isTagListEmpty());
        video.removeTag("myTag");
        assertTrue(video.isTagListEmpty());

    }

    @Test
    public void ICanRemoveAllTags(){
        video.addTag("myTag");
        video.addTag("myTag2");
        String[] listOfTags = video.getTags();
        assertEquals(2,listOfTags.length);
        video.removeAllTags();
        assertTrue(video.isTagListEmpty());
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingANullStringAsURLProvokesAnException(){
        video.setURL(null);
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingAnEmptyStringAsURLProvokesAnException(){
        video.setURL("");
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingANullStringAsTitleProvokesAnException(){
        video.setTitle(null);
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingANullStringAsTagProvokesAnException(){
        video.addTag(null);
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingAnEmptyStringAsTagProvokesAnException(){
        video.addTag("");
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingANullStringAsTagToRemoveProvokesAnException() {
        video.addTag(null);
    }

    @Test
    public void theVideoBelongsToAnOwner(){
        assertEquals("userId", video.getOwner());
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingAnEmptyStringAsOwnerProvokesAnException(){
       this.video = new Video("whatever","my Video", "");
    }

    @Test (expected = InvalidParameterException.class)
    public void PassingANullStringAsOwnerProvokesAnException(){
        this.video = new Video("whatever","my Video", null);
    }
}
