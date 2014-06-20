package me.nrubin29.chitchat.client;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.HashMap;

class SettingsWindow extends JFrame {

    private final JList list;

    private final HashMap<String, JPanel> settingsPanels;
    private JPanel currentPanel;

    SettingsWindow() {
        super("Settings");

        settingsPanels = new HashMap<String, JPanel>();
        settingsPanels.put("Account", new AccountSettingsPanel());

        DefaultListModel model = new DefaultListModel();

        for (String setting : settingsPanels.keySet()) {
            model.addElement(setting);
        }

        list = new JList(model);
        list.setMaximumSize(new Dimension(150, 480));
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Object selected = list.getSelectedValue();

                if (selected == null) return;

                if (currentPanel != null) {
                    remove(currentPanel);
                }

                add(currentPanel = settingsPanels.get(selected.toString()));
                validate();
                repaint();
            }
        });

        add(list);

        JSeparator sep = new JSeparator(SwingConstants.VERTICAL);
        sep.setMaximumSize(new Dimension(10, 480));
        add(sep);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        setSize(480, 480);
        setLocationRelativeTo(null);
    }
}