package me.nrubin29.chitchat.common;

import me.nrubin29.chitchat.client.ChatPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Chat {

    private final String name;
    private final ArrayList<String> users;
    private final ArrayList<Message> messages;

    private final transient ChatPanel chatPanel;

    public Chat(ChatData chatData) {
        this(chatData.getName(), chatData.getUsers());
    }

    public Chat(String name, String... users) {
        this.name = name;
        this.users = new ArrayList<String>(Arrays.asList(users));
        this.messages = new ArrayList<Message>();

        this.chatPanel = new ChatPanel(this);
    }

    public String getName() {
        return name;
    }

    public String[] getUsers() {
        return users.toArray(new String[users.size()]);
    }

    public void addUser(String user) {
        users.add(user);
    }

    public Message[] getMessages() {
        return messages.toArray(new Message[messages.size()]);
    }

    public void addMessage(String sender, String chat, String msg, Date when) {
        Message message = new Message(sender, chat, msg, when);
        messages.add(message);
        chatPanel.messageReceived(message);
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }
}