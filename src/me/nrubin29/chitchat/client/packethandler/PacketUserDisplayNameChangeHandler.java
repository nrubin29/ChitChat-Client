package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.client.AccountSettingsPanel;
import me.nrubin29.chitchat.client.Main;
import me.nrubin29.chitchat.client.User;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserDisplayNameChange;

public class PacketUserDisplayNameChangeHandler extends PacketHandler<PacketUserDisplayNameChange> {

    PacketUserDisplayNameChangeHandler() {
        super(PacketUserDisplayNameChange.class);
    }

    @Override
    public void handle(PacketUserDisplayNameChange packet) {
        User user = ChatManager.getInstance().getUser(packet.getUser());
        user.setDisplayName(packet.getNewDisplayName());

        Main.getInstance().getMainPanel().getSettingsWindow().<AccountSettingsPanel>getPanel("Account").displayNameChange();

        for (Chat chat : ChatManager.getInstance().getChats(user)) {
            chat.getChatPanel().update();
        }
    }
}