package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatData;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketChatList;

public class PacketChatListHandler extends PacketHandler<PacketChatList> {

    public PacketChatListHandler() {
        super(PacketChatList.class);
    }

    @Override
    public void handle(PacketChatList packet) {
        for (ChatData chat : packet.getChats()) {
            System.out.println("Going to add chat " + chat.getName());
            ChatManager.getInstance().addChat(new Chat(chat));
        }
    }
}