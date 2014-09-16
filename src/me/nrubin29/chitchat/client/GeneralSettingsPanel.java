package me.nrubin29.chitchat.client;

import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;

public class GeneralSettingsPanel extends VBox {

    public GeneralSettingsPanel() {
        final CheckBox notifications = new CheckBox("Enable Notifications");
        notifications.setSelected(Boolean.valueOf(Settings.getInstance().get("notifications")));
        notifications.setOnAction(e -> Settings.getInstance().set("notifications", notifications.isSelected()));
        getChildren().add(notifications);

        final CheckBox sound = new CheckBox("Enable Notification Sound");
        sound.setSelected(Boolean.valueOf(Settings.getInstance().get("notifications")));
        sound.setOnAction(e -> Settings.getInstance().set("sound", sound.isSelected()));
        getChildren().add(sound);

        setPrefSize(320, 480);
    }
}