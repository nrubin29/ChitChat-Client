package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.client.packet.packet.PacketLoginRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends Box {

    LoginPanel() {
        super(BoxLayout.Y_AXIS);

        final JTextField username = new JTextField();
        final JPasswordField password = new JPasswordField();
        final JTextField ip = new JTextField("localhost:");

        JButton login = new JButton("Login");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerConnector.getInstance().setIP(ip.getText());
                ServerConnector.getInstance().sendPacket(new PacketLoginRequest(username.getText(), new String(password.getPassword())));
            }
        });
        add(login);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(200, 200));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(username);
        panel.add(password);
        panel.add(ip);
        panel.add(login);

        add(Box.createVerticalGlue());
        add(panel);
        add(Box.createVerticalGlue());
    }
}