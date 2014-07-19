package me.nrubin29.chitchat.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneralSettingsPanel extends JPanel {

    public GeneralSettingsPanel() {
        final JCheckBox notifications = new JCheckBox("Enable Notifications");
        notifications.setSelected(Boolean.valueOf(Settings.getInstance().get("notifications")));
        notifications.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.getInstance().set("notifications", notifications.isSelected());
            }
        });
        add(notifications);

        final JCheckBox sound = new JCheckBox("Enable Notification Sound");
        sound.setSelected(Boolean.valueOf(Settings.getInstance().get("notifications")));
        sound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.getInstance().set("sound", sound.isSelected());
            }
        });
        add(sound);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(320, 480));
    }
}