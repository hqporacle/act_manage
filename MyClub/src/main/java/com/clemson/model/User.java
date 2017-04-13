package com.clemson.model;

/**
 * Created by shiwguo on 2017/4/5.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String realName;
    private int role;
    private String identity;
    private String phone;

    public User(int id, String username, String password, String realName, int role, String identity, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.realName = realName;
        this.role = role;
        this.identity = identity;
        this.phone = phone;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
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

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
