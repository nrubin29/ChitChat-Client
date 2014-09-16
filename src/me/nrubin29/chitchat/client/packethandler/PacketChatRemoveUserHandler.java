package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketChatRemoveUser;

public class PacketChatRemoveUserHandler extends PacketHandler<PacketChatRemoveUser> {

    public PacketChatRemoveUserHandler() {
        super(PacketChatRemoveUser.class);
    }

    @Override
    public void handle(PacketChatRemoveUser packet) {
        ChatManager.getInstance().getChat(packet.getChat()).removeUser(packet.getOldUser());
    }
}