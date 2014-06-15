package me.nrubin29.chitchat.common;

import java.util.Date;

public class Message {

    private final AbstractUser sender;
    private final Chat chat;
    private final String message;
    private final Date when;

    public Message(String sender, String chat, String message, Date when) {
        this(ChatManager.getInstance().getUser(sender), ChatManager.getInstance().getChat(chat), message, when);
    }

    private Message(AbstractUser sender, Chat chat, String message, Date when) {
        this.sender = sender;
        this.chat = chat;
        this.message = message;
        this.when = when;
    }

    public AbstractUser getSender() {
        return sender;
    }

    public Chat getChat() {
        return chat;
    }

    public String getMessage() {
        return message;
    }

    public Date getWhen() {
        return when;
    }
}