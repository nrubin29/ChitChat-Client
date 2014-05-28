package me.nrubin29.chitchat.client;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private Window() {
    }

    private static final Window instance = new Window();

    public static Window getInstance() {
        return instance;
    }

    private LoginPanel loginPanel;
    private ChatPanel chatPanel;

    private void setup() {
        setTitle("ChitChat - Login");

        add(loginPanel = new LoginPanel());

        setBackground(Color.WHITE);
        setSize(640, 480);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

//        ChatManager.getInstance().addUser(new User("Noah"));
//        ChatManager.getInstance().addUser(new User("Luke"));
//        ChatManager.getInstance().addUser(new User("Bob"));
//
//        ChatManager.getInstance().addChat(new Chat("Noah", "Noah"));
//        ChatManager.getInstance().addChat(new Chat("Friends", "Noah", "Luke"));
//        ChatManager.getInstance().addChat(new Chat("Global", "Noah", "Luke", "Bob"));
//
//        PacketHandlerManager.getInstance().handle(new PacketMessage("Noah", "Noah", "Hello there!"));
    }

    public void loginSuccess() {
        remove(loginPanel);
        add(chatPanel);
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public static void main(String[] args) {
        Window.getInstance().setup();
    }
}