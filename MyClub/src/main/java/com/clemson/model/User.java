package com.clemson.model;

/**
 * Created by shiwguo on 2017/4/5.
 */
public class User {
    private int id;
    private String name;
    private String password;
    private String realName;
    private int role;

    public User(int id, String name, String password, String realName, int role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.realName = realName;
        this.role = role;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRealName() {
        return realName;
    }

    public int getRole() {
        return role;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
