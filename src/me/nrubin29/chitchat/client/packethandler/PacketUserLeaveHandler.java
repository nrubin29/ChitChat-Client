package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserLeave;

public class PacketUserLeaveHandler extends PacketHandler<PacketUserLeave> {

    PacketUserLeaveHandler() {
        super(PacketUserLeave.class);
    }

    @Override
    public void handle(PacketUserLeave packet) {
        ChatManager.getInstance().removeUser(ChatManager.getInstance().getUser(packet.getUser()));
    }
}