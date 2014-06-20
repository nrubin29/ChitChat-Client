package me.nrubin29.chitchat.client;

import me.nrubin29.chitchat.common.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Notification extends JFrame {

    public static void showNotification(Message message) {
        final Notification notification = new Notification(message);
        Toolkit.getDefaultToolkit().beep();

        Timer timer = new Timer(5 * 1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notification.dispose();
            }
        });
        timer.start();
    }

    private Notification(Message message) {
        JLabel label = new JLabel("(" + message.getChat().getName() + ") " + message.getSender().getName() + ": " + message.getMessage());
        label.setForeground(Color.BLACK);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        add(label);

        JFrame.setDefaultLookAndFeelDecorated(true);
        setUndecorated(true);
        com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.5f);
        setSize(new Dimension(100, label.getFontMetrics(label.getFont()).stringWidth(label.getText()) + 50));
        setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().getLocation());
        setVisible(true);
    }
}