package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.common.packet.packet.PacketLoginRequest;

public class PacketLoginRequestHandler extends PacketHandler<PacketLoginRequest> {

    public PacketLoginRequestHandler() {
        super(PacketLoginRequest.class);
    }

    @Override
    public void handle(PacketLoginRequest packet) {

    }
}