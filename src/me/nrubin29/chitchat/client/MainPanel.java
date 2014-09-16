package me.nrubin29.chitchat.client;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketChatCreate;
import me.nrubin29.chitchat.common.packet.PacketChatRemoveUser;
import org.controlsfx.dialog.Dialogs;

import java.util.Optional;

import static me.nrubin29.chitchat.client.JFXUtils.columnConstraints;
import static me.nrubin29.chitchat.client.JFXUtils.region;

public class MainPanel extends GridPane {

    private final ListView<String> list;

    private Stage settingsStage;
    private final SettingsWindow settingsWindow;

    private Chat currentChat;

    MainPanel() {
        Window.getInstance().setTitle("ChitChat");

        final VBox leftPanel = new VBox();
        leftPanel.setPrefSize(150, 480);

        settingsStage = new Stage();
        settingsStage.setTitle("Settings");
        settingsStage.setScene(new Scene(settingsWindow = new SettingsWindow()));

        list = new ListView<>();
        list.setPrefSize(150, 460);

        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (currentChat != null) {
                getChildren().remove(currentChat.getChatPanel());
            }

            currentChat = ChatManager.getInstance().getChat(newValue);

            add(currentChat.getChatPanel(), 2, 0);

            Window.getInstance().setTitle("ChitChat - " + currentChat.getName());
        });
        leftPanel.getChildren().add(list);

        HBox buttonPanel = new HBox();
        buttonPanel.setAlignment(Pos.CENTER);
        buttonPanel.setPrefSize(150, 20);

        Button addChat = new Button("+");
        addChat.setOnAction(e -> {
            Optional<String> response = Dialogs.create()
                    .owner(Window.getInstance().getStage())
                    .title("Enter Name")
                    .masthead("Enter Name")
                    .message("Enter a new name for the chat.")
                    .showTextInput();

            if (response.isPresent()) {
                Chat chat = new Chat(response.get(), ChatManager.getInstance().getLocalUser().getName());
                ChatManager.getInstance().addChat(chat);
                ServerConnector.getInstance().sendPacket(new PacketChatCreate(chat));
            }
        });

        Button removeChat = new Button("-");
        removeChat.setOnAction(e -> {
            if (list.getSelectionModel().getSelectedItem() != null) {
                String chatName = list.getSelectionModel().getSelectedItem();

                if (currentChat != null) {
                    getChildren().remove(currentChat.getChatPanel());
                    Window.getInstance().setTitle("ChitChat");
                }

                ChatManager.getInstance().removeChat(chatName);
                ServerConnector.getInstance().sendPacket(new PacketChatRemoveUser(chatName, ChatManager.getInstance().getLocalUser().getName()));
            }
        });

        Button settings = new Button("S");
        settings.setOnAction(e -> settingsStage.show());

        Button logout = new Button("x");
        logout.setOnAction(e -> {
            Window.getInstance().showLoginPanel();
            ChatManager.getInstance().clear();
        });

        buttonPanel.getChildren().addAll(addChat, region(5, 0), removeChat, region(5, 0), settings, region(5, 0), logout);

        leftPanel.getChildren().addAll(region(0, 5), buttonPanel);

//        getChildren().addAll(leftPanel, region(5, 0));

        addRow(0, leftPanel, region(5, 0));
        getColumnConstraints().addAll(columnConstraints(29), columnConstraints(2), columnConstraints(69));
    }

    public SettingsWindow getSettingsWindow() {
        return settingsWindow;
    }

    public void chatAdded(Chat chat) {
        Platform.runLater(() -> list.getItems().add(chat.getName()));
    }

    public void chatRemoved(Chat chat) {
        Platform.runLater(() -> list.getItems().remove(chat.getName()));
    }
}