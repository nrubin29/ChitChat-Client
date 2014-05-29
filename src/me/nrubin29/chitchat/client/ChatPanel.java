package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.Message;
import me.nrubin29.chitchat.common.packet.packet.PacketChatAddUser;
import me.nrubin29.chitchat.common.packet.packet.PacketMessage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatPanel extends JPanel {

    private final JBubblePane bubbleArea;
    private final JTextArea textArea;
    private final JTextField input;

    public ChatPanel(final Chat chat) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));

        bubbleArea = new JBubblePane(chat);
        bubbleArea.setMaximumSize(new Dimension(490, 25));
        userPanel.add(bubbleArea);

        JButton add = new JButton("+");
        add.setMaximumSize(new Dimension(15, 15));
        add.setBorderPainted(false);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = JOptionPane.showInputDialog(ChatPanel.this, "Enter a user to add to the chat.");

                if (user != null) {
                    ServerConnector.getInstance().sendPacket(new PacketChatAddUser(chat, ChatManager.getInstance().getUser(user)));
                }
            }
        });
        userPanel.add(add);

        add(userPanel);

        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setMaximumSize(new Dimension(490, 20));
        add(sep);

        textArea = new JTextArea();
        textArea.setMaximumSize(new Dimension(490, 460 - 35));
        textArea.setEditable(false);
        add(textArea);

        JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
        sep1.setMaximumSize(new Dimension(490, 20));
        add(sep1);

        input = new JTextField();
        input.setMaximumSize(new Dimension(490, 20));
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    ServerConnector.getInstance().sendPacket(new PacketMessage(chat, ChatManager.getInstance().getLocalUser(), input.getText()));
                    input.setText("");
                }
            }
        });
        add(input);

        add(Box.createVerticalStrut(3));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setMaximumSize(new Dimension(490 - 5, 480));
    }

    public void messageReceived(Message message) {
        textArea.append("[" + message.getWhen() + "] " + message.getSender() + ": " + message.getMessage() + "\n");
    }

    public void userAdded(AbstractUser user) {
        bubbleArea.addBubble(user.getName());
    }

    public void userRemoved(AbstractUser user) {
        bubbleArea.removeBubble(user.getName());
    }
}