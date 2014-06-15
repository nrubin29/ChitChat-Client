package me.nrubin29.chitchat.common;

import java.io.Serializable;

public class ChatData implements Serializable {

    private static final long serialVersionUID = -8228690534652292411L;

    private final String name;
    private final String[] users;

    public ChatData(String name, String[] users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public String[] getUsers() {
        return users;
    }
}