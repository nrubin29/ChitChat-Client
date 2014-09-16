package me.nrubin29.chitchat.client;

import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;

import static me.nrubin29.chitchat.client.JFXUtils.region;
import static me.nrubin29.chitchat.client.JFXUtils.runAndWait;

class BubblePane extends HBox {

    private final Chat chat;

    public BubblePane(Chat chat) {
        this.chat = chat;
    }

    public void update() {
        runAndWait(() -> {
            getChildren().clear();

            for (String user : chat.getUsers()) {
                HBox border = new HBox();

                Text label = new Text(user);
                border.getChildren().add(label);

                getChildren().add(border);

                if (ChatManager.getInstance().getUser(user) != null) {
                    User u = ChatManager.getInstance().getUser(user);
                    label.setText(u.getDisplayName());

                    if (u.getUserStatus() == AbstractUser.UserStatus.ONLINE) {
                        border.setStyle("-fx-border-color: black; -fx-background-color: green;");
                    } else if (u.getUserStatus() == AbstractUser.UserStatus.AWAY) {
                        border.setStyle("-fx-border-color: black; -fx-background-color: yellow;");
                    }
                } else {
                    border.setStyle("-fx-border-color: black; -fx-background-color: red;");
                }

                getChildren().add(region(5, 0));
            }
        });
    }
}