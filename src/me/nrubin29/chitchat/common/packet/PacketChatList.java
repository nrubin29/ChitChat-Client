package me.nrubin29.chitchat.common.packet;

import me.nrubin29.chitchat.common.ChatData;

public class PacketChatList extends Packet {

    private static final long serialVersionUID = 6368232371915047155L;

    private final ChatData[] chats;

    public PacketChatList(ChatData[] chats) {
        this.chats = chats;
    }

    public ChatData[] getChats() {
        return chats;
    }
}