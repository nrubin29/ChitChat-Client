package me.nrubin29.chitchat.client.packet.handler;

import me.nrubin29.chitchat.client.Window;
import me.nrubin29.chitchat.client.packet.packet.PacketLoginResponse;

import javax.swing.*;
import java.util.HashMap;

public class PacketLoginResponseHandler extends PacketHandler {

    @Override
    public void handle(HashMap<String, String> args) {
        PacketLoginResponse.LoginResponse response = PacketLoginResponse.LoginResponse.valueOf(args.get("response"));

        if (response == PacketLoginResponse.LoginResponse.SUCCESS) {
            Window.getInstance().loginSuccess();
        } else {
            JOptionPane.showMessageDialog(Window.getInstance(), "Login failed.");
        }
    }
}