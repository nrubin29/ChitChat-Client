package me.nrubin29.chitchat.common;

import java.io.Serializable;

public class AbstractUser implements Serializable {

    private static final long serialVersionUID = 4086519985573649809L;

    public enum UserStatus {
        ONLINE,
        AWAY
    }

    private String name;
    private UserStatus userStatus;

    protected AbstractUser() {
        this("");
    }

    protected AbstractUser(String name) {
        this.name = name;
        this.userStatus = UserStatus.ONLINE;
    }

    public String getName() {
        return name;
    }

    /*
    This should be called once by the server.
     */
    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return name;
    }
}