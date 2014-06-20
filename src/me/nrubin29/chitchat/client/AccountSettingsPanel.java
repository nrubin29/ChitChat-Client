package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.ChatManager;

import javax.swing.*;
import java.awt.*;

class AccountSettingsPanel extends JPanel {

    public AccountSettingsPanel() {
        JLabel user = new JLabel("Logged in as " + ChatManager.getInstance().getLocalUser().getName() + ".");
        add(user);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(320, 480));
    }
}