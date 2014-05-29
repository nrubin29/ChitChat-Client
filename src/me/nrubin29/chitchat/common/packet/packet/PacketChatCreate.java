package me.nrubin29.chitchat.common.packet.packet;

import me.nrubin29.chitchat.common.Chat;

public class PacketChatCreate extends Packet {

    private static final long serialVersionUID = -6536580021206781744L;

    private final String chat;
    private final String users;

    public PacketChatCreate(Chat chat) {
        this(chat.getName(), arrayToString(chat.getUsers()));
    }

    public PacketChatCreate(String chat, String... users) {
        this.chat = chat;
        this.users = join(users);
    }

    public String getChat() {
        return chat;
    }

    public String getUsers() {
        return users;
    }

    private static <T> String[] arrayToString(T[] array) {
        String[] strs = new String[array.length];

        for (int i = 0; i < strs.length; i++) {
            strs[i] = array[i].toString();
        }

        return strs;
    }

    private String join(String[] strs) {
        StringBuilder builder = new StringBuilder();

        for (String str : strs) {
            builder.append(str).append(",");
        }

        return builder.toString().trim();
    }
}