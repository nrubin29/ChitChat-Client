package me.nrubin29.chitchat.common;

public class AbstractUser {

    public enum UserStatus {
        ONLINE,
        AWAY
    }

    private String name, displayName;
    private UserStatus userStatus;

    protected AbstractUser() {
        this("", "");
    }

    protected AbstractUser(String name, String displayName) {
        this.name = name;
        this.displayName = displayName;
        this.userStatus = UserStatus.ONLINE;
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    /*
    This should be called once by the server.
     */
    public void setName(String name) {
        this.name = name;
    }

    /*
    This should be called once by the server.
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return displayName;
    }
}