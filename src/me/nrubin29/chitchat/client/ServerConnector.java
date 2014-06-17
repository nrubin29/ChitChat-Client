package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.handler.PacketHandlerManager;
import me.nrubin29.chitchat.common.packet.packet.Packet;

import javax.crypto.Cipher;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class ServerConnector {

    private ServerConnector() {
    }

    private static final ServerConnector instance = new ServerConnector();

    public static ServerConnector getInstance() {
        return instance;
    }

    private String ip;
    private Socket socket;
    private SecretKey key;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    void connect() {
        try {
            socket = new Socket(ip.split(":")[0], Integer.valueOf(ip.split(":")[1]));

            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            key = (SecretKey) inputStream.readObject();

            System.out.println("Got key: " + key);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Packet packet = (Packet) inputStream.readObject();
                            System.out.println("Received packet: " + packet);
                            handlePacket(packet);
                        } catch (EOFException e) {
                            System.out.println("Lost connection to server.");

                            try {
                                socket.close();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            socket = null;

                            Window.getInstance().swapPanels();
                            ChatManager.getInstance().clear();

                            break;
                        } catch (SocketException e) {
                            System.out.println("Socket closed.");
                            socket = null;
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            socket.close();
            socket = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendPacket(Packet packet) {
        if (socket == null) {
            connect();
        }

        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, key);
            outputStream.writeObject(new SealedObject(packet, c));
            System.out.println("Sent packet: " + packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePacket(Packet packet) {
        PacketHandlerManager.getInstance().handle(packet);
    }

    public void setIP(String ip) {
        this.ip = ip;
    }
}