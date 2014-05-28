package me.nrubin29.chitchat.client.packet.handler;

import me.nrubin29.chitchat.client.packet.packet.Packet;

import java.util.ArrayList;
import java.util.HashMap;

public class PacketHandlerManager {

    private static final PacketHandlerManager instance = new PacketHandlerManager();

    public static PacketHandlerManager getInstance() {
        return instance;
    }

    private final ArrayList<PacketHandler> handlers = new ArrayList<PacketHandler>();

    private PacketHandlerManager() {
        handlers.add(new PacketChatCreateHandler());
        handlers.add(new PacketLoginRequestHandler());
        handlers.add(new PacketLoginResponseHandler());
        handlers.add(new PacketMessageHandler());
    }

    /*
     * PacketMessage chat:Friends user:Noah msg:Hello! when:0
     */
    public void handle(String unparsedPacket) {
        String[] unparsedArgs = unparsedPacket.split(" ");

        HashMap<String, String> args = new HashMap<String, String>();

        for (int i = 1; i < unparsedArgs.length; i++) {
            String[] splitArg = unparsedArgs[i].split(":");
            args.put(splitArg[0], splitArg[1]);
        }

        for (PacketHandler handler : handlers) {
            if (handler.getClass().getName().equals("me.nrubin29.chitchat.client.packet.handler." + unparsedArgs[0] + "Handler")) {
                handler.handle(args);
            }
        }
    }

    public void handle(Packet p) {
        for (PacketHandler handler : handlers) {
            if (handler.getClass().getName().equals("me.nrubin29.chitchat.client.packet.handler." + p.getClass().getSimpleName() + "Handler")) {
                handler.handle(p.args);
            }
        }
    }
}