package me.nrubin29.chitchat.client.packet.packet;

class PacketMessage extends Packet {

    public PacketMessage(String chat, String sender, String msg) {
        args.put("chat", chat);
        args.put("sender", sender);
        args.put("msg", msg.replaceAll(" ", "%20"));
        args.put("when", String.valueOf(System.currentTimeMillis()));
    }
}