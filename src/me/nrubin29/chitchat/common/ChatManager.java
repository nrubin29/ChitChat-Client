package me.nrubin29.chitchat.common;

import me.nrubin29.chitchat.client.Window;

import java.util.ArrayList;
import java.util.Arrays;

public class ChatManager {

    private ChatManager() {
    }

    private static final ChatManager instance = new ChatManager();

    public static ChatManager getInstance() {
        return instance;
    }

    private final ArrayList<Chat> chats = new ArrayList<Chat>();
    private final ArrayList<AbstractUser> users = new ArrayList<AbstractUser>();
    private AbstractUser localUser;

    public Chat addChat(Chat chat) {
        System.out.println("Adding chat " + chat.getName() + " with users " + Arrays.toString(chat.getUsers()));
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

    public AbstractUser addUser(AbstractUser user) {
        users.add(user);
        Window.getInstance().getMainPanel().getCurrentChat().getChatPanel().userAdded(user.getName());
        return user;
    }

    public AbstractUser removeUser(AbstractUser user) {
        users.remove(user);
        Window.getInstance().getMainPanel().getCurrentChat().getChatPanel().userRemoved(user.getName());
        return user;
    }

    public AbstractUser getLocalUser() {
        return localUser;
    }

    public void setLocalUser(AbstractUser localUser) {
        this.localUser = localUser;
    }

    public AbstractUser getUser(String name) {
        for (AbstractUser user : getAllUsers()) {
            if (user.getName().equals(name)) {
                return user;
            }
        }

        throw new NullPointerException("User by name " + name + " does not exist.");
    }

    public AbstractUser[] getUsers(String... names) {
        AbstractUser[] users = new AbstractUser[names.length];

        int i = 0;
        for (String name : names) {
            users[i++] = getUser(name);
        }

        return users;
    }

    AbstractUser[] getAllUsers() {
        ArrayList<AbstractUser> temp = new ArrayList<AbstractUser>();
        temp.addAll(users);
        temp.add(localUser);
        return temp.toArray(new AbstractUser[temp.size()]);
    }
}