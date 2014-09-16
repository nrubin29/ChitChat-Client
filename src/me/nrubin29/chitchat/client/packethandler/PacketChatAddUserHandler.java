package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketChatAddUser;

public class PacketChatAddUserHandler extends PacketHandler<PacketChatAddUser> {

    public PacketChatAddUserHandler() {
        super(PacketChatAddUser.class);
    }

    @Override
    public void handle(PacketChatAddUser packet) {
        ChatManager.getInstance().getChat(packet.getChat()).addUser(packet.getNewUser());
    }
}