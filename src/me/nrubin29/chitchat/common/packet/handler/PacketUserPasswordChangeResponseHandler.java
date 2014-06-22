package me.nrubin29.chitchat.common.packet.handler;

import me.nrubin29.chitchat.client.AccountSettingsPanel;
import me.nrubin29.chitchat.client.Window;
import me.nrubin29.chitchat.common.packet.packet.PacketUserPasswordChangeResponse;

public class PacketUserPasswordChangeResponseHandler extends PacketHandler<PacketUserPasswordChangeResponse> {

    public PacketUserPasswordChangeResponseHandler() {
        super(PacketUserPasswordChangeResponse.class);
    }

    @Override
    public void handle(PacketUserPasswordChangeResponse packet) {
        Window.getInstance().getMainPanel().getSettingsWindow().<AccountSettingsPanel>getPanel("Account").passwordChangeResponse(packet);
    }
}