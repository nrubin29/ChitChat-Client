package me.nrubin29.chitchat.client.packet.handler;

import me.nrubin29.chitchat.client.ChatManager;

import java.util.Date;
import java.util.HashMap;

public class PacketMessageHandler extends PacketHandler {

    @Override
    public void handle(HashMap<String, String> args) {
        String chat = args.get("chat");
        String sender = args.get("sender");
        String msg = args.get("msg").replace("%20", " ");
        long when = Long.valueOf(args.get("when"));

        ChatManager.getInstance().getChat(chat).addMessage(sender, chat, msg, new Date(when));
    }
}