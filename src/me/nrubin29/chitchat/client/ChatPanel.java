package me.nrubin29.chitchat.client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

public class ChatPanel extends JPanel {

    private final JList list;
    private final DefaultListModel model;
    private JTextArea currentTextArea;
    private final JTextField input;
    private JSeparator rightSep;

    protected ChatPanel() {
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setMaximumSize(new Dimension(490 - 5, 480));

        input = new JTextField();
        input.setMaximumSize(new Dimension(490, 20));

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setMaximumSize(new Dimension(150 - 5, 480));

        model = new DefaultListModel();

        list = new JList(model);
        list.setMaximumSize(new Dimension(150, 460));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Chat newChat = ChatManager.getInstance().getChat(list.getSelectedValue().toString());
                if (currentTextArea != null) {
                    rightPanel.remove(currentTextArea);
                }

                if (rightSep == null) {
                    rightSep = new JSeparator(SwingConstants.HORIZONTAL);
                    rightSep.setMaximumSize(new Dimension(490 - 5, 10));
                }

                rightPanel.remove(input);
                rightPanel.remove(rightSep);
                rightPanel.add(currentTextArea = newChat.getTextArea());
                rightPanel.add(rightSep);
                rightPanel.add(input);

                rightPanel.validate();
                validate();

                rightPanel.repaint();
                repaint();

                Window.getInstance().setTitle("ChitChat - " + newChat.getName());
            }
        });
        list.setSelectedIndex(0);
        leftPanel.add(list);

        JSeparator leftSep = new JSeparator(SwingConstants.HORIZONTAL);
        leftSep.setMaximumSize(new Dimension(150 - 5, 10));
        leftPanel.add(leftSep);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setMaximumSize(new Dimension(150, 22));

        JButton addChat = new JButton("+");
        addChat.setMaximumSize(new Dimension(20, 20));
        addChat.setBorderPainted(false);
        buttonPanel.add(addChat);

        JButton removeChat = new JButton("-");
        removeChat.setMaximumSize(new Dimension(20, 20));
        removeChat.setBorderPainted(false);
        buttonPanel.add(removeChat);

        leftPanel.add(buttonPanel);

        add(leftPanel);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setMaximumSize(new Dimension(10, 480));
        add(sep);

        add(rightPanel);

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    }

    public void messageReceived(Message message) {
        message.getChat().getTextArea().append("[" + message.getWhen() + "] " + message.getSender() + ": " + message.getMessage());
    }

    public void chatAdded(Chat chat) {
        model.addElement(chat.getName());
    }

    public void chatRemoved(Chat chat) {
        model.removeElement(chat.getName());
    }

    public void userAdded(User user) {

    }

    public void userRemoved(User user) {

    }
}