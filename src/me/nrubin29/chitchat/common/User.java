package me.nrubin29.chitchat.common;

import javax.swing.*;
import java.awt.*;

public class User extends AbstractUser {

    private JLabel label;

    public User(String name) {
        super(name);

        label = new JLabel(getName());
        label.setOpaque(true);
        label.setBackground(Color.GREEN);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
    }

    public JLabel getLabel() {
        return label;
    }
}