package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketUserStatusChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

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

    private TrayIcon trayIcon;

    private void setup() {
        setTitle("ChitChat - Login");

        showLoginPanel();

        setBackground(Color.WHITE);
        setSize(640, 480);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                if (!login) {
                    ServerConnector.getInstance().sendPacket(new PacketUserStatusChange(ChatManager.getInstance().getLocalUser(), AbstractUser.UserStatus.AWAY));
                }
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                if (!login) {
                    ServerConnector.getInstance().sendPacket(new PacketUserStatusChange(ChatManager.getInstance().getLocalUser(), AbstractUser.UserStatus.ONLINE));
                }
            }
        });

        trayIcon = new TrayIcon(ProgramImage.TRAYLOGO.getImage(), "ChitChat");
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!login && !isVisible()) {
                    setVisible(true);
                }
            }
        });
    }

    public void showLoginPanel() {
        if (mainPanel != null) {
            remove(mainPanel);
        }

        add(loginPanel != null ? loginPanel : (loginPanel = new LoginPanel()));

        login = true;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        SystemTray.getSystemTray().remove(trayIcon);

        validate();
        repaint();
    }

    public void showMainPanel() {
        if (loginPanel != null) {
            remove(loginPanel);
        }

        add(mainPanel != null ? mainPanel : (mainPanel = new MainPanel()));
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        login = false;

        try {
            SystemTray.getSystemTray().add(trayIcon);
        } catch (Exception e) {
            e.printStackTrace();
        }

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