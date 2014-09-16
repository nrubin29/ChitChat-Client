package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.client.Main;
import me.nrubin29.chitchat.client.ServerConnector;
import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketLoginResponse;
import me.nrubin29.chitchat.common.packet.PacketLoginResponse.LoginResponse;

import javax.swing.*;

public class PacketLoginResponseHandler extends PacketHandler<PacketLoginResponse> {

    public PacketLoginResponseHandler() {
        super(PacketLoginResponse.class);
    }

    @Override
    public void handle(PacketLoginResponse packet) {
        LoginResponse response = LoginResponse.valueOf(packet.getResponse());

        if (response == LoginResponse.SUCCESS) {
            ChatManager.getInstance().setLocalUser(new User(packet.getUser().split(";")));
            Main.getInstance().showMainPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Login failed."); // TODO: Replace with JavaFX popup.
            ServerConnector.getInstance().disconnect();
        }
    }
}