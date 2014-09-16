package me.nrubin29.chitchat.client;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import me.nrubin29.chitchat.common.Chat;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.Message;
import me.nrubin29.chitchat.common.packet.PacketChatAddUser;
import me.nrubin29.chitchat.common.packet.PacketMessage;
import org.controlsfx.dialog.Dialogs;

import java.util.Calendar;
import java.util.Optional;

import static me.nrubin29.chitchat.client.JFXUtils.rowConstraints;

public class ChatPanel extends GridPane {

    private final BubblePane bubbleArea;
    private final TextArea textArea;
    private final TextField input;

    public ChatPanel(final Chat chat) {
        BorderPane userPanel = new BorderPane();

        bubbleArea = new BubblePane(chat);

        Button add = new Button("+");
        add.setOnAction(e -> {
            Optional<String> response = Dialogs.create()
                    .owner(Window.getInstance().getStage())
                    .title("Enter User")
                    .masthead("Enter User")
                    .message("Enter the name of the user to add to the chat.")
                    .showTextInput();

            if (response.isPresent()) {
                ServerConnector.getInstance().sendPacket(new PacketChatAddUser(chat, response.get()));
            }
        });

        userPanel.setLeft(bubbleArea);
        userPanel.setRight(add);

        textArea = new TextArea();
        textArea.setEditable(false);

        input = new TextField();
        input.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER && !input.getText().trim().equals("")) {
                ServerConnector.getInstance().sendPacket(new PacketMessage(chat, ChatManager.getInstance().getLocalUser(), input.getText()));
                input.setText("");
            }
        });

        addColumn(0, userPanel, JFXUtils.region(0, 5), textArea, /* JFXUtils.region(0, 5), */ input);
        getRowConstraints().addAll(rowConstraints(10), rowConstraints(2), rowConstraints(78), /* rowConstraints(2), */ rowConstraints(10));
    }

    public void messageReceived(Message message) {
        Platform.runLater(() -> {
            String time = "";
            time += message.getWhen().get(Calendar.HOUR_OF_DAY) + ":";
            time += message.getWhen().get(Calendar.MINUTE) + " ";
            time += message.getWhen().get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";

            textArea.appendText("[" + time + "] " + message.getSender() + ": " + message.getMessage() + "\n");
        });
    }

    public void update() {
        bubbleArea.update();
    }
}