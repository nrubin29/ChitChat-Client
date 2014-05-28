package me.nrubin29.chitchat.client.packet.packet;

class PacketChatCreate extends Packet {

    public PacketChatCreate(String chat, String... users) {
        args.put("chat", chat);
        args.put("users", join(users));
    }

    private String join(String[] strs) {
        StringBuilder builder = new StringBuilder();

        for (String str : strs) {
            builder.append(str).append(",");
        }

        return builder.toString().trim();
    }
}