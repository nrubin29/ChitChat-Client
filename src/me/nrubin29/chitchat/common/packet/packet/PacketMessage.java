package me.nrubin29.chitchat.common.packet.packet;

import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.Chat;

import java.util.Date;

public class PacketMessage extends Packet {

    private static final long serialVersionUID = -4098200773356894785L;

    public PacketMessage(Chat chat, AbstractUser sender, String msg) {
        this(chat.getName(), sender.getName(), msg);
    }

    private final String chat;
    private final String sender;
    private final String msg;
    private final Date when;

    public PacketMessage(String chat, String sender, String msg) {
        this.chat = chat;
        this.sender = sender;
        this.msg = msg.replaceAll(" ", "%20");
        this.when = new Date(System.currentTimeMillis());
    }

    public String getChat() {
        return chat;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return msg;
    }

    public Date getWhen() {
        return when;
    }
}