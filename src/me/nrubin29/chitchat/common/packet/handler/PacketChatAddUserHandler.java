package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.client.Window;
import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketChatAddUser;

public class PacketChatAddUserHandler extends PacketHandler<PacketChatAddUser> {

    public PacketChatAddUserHandler() {
        super(PacketChatAddUser.class);
    }

    @Override
    public void handle(PacketChatAddUser packet) {
        AbstractUser user;
        ChatManager.getInstance().getChat(packet.getChat()).addUser(user = ChatManager.getInstance().getUser(packet.getNewUser()));
        Window.getInstance().getMainPanel().getCurrentChat().getChatPanel().userAdded(user);
    }
}