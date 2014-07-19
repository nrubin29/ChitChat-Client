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

    public void addChat(Chat chat) {
        chats.add(chat);
        Window.getInstance().getMainPanel().chatAdded(chat);
    }

    public void removeChat(String name) {
        removeChat(getChat(name));
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

    public Chat[] getChats(User user) {
        ArrayList<Chat> userChats = new ArrayList<Chat>();

        for (Chat chat : chats) {
            if (chat.hasUser(user.getName())) {
                userChats.add(chat);
            }
        }

        return userChats.toArray(new Chat[userChats.size()]);
    }

    public void addUser(User user) {
        users.add(user);

        for (Chat chat : getChats(user)) {
            chat.getChatPanel().update();
        }
    }

    public void removeUser(User user) {
        users.remove(user);

        for (Chat chat : getChats(user)) {
            chat.getChatPanel().update();
        }
    }

    public User getLocalUser() {
        return localUser;
    }

    public void setLocalUser(User localUser) {
        this.localUser = localUser;
    }

    public User getUser(String name) {
        if (localUser.getName().equals(name)) return localUser;

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