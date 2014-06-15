package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketMessage;

public class PacketMessageHandler extends PacketHandler<PacketMessage> {

    public PacketMessageHandler() {
        super(PacketMessage.class);
    }

    @Override
    public void handle(PacketMessage packet) {
        ChatManager.getInstance().getChat(packet.getChat()).addMessage(packet.getSender(), packet.getChat(), packet.getMessage().replaceAll("%20", " "), packet.getWhen());
    }
}