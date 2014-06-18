package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.client.ServerConnector;
import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.client.Window;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketRegisterResponse;
import me.nrubin29.chitchat.common.packet.packet.PacketRegisterResponse.RegisterResponse;

import javax.swing.*;

public class PacketRegisterResponseHandler extends PacketHandler<PacketRegisterResponse> {

    public PacketRegisterResponseHandler() {
        super(PacketRegisterResponse.class);
    }

    @Override
    public void handle(PacketRegisterResponse packet) {
        RegisterResponse response = RegisterResponse.valueOf(packet.getResponse());

        if (response == RegisterResponse.SUCCESS) {
            ChatManager.getInstance().setLocalUser(new User(packet.getUser()));
            Window.getInstance().swapPanels();
        } else {
            JOptionPane.showMessageDialog(Window.getInstance(), "Register failed.");
            ServerConnector.getInstance().disconnect();
        }
    }
}