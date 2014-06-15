package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.Chat;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;

class JBubblePane extends JTextPane {

    public JBubblePane(Chat chat) {
        for (String user : chat.getUsers()) {
            addBubble(user);
        }

        setMaximumSize(new Dimension(490, 20));
        setEditable(false);
    }

    public void addBubble(String text) {
        JLabel label = new JLabel(text);
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        setCaretPosition(getStyledDocument().getLength());
        insertComponent(label);
        try {
            getStyledDocument().insertString(getStyledDocument().getLength(), " ", null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public void removeBubble(String text) {
        for (Component c : getComponents()) {
            if (c instanceof JLabel) {
                if (((JLabel) c).getText().equals(text)) {
                    remove(c);
                }
            }
        }
    }
}