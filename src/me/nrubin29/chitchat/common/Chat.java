package me.nrubin29.chitchat.common;

import me.nrubin29.chitchat.client.ChatPanel;
import me.nrubin29.chitchat.client.Main;
import me.nrubin29.chitchat.client.Notification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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
        this.users = new ArrayList<>();
        this.messages = new ArrayList<>();

        this.chatPanel = new ChatPanel(this);

        for (String user : users) {
            addUser(user);
        }
    }

    public String getName() {
        return name;
    }

    public String[] getUsers() {
        return users.toArray(new String[users.size()]);
    }

    public void addUser(String user) {
        users.add(user);
        chatPanel.update();
    }

    public void removeUser(String user) {
        users.remove(user);
        chatPanel.update();
    }

    public boolean hasUser(String user) {
        return users.contains(user);
    }

    public Message[] getMessages() {
        return messages.toArray(new Message[messages.size()]);
    }

    public void addMessage(String sender, String chat, String msg, Calendar when) {
        Message message = new Message(sender, chat, msg, when);
        messages.add(message);
        chatPanel.messageReceived(message);

        if (!Main.getInstance().isVisible()) {
            Notification.showNotification(message);
        }
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    @Override
    public String toString() {
        return "Chat name=" + name + " users=" + Arrays.toString(users.toArray());
    }
}