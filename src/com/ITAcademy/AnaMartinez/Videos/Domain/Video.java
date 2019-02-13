package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Video {

    private String URL;
    private String title;
    private List<String> tags;
    private String owner;

    public Video(String URL, String title, String owner) {
        setURL(URL);
        setTitle(title);
        setOwner(owner);
        this.tags = new ArrayList<>();
    }

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

    public String getOwner() {
        return owner;
    }

    private void setOwner( String owner) {
        if(isANullOrEmptyString(owner)) throw new InvalidParameterException();
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Video{" +
                "URL='" + URL + '\'' +
                ", title='" + title + '\'' +
                ", tags=" + tags +
                ", owner='" + owner + '\'' +
                '}';
    }
}

