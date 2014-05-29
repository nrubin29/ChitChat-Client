package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.common.packet.packet.Packet;

abstract class PacketHandler<T extends Packet> {

    private final Class<? extends Packet> packetClass;

    PacketHandler(Class<? extends Packet> packetClass) {
        this.packetClass = packetClass;
    }

    public Class<? extends Packet> getPacketClass() {
        return packetClass;
    }

    public abstract void handle(T packet);
}