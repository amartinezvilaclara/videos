package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.util.ArrayList;
import java.util.List;

public class Video {

    private String URL;
    private String title;
    private List<String> tags;

    public Video(String URL, String title) {
        this.URL = URL;
        this.title = title;
        this.tags = new ArrayList<>();
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getTags() {
        return (String[]) tags.toArray();
    }

    public void addTag(String tag){
        if(!tags.contains(tag)) tags.add(tag);
    }

    public void removeTag(String tag){
        tags.remove(tag);
    }

    public void removeAllTags(){
        tags = new ArrayList<>();
    }
}

