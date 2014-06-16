package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketUserJoin;

public class PacketUserJoinHandler extends PacketHandler<PacketUserJoin> {

    PacketUserJoinHandler() {
        super(PacketUserJoin.class);
    }

    @Override
    public void handle(PacketUserJoin packet) {
        ChatManager.getInstance().addUser(new AbstractUser(packet.getUser()));
    }
}