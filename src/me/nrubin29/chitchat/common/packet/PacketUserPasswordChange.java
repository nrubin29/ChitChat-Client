package me.nrubin29.chitchat.common.packet;

import me.nrubin29.chitchat.common.AbstractUser;

public class PacketUserPasswordChange extends Packet {

    private static final long serialVersionUID = 1893847617923738573L;

    private final String user, oldPassword, newPassword;

    public PacketUserPasswordChange(AbstractUser user, String oldPassword, String newPassword) {
        this.user = user.getName();
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getUser() {
        return user;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}