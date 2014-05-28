package me.nrubin29.chitchat.client.packet.packet;

public class PacketLoginResponse extends Packet {

    public enum LoginResponse {
        SUCCESS,
        FAILURE
    }

    public PacketLoginResponse(LoginResponse response) {
        args.put("response", response.name());
    }
}