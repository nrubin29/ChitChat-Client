package me.nrubin29.chitchat.client;

import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserDisplayNameChange;
import me.nrubin29.chitchat.common.packet.PacketUserPasswordChange;
import me.nrubin29.chitchat.common.packet.PacketUserPasswordChangeResponse;
import org.controlsfx.dialog.Dialogs;

import java.util.Optional;

public class AccountSettingsPanel extends VBox {

    private Text user;

    public AccountSettingsPanel(SettingsWindow settings) {
        user = new Text("Username: " + ChatManager.getInstance().getLocalUser().getName() + ". Display Name: " + ChatManager.getInstance().getLocalUser().getDisplayName() + ".");
        getChildren().add(user);

        Button changeDisplayName = new Button("Change Display Name");
        changeDisplayName.setOnAction(e -> {
            Optional<String> response = Dialogs.create()
                    .owner(settings)
                    .title("Change Display Name")
                    .masthead("Change Display Name")
                    .message("Please enter a new display name.")
                    .showTextInput();

            if (response.isPresent()) {
                ServerConnector.getInstance().sendPacket(new PacketUserDisplayNameChange(ChatManager.getInstance().getLocalUser(), response.get()));
            }
        });
        getChildren().add(changeDisplayName);

        Button changePassword = new Button("Change Password");
        changePassword.setOnAction(e -> {
            Optional<String> oldReponse = Dialogs.create()
                    .owner(settings)
                    .title("Enter Old Password")
                    .masthead("Enter Old Password")
                    .message("Please enter your old password.")
                    .showTextInput();

            if (!oldReponse.isPresent()) return;

            Optional<String> newResponse = Dialogs.create()
                    .owner(settings)
                    .title("Enter New Password")
                    .masthead("Enter New Password")
                    .message("Please enter a new password.")
                    .showTextInput();

            if (!newResponse.isPresent()) return;

            ServerConnector.getInstance().sendPacket(new PacketUserPasswordChange(ChatManager.getInstance().getLocalUser(), oldReponse.get(), newResponse.get()));
        });
        getChildren().add(changePassword);

        setPrefSize(320, 480);
    }

    public void passwordChangeResponse(PacketUserPasswordChangeResponse response) {
        if (PacketUserPasswordChangeResponse.PasswordChangeResponse.valueOf(response.getResponse()) == PacketUserPasswordChangeResponse.PasswordChangeResponse.SUCCESS) {
            Dialogs.create()
                    .owner(Window.getInstance().getStage())
                    .title("Success")
                    .masthead("Success")
                    .message("Password changed successfully.")
                    .showInformation();
        } else {
            Dialogs.create()
                    .owner(Window.getInstance().getStage())
                    .title("Failure")
                    .masthead("Failure")
                    .message("Could not change password.")
                    .showInformation();
        }
    }

    public void displayNameChange() {
        user.setText("Username: " + ChatManager.getInstance().getLocalUser().getName() + ". Display Name: " + ChatManager.getInstance().getLocalUser().getDisplayName() + ".");
    }
}