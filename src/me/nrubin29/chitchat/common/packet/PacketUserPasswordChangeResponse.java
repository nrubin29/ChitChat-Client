package me.nrubin29.chitchat.common.packet;

public class PacketUserPasswordChangeResponse extends Packet {

    private static final long serialVersionUID = 126223847463937529L;

    public enum PasswordChangeResponse {
        SUCCESS,
        FAILURE
    }

    private final String user, response;

    public PacketUserPasswordChangeResponse(String user, PasswordChangeResponse response) {
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