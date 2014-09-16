package me.nrubin29.chitchat.common.packet;

import me.nrubin29.chitchat.common.AbstractUser;

public class PacketUserDisplayNameChange extends Packet {

    private static final long serialVersionUID = 4838647364923128907L;

    private final String user, newDisplayName;

    public PacketUserDisplayNameChange(AbstractUser user, String newDisplayName) {
        this.user = user.getName();
        this.newDisplayName = newDisplayName;
    }

    public String getUser() {
        return user;
    }

    public String getNewDisplayName() {
        return newDisplayName;
    }
}