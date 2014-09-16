package me.nrubin29.chitchat.client;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketChatCreate;
import me.nrubin29.chitchat.common.packet.PacketChatRemoveUser;

import static me.nrubin29.chitchat.client.JFXUtils.columnConstraints;
import static me.nrubin29.chitchat.client.JFXUtils.region;

public class MainPanel extends GridPane {

    private final ListView<String> list;

    private final SettingsStage settings;

    private Chat currentChat;

    MainPanel() {
        Main.getInstance().setTitle("ChitChat");

        final VBox leftPanel = new VBox();
        leftPanel.setPrefSize(150, 480);

        settings = new SettingsStage();

        list = new ListView<>();
        list.setPrefSize(150, 460);

        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (currentChat != null) {
                    MainPanel.this.getChildren().remove(currentChat.getChatPanel());
                }

                currentChat = ChatManager.getInstance().getChat(newValue);

                MainPanel.this.add(currentChat.getChatPanel(), 2, 0);

                Main.getInstance().setTitle("ChitChat - " + currentChat.getName());
            }
        });
        leftPanel.getChildren().add(list);

        HBox buttonPanel = new HBox();
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPrefSize(150, 20);

        Button addChat = new Button("+");
        addChat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String response = Dialogs.showInputDialog(
                        Main.getInstance().getStage(),
                        "Enter a new name for the chat.",
                        "Enter Name",
                        "Enter Name",
                        currentChat.getName()
                );

                if (response != null) {
                    Chat chat = new Chat(response, ChatManager.getInstance().getLocalUser().getName());
                    ChatManager.getInstance().addChat(chat);
                    ServerConnector.getInstance().sendPacket(new PacketChatCreate(chat));
                }
            }
        });

        Button removeChat = new Button("-");
        removeChat.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (list.getSelectionModel().getSelectedItem() != null) {
                    String chatName = list.getSelectionModel().getSelectedItem();

                    if (currentChat != null) {
                        MainPanel.this.getChildren().remove(currentChat.getChatPanel());
                        Main.getInstance().setTitle("ChitChat");
                    }

                    ChatManager.getInstance().removeChat(chatName);
                    ServerConnector.getInstance().sendPacket(new PacketChatRemoveUser(chatName, ChatManager.getInstance().getLocalUser().getName()));
                }
            }
        });

        Button settings = new Button("S");
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                MainPanel.this.settings.show();
            }
        });

        Button logout = new Button("x");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Main.getInstance().showLoginPanel();
                ChatManager.getInstance().clear();
            }
        });

        buttonPanel.getChildren().addAll(addChat, region(5, 0), removeChat, region(5, 0), settings, region(5, 0), logout);

        leftPanel.getChildren().addAll(region(0, 5), buttonPanel);

//        getChildren().addAll(leftPanel, region(5, 0));

        addRow(0, leftPanel, region(5, 0));
        getColumnConstraints().addAll(columnConstraints(29), columnConstraints(2), columnConstraints(69));
    }

    public SettingsStage getSettingsWindow() {
        return settings;
    }

    public void chatAdded(final Chat chat) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                list.getItems().add(chat.getName());
            }
        });
    }

    public void chatRemoved(final Chat chat) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                list.getItems().remove(chat.getName());
            }
        });
    }
}