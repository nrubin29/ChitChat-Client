package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.Message;
import me.nrubin29.chitchat.common.packet.packet.PacketChatAddUser;
import me.nrubin29.chitchat.common.packet.packet.PacketMessage;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.util.Calendar;

public class ChatPanel extends JPanel {

    private final JBubblePane bubbleArea;
    private final JTextPane textArea;
    private final JTextField input;

    public ChatPanel(final Chat chat) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.X_AXIS));

        bubbleArea = new JBubblePane(chat);
        bubbleArea.setMaximumSize(new Dimension(490, 25));
        userPanel.add(bubbleArea);

        JLabel add = new JLabel("+");
        add.setMaximumSize(new Dimension(15, 15));
        add.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String user = JOptionPane.showInputDialog(ChatPanel.this, "Enter a user to add to the chat.");

                if (user != null) {
                    ServerConnector.getInstance().sendPacket(new PacketChatAddUser(chat, user));
                }
            }
        });
        userPanel.add(add);

        add(userPanel);

        JSeparator sep = new JSeparator(SwingConstants.HORIZONTAL);
        sep.setMaximumSize(new Dimension(490, 20));
        add(sep);

        textArea = new JTextPane();
        textArea.setMaximumSize(new Dimension(490, 460 - 35));
        textArea.setEditable(false);
        textArea.setContentType("text/html");
        textArea.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, true);
        textArea.addHyperlinkListener(new HyperlinkListener() {
            @Override
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                    try {
                        Desktop.getDesktop().browse(new URI(e.getDescription()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JScrollPane textAreaScroll = new JScrollPane(textArea);
        textAreaScroll.setMaximumSize(new Dimension(490, 460 - 35));
        textAreaScroll.setBorder(null);
        add(textAreaScroll);

        JSeparator sep1 = new JSeparator(SwingConstants.HORIZONTAL);
        sep1.setMaximumSize(new Dimension(490, 20));
        add(sep1);

        input = new JTextField();
        input.setMaximumSize(new Dimension(490, 20));
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !input.getText().trim().equals("")) {
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
        String time = "";
        time += message.getWhen().get(Calendar.HOUR_OF_DAY) + ":";
        time += message.getWhen().get(Calendar.MINUTE) + " ";
        time += message.getWhen().get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

        try {
            HTMLDocument doc = (HTMLDocument) textArea.getDocument();
            HTMLEditorKit editorKit = (HTMLEditorKit) textArea.getEditorKit();
            editorKit.insertHTML(doc, doc.getLength(), "[" + time + "] " + message.getSender() + ": " + message.getMessage() + "\n", 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        bubbleArea.update();
    }
}