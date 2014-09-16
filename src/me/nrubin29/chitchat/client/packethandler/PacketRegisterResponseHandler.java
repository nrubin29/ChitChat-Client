package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.client.ServerConnector;
import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.client.Window;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketRegisterResponse;
import me.nrubin29.chitchat.common.packet.PacketRegisterResponse.RegisterResponse;

import javax.swing.*;

public class PacketRegisterResponseHandler extends PacketHandler<PacketRegisterResponse> {

    public PacketRegisterResponseHandler() {
        super(PacketRegisterResponse.class);
    }

    @Override
    public void handle(PacketRegisterResponse packet) {
        RegisterResponse response = RegisterResponse.valueOf(packet.getResponse());

        if (response == RegisterResponse.SUCCESS) {
            ChatManager.getInstance().setLocalUser(new User(packet.getUser().split(";")));
            Window.getInstance().showMainPanel();
        } else {
            JOptionPane.showMessageDialog(null, "Register failed."); // TODO: Replace with JavaFX popup.
            ServerConnector.getInstance().disconnect();
        }
    }
}