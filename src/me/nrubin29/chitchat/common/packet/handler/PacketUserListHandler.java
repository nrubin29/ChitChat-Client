package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketUserList;

public class PacketUserListHandler extends PacketHandler<PacketUserList> {

    PacketUserListHandler() {
        super(PacketUserList.class);
    }

    @Override
    public void handle(PacketUserList packet) {
        for (String user : packet.getUsers()) {
            ChatManager.getInstance().addUser(new User(user.split(";")));
        }
    }
}