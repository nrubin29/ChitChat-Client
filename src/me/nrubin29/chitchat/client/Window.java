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
    private boolean login;

    private void setup() {
        setTitle("ChitChat - Login");

        add(loginPanel = new LoginPanel());
        login = true;

        setBackground(Color.WHITE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void swapPanels() {
        if (login) {
            remove(loginPanel);
            add(mainPanel = new MainPanel());
        } else {
            remove(mainPanel);
            add(loginPanel = new LoginPanel());
        }

        login = !login;

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