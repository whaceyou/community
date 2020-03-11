package com.example.communicate.dto;

/**
 * @ClassName GitHubUser
 * @Description: TODO
 * @Author whaceyou
 * @Date 2020/3/11
 * @Version V1.0
 **/
public class GitHubUser {
    private String name;
    private String id;
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
