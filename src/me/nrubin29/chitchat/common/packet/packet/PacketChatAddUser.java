package me.nrubin29.chitchat.common.packet.packet;

import me.nrubin29.chitchat.common.Chat;

public class PacketChatAddUser extends Packet {

    private static final long serialVersionUID = 4270772243018965156L;

    private final String chat;
    private final String newUser;

    public PacketChatAddUser(Chat chat, String newUser) {
        this(chat.getName(), newUser);
    }

    public PacketChatAddUser(String chat, String newUser) {
        this.chat = chat;
        this.newUser = newUser;
    }

    public String getChat() {
        return chat;
    }

    public String getNewUser() {
        return newUser;
    }
}