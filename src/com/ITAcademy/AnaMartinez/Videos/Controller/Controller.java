package com.ITAcademy.AnaMartinez.Videos.Controller;

import com.ITAcademy.AnaMartinez.Videos.Domain.User;
import com.ITAcademy.AnaMartinez.Videos.Domain.Video;

import java.security.InvalidParameterException;
import java.util.*;

public class Controller {

    //TODO remove functions: remove user, remove all users, remove video, remove all videos, remove tag, remove all tags
    private Set<User> users;
    private List<Video> videos;

    public Controller(){
         users = new HashSet<>();
         videos = new ArrayList<>();
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    public void addVideo(Video video) throws InvalidParameterException {
        if(users.contains(User.createDummy(video.getOwner()))) {
            videos.add(video);
        }
        else throw new InvalidParameterException();
    }

    public int getNumberOfVideos() {
        return videos.size();
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public List<Video> getAllVideosFromUser(String userID) throws InvalidParameterException {
        if(!users.contains(User.createDummy(userID))) throw new InvalidParameterException();
        List<Video> userVideoList = new ArrayList<>();
        for(Video v : videos){
            if(v.getOwner().equals(userID)) userVideoList.add(v);
        }
        return userVideoList;
    }

    public boolean getNumberOfTagsFromVideo(UUID uuid) throws InvalidParameterException{
        for (Video v : videos){
            if(v.getUUID().equals(uuid)) return v.isTagListEmpty();
        }
        throw new InvalidParameterException();
    }

    public List<String> getListOfTagsFromVideo(UUID uuid) throws InvalidParameterException {
        for (Video v : videos){
            if(v.getUUID().equals(uuid)) return v.getTags();
        }
        throw new InvalidParameterException();
    }

    public void addTagToVideo(UUID uuid, String myTag) throws InvalidParameterException{
        for (Video v : videos){
            if(v.getUUID().equals(uuid)){
                v.addTag(myTag);
                return;
            }
        }
        throw new InvalidParameterException();
    }
}
