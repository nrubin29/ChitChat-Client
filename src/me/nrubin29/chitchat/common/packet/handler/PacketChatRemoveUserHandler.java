package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketChatRemoveUser;

public class PacketChatRemoveUserHandler extends PacketHandler<PacketChatRemoveUser> {

    public PacketChatRemoveUserHandler() {
        super(PacketChatRemoveUser.class);
    }

    @Override
    public void handle(PacketChatRemoveUser packet) {
        ChatManager.getInstance().getChat(packet.getChat()).removeUser(packet.getOldUser());
    }
}