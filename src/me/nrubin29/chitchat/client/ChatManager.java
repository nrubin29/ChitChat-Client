package me.nrubin29.chitchat.client;

import java.util.ArrayList;

public class ChatManager {

    private ChatManager() {
    }

    private static final ChatManager instance = new ChatManager();

    public static ChatManager getInstance() {
        return instance;
    }

    private final ArrayList<Chat> chats = new ArrayList<Chat>();
    private final ArrayList<User> users = new ArrayList<User>();

    public void addChat(Chat chat) {
        chats.add(chat);
        Window.getInstance().getChatPanel().chatAdded(chat);
    }

    public void removeChat(Chat chat) {
        chats.remove(chat);
        Window.getInstance().getChatPanel().chatRemoved(chat);
    }

    public Chat getChat(String name) {
        for (Chat chat : chats) {
            if (chat.getName().equals(name)) {
                return chat;
            }
        }

        return null;
    }

    public void addUser(User user) {
        users.add(user);
        Window.getInstance().getChatPanel().userAdded(user);
    }

    public void removeUser(User user) {
        users.remove(user);
        Window.getInstance().getChatPanel().userRemoved(user);
    }

    public User getUser(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        return null;
    }

    public User[] getUsers(String... names) {
        User[] users = new User[names.length];

        int i = 0;
        for (String name : names) {
            users[i++] = getUser(name);
        }

        return users;
    }
}