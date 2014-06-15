package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.packet.PacketChatCreate;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel {

    private final JList list;
    private final DefaultListModel model;

    private Chat currentChat;

    MainPanel() {
        final JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setMaximumSize(new Dimension(150, 480));

        model = new DefaultListModel();

        list = new JList(model);
        list.setMaximumSize(new Dimension(150, 460));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selected = list.getSelectedValue();

                if (selected == null) return;

                if (currentChat != null) {
                    remove(currentChat.getChatPanel());
                }

                currentChat = ChatManager.getInstance().getChat(selected.toString());

                add(currentChat.getChatPanel());

                validate();
                repaint();

                Window.getInstance().setTitle("ChitChat - " + currentChat.getName());
            }
        });
        leftPanel.add(list);

        JSeparator leftSep = new JSeparator(SwingConstants.HORIZONTAL);
        leftSep.setMaximumSize(new Dimension(150 - 5, 10));
        leftPanel.add(leftSep);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setMaximumSize(new Dimension(150, 33)); // 22

        JButton addChat = new JButton("+");
        addChat.setMaximumSize(new Dimension(20, 20));
        addChat.setBorderPainted(false);
        addChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chatName = JOptionPane.showInputDialog(MainPanel.this, "Enter a name for the chat.");

                if (chatName != null) {
                    Chat chat = new Chat(chatName, ChatManager.getInstance().getLocalUser().getName());
                    ChatManager.getInstance().addChat(chat);
                    ServerConnector.getInstance().sendPacket(new PacketChatCreate(chat));
                }
            }
        });
        buttonPanel.add(addChat);

        buttonPanel.add(Box.createHorizontalGlue());

        JButton removeChat = new JButton("-");
        removeChat.setMaximumSize(new Dimension(20, 20));
        removeChat.setBorderPainted(false);
        removeChat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String chatName = list.getSelectedValue().toString();

                if (chatName != null) {
                    if (currentChat != null) {
                        remove(currentChat.getChatPanel());
                        Window.getInstance().setTitle("ChitChat");
                        validate();
                        repaint();
                    }

                    ChatManager.getInstance().removeChat(chatName); // TODO: Send a packet.
                }
            }
        });
        buttonPanel.add(removeChat);

        leftPanel.add(buttonPanel);

        add(leftPanel);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setMaximumSize(new Dimension(10, 480));
        add(sep);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public Chat getCurrentChat() {
        return currentChat;
    }

    public void chatAdded(Chat chat) {
        model.addElement(chat.getName());
    }

    public void chatRemoved(Chat chat) {
        model.removeElement(chat.getName());
    }
}