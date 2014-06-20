package me.nrubin29.chitchat.common;

import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.client.Window;

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
    private User localUser;

    public Chat addChat(Chat chat) {
        chats.add(chat);
        Window.getInstance().getMainPanel().chatAdded(chat);
        return chat;
    }

    public Chat removeChat(String name) {
        return removeChat(getChat(name));
    }

    Chat removeChat(Chat chat) {
        chats.remove(chat);
        Window.getInstance().getMainPanel().chatRemoved(chat);
        return chat;
    }

    public Chat getChat(String name) {
        for (Chat chat : chats) {
            if (chat.getName().equals(name)) {
                return chat;
            }
        }

        return null;
    }

    public User addUser(User user) {
        users.add(user);

        for (Chat chat : chats) {
            if (chat.hasUser(user.getName())) {
                chat.getChatPanel().userAdded();
            }
        }

        return user;
    }

    public User removeUser(User user) {
        users.remove(user);

        for (Chat chat : chats) {
            if (chat.hasUser(user.getName())) {
                chat.getChatPanel().userRemoved();
            }
        }

        return user;
    }

    public User getLocalUser() {
        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public User getUser(String name) {
        for (User user : getAllUsers()) {
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

    public User[] getAllUsers() {
        ArrayList<User> temp = new ArrayList<User>();
        temp.addAll(users);
        temp.add(localUser);
        return temp.toArray(new User[temp.size()]);
    }

    public void clear() {
        chats.clear();
        users.clear();
        localUser = null;
    }
}