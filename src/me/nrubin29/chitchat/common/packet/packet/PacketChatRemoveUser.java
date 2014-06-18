package me.nrubin29.chitchat.common.packet.packet;

import me.nrubin29.chitchat.common.Chat;

public class PacketChatRemoveUser extends Packet {

    private static final long serialVersionUID = -9170778343018997460L;

    private final String chat;
    private final String oldUser;

    public PacketChatRemoveUser(Chat chat, String oldUser) {
        this(chat.getName(), oldUser);
    }

    public PacketChatRemoveUser(String chat, String oldUser) {
        this.chat = chat;
        this.oldUser = oldUser;
    }

    public String getChat() {
        return chat;
    }

    public String getOldUser() {
        return oldUser;
    }
}