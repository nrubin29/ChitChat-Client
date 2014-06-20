package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;

import javax.swing.*;
import java.awt.*;

class JBubblePane extends JPanel {

    private Chat chat;

    public JBubblePane(Chat chat) {
        this.chat = chat;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setMaximumSize(new Dimension(490, 20));
    }

    public void update() {
        removeAll();

        for (String user : chat.getUsers()) {
            if (ChatManager.getInstance().getUser(user) != null) {
                add(ChatManager.getInstance().getUser(user).getLabel());
            } else {
                JLabel label = new JLabel(user);
                label.setOpaque(true);
                label.setBackground(Color.RED);
                label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                add(label);
            }

            add(Box.createHorizontalStrut(5));
        }

        validate();
        repaint();
    }
}