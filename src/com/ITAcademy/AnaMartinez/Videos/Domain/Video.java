package com.ITAcademy.AnaMartinez.Videos.Domain;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Video {

    private String URL;
    private String title;
    private List<String> tags;
    private String owner;
    private UUID uuid;

    public Video(String URL, String title, String owner) throws InvalidParameterException{
        setURL(URL);
        setTitle(title);
        setOwner(owner);
        this.tags = new ArrayList<>();
        this.uuid = UUID.randomUUID();
    }

    public String getURL() {
        return URL;
    }
    public String getTitle() {
        return title;
    }
    public UUID getUUID() { return uuid; }
    public String getOwner() {
        return owner;
    }

    public void setURL(String URL) throws InvalidParameterException{
        if(isANullOrEmptyString(URL)) throw new InvalidParameterException();
        this.URL = URL;
    }

    public void setTitle(String title) throws InvalidParameterException {
        if(title == null) throw new InvalidParameterException();
        this.title = title;
    }

    private void setOwner( String owner) throws InvalidParameterException{
        if(isANullOrEmptyString(owner)) throw new InvalidParameterException();
        this.owner = owner;
    }

    public boolean isTagListEmpty() {
        return tags.isEmpty();
    }

    public void addTag(String myTag) throws InvalidParameterException {
        if(isANullOrEmptyString(myTag)) throw new InvalidParameterException();
        if(!this.tags.contains(myTag)) this.tags.add(myTag);
    }

    private boolean isANullOrEmptyString(String string) {
        return (string == null) || (string.equals(""));
    }

    //If there are not tags, a exception is thrown. Is it better solution than returning a Null?
    public String[] getTags() throws NullPointerException{
        if (isTagListEmpty()) throw new NullPointerException();
        return this.tags.toArray(new String[1]);
    }

    public void removeTag(String myTag) throws InvalidParameterException{
        if(myTag == null) throw new InvalidParameterException();
        this.tags.remove(myTag);
    }

    public void removeAllTags() {
        this.tags = new ArrayList<>();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Video)) return false;
        Video video = (Video) o;
        return Objects.equals(getURL(), video.getURL()) &&
                Objects.equals(getTitle(), video.getTitle()) &&
                Objects.equals(getOwner(), video.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getURL(), getTitle(), getOwner());
    }



}

