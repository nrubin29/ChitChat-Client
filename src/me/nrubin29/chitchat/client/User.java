package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.AbstractUser;

public class User extends AbstractUser {

    public User(String[] information) {
        super(information[0], information[1]);
    }
}