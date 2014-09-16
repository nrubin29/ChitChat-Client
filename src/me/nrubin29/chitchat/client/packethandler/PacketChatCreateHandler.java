package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketChatCreate;

public class PacketChatCreateHandler extends PacketHandler<PacketChatCreate> {

    public PacketChatCreateHandler() {
        super(PacketChatCreate.class);
    }

    @Override
    public void handle(PacketChatCreate packet) {
        ChatManager.getInstance().addChat(new Chat(packet.getChat(), packet.getUsers()));
    }
}