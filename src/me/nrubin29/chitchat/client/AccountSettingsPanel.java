package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketUserDisplayNameChange;
import me.nrubin29.chitchat.common.packet.packet.PacketUserPasswordChange;
import me.nrubin29.chitchat.common.packet.packet.PacketUserPasswordChangeResponse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountSettingsPanel extends JPanel {

    private JLabel user;

    public AccountSettingsPanel() {
        user = new JLabel("Username: " + ChatManager.getInstance().getLocalUser().getName() + ". Display Name: " + ChatManager.getInstance().getLocalUser().getDisplayName() + ".");
        add(user);

        JButton changeDisplayName = new JButton("Change Display Name");
        changeDisplayName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newDisplayName = JOptionPane.showInputDialog(AccountSettingsPanel.this, "Enter a new display name.");
                if (newDisplayName == null) return;

                if (JOptionPane.showConfirmDialog(AccountSettingsPanel.this, "Do you want to change your display name?") == JOptionPane.YES_OPTION) {
                    ServerConnector.getInstance().sendPacket(new PacketUserDisplayNameChange(ChatManager.getInstance().getLocalUser(), newDisplayName));
                }
            }
        });
        add(changeDisplayName);

        JButton changePassword = new JButton("Change Password");
        changePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldPassword = JOptionPane.showInputDialog(AccountSettingsPanel.this, "Enter your old password.");
                if (oldPassword == null) return;

                String newPassword = JOptionPane.showInputDialog(AccountSettingsPanel.this, "Enter a new password.");
                if (newPassword == null) return;

                if (JOptionPane.showConfirmDialog(AccountSettingsPanel.this, "Do you want to change your password? If yes, wait for confirmation before closing this window.") == JOptionPane.YES_OPTION) {
                    ServerConnector.getInstance().sendPacket(new PacketUserPasswordChange(ChatManager.getInstance().getLocalUser(), oldPassword, newPassword));
                }
            }
        });
        add(changePassword);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(320, 480));
    }

    public void passwordChangeResponse(PacketUserPasswordChangeResponse response) {
        if (PacketUserPasswordChangeResponse.PasswordChangeResponse.valueOf(response.getResponse()) == PacketUserPasswordChangeResponse.PasswordChangeResponse.SUCCESS) {
            JOptionPane.showMessageDialog(this, "Password changed successfully.");
        } else {
            JOptionPane.showMessageDialog(this, "Could not change password.");
        }
    }

    public void displayNameChange() {
        user.setText("Username: " + ChatManager.getInstance().getLocalUser().getName() + ". Display Name: " + ChatManager.getInstance().getLocalUser().getDisplayName() + ".");
        validate();
        repaint();
    }
}