package me.nrubin29.chitchat.client.packet.packet;

public class PacketLoginRequest extends Packet {

    public PacketLoginRequest(String user, String password) {
        args.put("user", user);
        args.put("password", password);
    }
}