package me.nrubin29.chitchat.client;

import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import me.nrubin29.chitchat.common.Message;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Notification extends FlowPane {

    public static void showNotification(Message message) {
        if (!Boolean.valueOf(Settings.getInstance().get("notifications"))) return;

        final Notification notification = new Notification(message);

        Stage stage = new Stage();
        Scene scene = new Scene(notification);
        stage.setScene(scene);

        stage.setX(Screen.getPrimary().getVisualBounds().getWidth());
        stage.setY(Screen.getPrimary().getVisualBounds().getHeight());

        if (Boolean.valueOf(Settings.getInstance().get("sound"))) {
            Toolkit.getDefaultToolkit().beep();
        }

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                stage.close();
            }
        }, 0, 5000);
    }

    private Notification(Message message) {
        Text label = new Text("(" + message.getChat().getName() + ") " + message.getSender().getName() + ": " + message.getMessage());
        label.setTextAlignment(TextAlignment.CENTER);
        label.setFont(Font.font(Font.getDefault().getName(), FontWeight.NORMAL, FontPosture.REGULAR, 10));
        getChildren().add(label);

        setOpacity(.5);
        setPrefSize(label.getWrappingWidth() + 50, 100);
    }
}