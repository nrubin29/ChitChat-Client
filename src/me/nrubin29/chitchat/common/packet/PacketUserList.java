package me.nrubin29.chitchat.common.packet;

import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;

public class PacketUserList extends Packet {

    private static final long serialVersionUID = 1168341392763009279L;

    private final String[] users;

    public PacketUserList() {
        this.users = convert(ChatManager.getInstance().getAllUsers());
    }

    public String[] getUsers() {
        return users;
    }

    private String[] convert(AbstractUser[] abstractUsers) {
        String[] names = new String[abstractUsers.length];

        for (int i = 0; i < abstractUsers.length; i++) {
            names[i] = abstractUsers[i].getName() + ";" + abstractUsers[i].getDisplayName();
        }

        return names;
    }
}