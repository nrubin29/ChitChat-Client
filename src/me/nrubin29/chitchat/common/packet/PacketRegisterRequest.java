package me.nrubin29.chitchat.common.packet;

public class PacketRegisterRequest extends Packet {

    private static final long serialVersionUID = 619450630159893196L;

    private final String user;
    private final String password;

    public PacketRegisterRequest(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}