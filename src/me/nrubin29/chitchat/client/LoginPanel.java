package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.packet.packet.PacketLoginRequest;
import me.nrubin29.chitchat.common.packet.packet.PacketRegisterRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends Box {

    LoginPanel() {
        super(BoxLayout.Y_AXIS);

        JLabel logo = new JLabel(new ImageIcon(ProgramImage.LOGO.getImage()));
        final JTextField username = new JTextField();
        final JPasswordField password = new JPasswordField();
        final JTextField ip = new JTextField();

        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton login = new JButton("Login");
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerConnector.getInstance().setIP(ip.getText());
                ServerConnector.getInstance().sendPacket(new PacketLoginRequest(username.getText(), new String(password.getPassword())));
            }
        });

        JButton register = new JButton("Register");
        register.setAlignmentX(Component.CENTER_ALIGNMENT);
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ServerConnector.getInstance().setIP(ip.getText());
                ServerConnector.getInstance().sendPacket(new PacketRegisterRequest(username.getText(), new String(password.getPassword())));
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setMaximumSize(new Dimension(300, 300));
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(logo);
        panel.add(username);
        panel.add(password);
        panel.add(ip);
        panel.add(login);
        panel.add(register);

        add(Box.createVerticalGlue());
        add(panel);
        add(Box.createVerticalGlue());
    }
}