package me.nrubin29.chitchat.common.packet.packet;

import me.nrubin29.chitchat.common.AbstractUser;

public class PacketUserLeave extends Packet {

    private static final long serialVersionUID = 7711637711615685676L;

    private String user;

    public PacketUserLeave(AbstractUser user) {
        this.user = user.getName();
    }

    public String getUser() {
        return user;
    }
}