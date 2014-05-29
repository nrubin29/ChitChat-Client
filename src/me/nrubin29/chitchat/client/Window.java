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
    private MainPanel mainPanel;

    private void setup() {
        setTitle("ChitChat - Login");

        add(loginPanel = new LoginPanel());
//        add(mainPanel = new MainPanel());

        setBackground(Color.WHITE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

//        User u = ChatManager.getInstance().addUser(new User("Noah"));
//        ChatManager.getInstance().addUser(new User("Luke"));
//        ChatManager.getInstance().addUser(new User("Bob"));
//
//        Chat c = ChatManager.getInstance().addChat(new Chat("Noah", "Noah"));
//        ChatManager.getInstance().addChat(new Chat("Friends", "Noah", "Luke"));
//        ChatManager.getInstance().addChat(new Chat("Global", "Noah", "Luke", "Bob"));
//
//        PacketHandlerManager.getInstance().handle(new PacketMessage(c, u, "Hello there!"));
    }

    public void loginSuccess() {
        remove(loginPanel);
        add(mainPanel = new MainPanel());

        validate();
        repaint();
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public static void main(String[] args) {
        Window.getInstance().setup();
    }
}