package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserJoin;

public class PacketUserJoinHandler extends PacketHandler<PacketUserJoin> {

    PacketUserJoinHandler() {
        super(PacketUserJoin.class);
    }

    @Override
    public void handle(PacketUserJoin packet) {
        ChatManager.getInstance().addUser(new User(packet.getUser().split(";")));
    }
}