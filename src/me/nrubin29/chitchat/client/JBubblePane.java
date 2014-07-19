package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;

import javax.swing.*;
import java.awt.*;

class JBubblePane extends JPanel {

    private final Chat chat;

    public JBubblePane(Chat chat) {
        this.chat = chat;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(new Dimension(490, 20));
    }

    public void update() {
        removeAll();

        for (String user : chat.getUsers()) {
            JLabel label = new JLabel(user);
            label.setOpaque(true);
            label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
            add(label);

            if (ChatManager.getInstance().getUser(user) != null) {
                User u = ChatManager.getInstance().getUser(user);
                label.setText(u.getDisplayName());

                if (u.getUserStatus() == AbstractUser.UserStatus.ONLINE) {
                    label.setBackground(Color.GREEN);
                } else if (u.getUserStatus() == AbstractUser.UserStatus.AWAY) {
                    label.setBackground(Color.YELLOW);
                }
            } else {
                label.setBackground(Color.RED);
            }

            add(Box.createHorizontalStrut(5));
        }

        validate();
        repaint();
    }
}