package me.nrubin29.chitchat.common.packet.packet;

public class PacketRegisterResponse extends Packet {

    private static final long serialVersionUID = 9162231317463057120L;

    public enum RegisterResponse {
        SUCCESS,
        FAILURE
    }

    private final String user;
    private final String response;

    public PacketRegisterResponse(String user, RegisterResponse response) {
        this.user = user;
        this.response = response.name();
    }

    public String getUser() {
        return user;
    }

    public String getResponse() {
        return response;
    }
}