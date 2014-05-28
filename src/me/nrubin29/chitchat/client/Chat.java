package me.nrubin29.chitchat.client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Chat {

    private final String name;
    private final ArrayList<User> users;
    private final ArrayList<Message> messages;

    private final transient JTextArea textArea;

    public Chat(String name, String... names) {
        this(name, ChatManager.getInstance().getUsers(names));
    }

    private Chat(String name, User... users) {
        this.name = name;
        this.users = new ArrayList<User>(Arrays.asList(users));
        this.messages = new ArrayList<Message>();

        this.textArea = new JTextArea();
        textArea.setMaximumSize(new Dimension(490, 460 - 35));
        textArea.setEditable(false);
    }

    public String getName() {
        return name;
    }

    public User[] getUsers() {
        return users.toArray(new User[users.size()]);
    }

    public Message[] getMessages() {
        return messages.toArray(new Message[messages.size()]);
    }

    public void addMessage(String sender, String chat, String msg, Date when) {
        Message message = new Message(sender, chat, msg, when);
        messages.add(message);
        Window.getInstance().getChatPanel().messageReceived(message);
    }

    public JTextArea getTextArea() {
        return textArea;
    }
}