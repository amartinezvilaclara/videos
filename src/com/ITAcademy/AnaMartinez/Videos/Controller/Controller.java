package com.ITAcademy.AnaMartinez.Videos.Controller;

import com.ITAcademy.AnaMartinez.Videos.Domain.User;
import com.ITAcademy.AnaMartinez.Videos.Domain.Video;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {

    private Set<User> users;
    private List<Video> videos;

    public Controller(){
         users = new HashSet<>();
         videos = new ArrayList<>();
    }

    public boolean addUser(String userId, String name, String surname, String password) {
        return users.add(new User(userId, name, surname, password));
    }

    public void addVideo(String URL, String title, String owner) {
        if(users.contains(User.createDummy(owner))) {
            videos.add(new Video(URL, title, owner));
        }
        else throw new InvalidParameterException();
    }

    public int getNumberOfVideos() {
        return videos.size();
    }

    public int getNumberOfUsers() {
        return users.size();
    }

    public List<Video> getAllVideosFromUser(String userID) {
        List<Video> userVideoList = new ArrayList<>();
        for(Video v : videos){
            if(v.getOwner().equals(userID)) userVideoList.add(v);
        }
        return userVideoList;
    }
}
