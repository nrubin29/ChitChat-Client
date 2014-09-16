package me.nrubin29.chitchat.common.packet;

public class PacketLoginResponse extends Packet {

    private static final long serialVersionUID = 2259015235455303614L;

    public enum LoginResponse {
        SUCCESS,
        FAILURE
    }

    private final String user;
    private final String response;

    public PacketLoginResponse(String user, LoginResponse response) {
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