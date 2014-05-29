package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.client.ServerConnector;
import me.nrubin29.chitchat.client.Window;
import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketLoginResponse;
import me.nrubin29.chitchat.common.packet.packet.PacketLoginResponse.LoginResponse;

import javax.swing.*;

public class PacketLoginResponseHandler extends PacketHandler<PacketLoginResponse> {

    public PacketLoginResponseHandler() {
        super(PacketLoginResponse.class);
    }

    @Override
    public void handle(PacketLoginResponse packet) {
        LoginResponse response = LoginResponse.valueOf(packet.getResponse());

        if (response == LoginResponse.SUCCESS) {
            Window.getInstance().loginSuccess(); // This could cause errors later...
            ChatManager.getInstance().setLocalUser(new AbstractUser(packet.getUser())); // TODO: Get an AbstractUser from the server?
        } else {
            JOptionPane.showMessageDialog(Window.getInstance(), "Login failed.");
            ServerConnector.getInstance().disconnect();
        }
    }
}