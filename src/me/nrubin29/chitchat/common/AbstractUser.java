package me.nrubin29.chitchat.common;

import java.io.Serializable;

public class AbstractUser implements Serializable {

    private static final long serialVersionUID = 4086519985573649809L;

    private String name;

    public AbstractUser() {
        this("");
    }

    public AbstractUser(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return name;
    }
}