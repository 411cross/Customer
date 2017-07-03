package com.example.peek_mapdemotest.nurseapp.Entity;

/**
 * Created by Administrator on 2017/6/29.
 */

public class User {
    private String id;
    private String password;
    private String name;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User(String id, String password, String name){
        super();
        this.id=id;
        this.password=password;
        this.name=name;

    }

    public User(String id, String password, String name, String avatar) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
    }
}