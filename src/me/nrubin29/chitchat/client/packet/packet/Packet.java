package me.nrubin29.chitchat.client.packet.packet;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

public class Packet implements Serializable {

    public final HashMap<String, String> args = new HashMap<String, String>();

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append(getClass().getSimpleName()).append(" ");

        for (Entry<String, String> arg : args.entrySet()) {
            builder.append(arg.getKey()).append(":").append(arg.getValue()).append(" ");
        }

        return builder.toString();
    }
}