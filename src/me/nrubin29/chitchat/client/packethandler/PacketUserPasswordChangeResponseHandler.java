package me.nrubin29.chitchat.client.packethandler;

import me.nrubin29.chitchat.client.AccountSettingsPanel;
import me.nrubin29.chitchat.client.Main;
import me.nrubin29.chitchat.common.packet.PacketUserPasswordChangeResponse;

public class PacketUserPasswordChangeResponseHandler extends PacketHandler<PacketUserPasswordChangeResponse> {

    public PacketUserPasswordChangeResponseHandler() {
        super(PacketUserPasswordChangeResponse.class);
    }

    @Override
    public void handle(PacketUserPasswordChangeResponse packet) {
        Main.getInstance().getMainPanel().getSettingsWindow().<AccountSettingsPanel>getPanel("Account").passwordChangeResponse(packet);
    }
}