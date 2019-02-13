package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Video {

    private UUID uuid;
    private String URL;
    private String title;
    private List<String> tags;

    public Video(String URL, String title) {
        uuid = UUID.randomUUID();
        setURL(URL);
        setTitle(title);
        this.tags = new ArrayList<>();
    }

    public UUID getUuid() { return uuid; }
    public String getURL() {
        return URL;
    }
    public String getTitle() {
        return title;
    }

    public void setURL(String URL) {
        if(isANullOrEmptyString(URL)) throw new InvalidParameterException();
        this.URL = URL;
    }

    public void setTitle(String title) {
        if(title == null) throw new InvalidParameterException();
        this.title = title;
    }

    public boolean isTagListEmpty() {
        return tags.isEmpty();
    }

    public void addTag(String myTag) {
        if(isANullOrEmptyString(myTag)) throw new InvalidParameterException();
        if(!this.tags.contains(myTag)) this.tags.add(myTag);
    }

    private boolean isANullOrEmptyString(String string) {
        return (string == null) || (string.equals(""));
    }

    public String[] getTags() {
        if (isTagListEmpty()) throw new NullPointerException();
        return this.tags.toArray(new String[1]);
    }

    public void removeTag(String myTag){
        if(myTag == null) throw new InvalidParameterException();
        this.tags.remove(myTag);
    }

    public void removeAllTags() {
        this.tags = new ArrayList<>();
    }
}

