package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

class JBubblePane extends JTextPane {

    public JBubblePane(Chat chat) {
        for (String user : chat.getUsers()) {
            if (ChatManager.getInstance().getUser(user) != null) {
                addBubble(ChatManager.getInstance().getUser(user));
            } else {
                addBubble(user);
            }
        }

        setMaximumSize(new Dimension(490, 20));
        setEditable(false);
    }

    public void addBubble(User user) {
        setCaretPosition(getStyledDocument().getLength());
        insertComponent(user.getLabel());
        try {
            getStyledDocument().insertString(getStyledDocument().getLength(), " ", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void addBubble(String user) {
        JLabel label = new JLabel(user);
        label.setOpaque(true);
        label.setBackground(Color.RED);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setCaretPosition(getStyledDocument().getLength());
        insertComponent(label);
        try {
            getStyledDocument().insertString(getStyledDocument().getLength(), " ", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void removeBubble(User user) {
        for (Component c : getComponents()) {
            if (c instanceof JLabel) {
                if (c.equals(user.getLabel())) {
                    remove(c);
                }
            }
        }
    }

    public void removeBubble(String user) {
        for (Component c : getComponents()) {
            if (c instanceof JLabel) {
                if (((JLabel) c).getText().equals(user)) {
                    remove(c);
                }
            }
        }
    }
}