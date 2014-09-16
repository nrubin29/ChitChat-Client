package me.nrubin29.chitchat.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserDisplayNameChange;
import me.nrubin29.chitchat.common.packet.PacketUserPasswordChange;
import me.nrubin29.chitchat.common.packet.PacketUserPasswordChangeResponse;

public class AccountSettingsPanel extends VBox {

    private Text user;

    public AccountSettingsPanel(final SettingsStage settings) {
        user = new Text("Username: " + ChatManager.getInstance().getLocalUser().getName() + ". Display Name: " + ChatManager.getInstance().getLocalUser().getDisplayName() + ".");
        getChildren().add(user);

        Button changeDisplayName = new Button("Change Display Name");
        changeDisplayName.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String response = Dialogs.showInputDialog(
                        settings,
                        "Please enter a new display name.",
                        "Change Display Name",
                        "Change Display Name",
                        ChatManager.getInstance().getLocalUser().getDisplayName()
                );

                if (response != null) {
                    ServerConnector.getInstance().sendPacket(new PacketUserDisplayNameChange(ChatManager.getInstance().getLocalUser(), response));
                }
            }
        });
        getChildren().add(changeDisplayName);

        Button changePassword = new Button("Change Password");
        changePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String oldReponse = Dialogs.showInputDialog(
                        settings,
                        "Please enter your old password.",
                        "Enter Old Password",
                        "Enter Old Password"
                );

                if (oldReponse == null) return;

                String newResponse = Dialogs.showInputDialog(
                        settings,
                        "Please enter a new password.",
                        "Enter New Password",
                        "Enter New Password"
                );

                if (newResponse == null) return;

                ServerConnector.getInstance().sendPacket(new PacketUserPasswordChange(ChatManager.getInstance().getLocalUser(), oldReponse, newResponse));
            }
        });
        getChildren().add(changePassword);

        setPrefSize(320, 480);
    }

    public void passwordChangeResponse(PacketUserPasswordChangeResponse response) {
        if (PacketUserPasswordChangeResponse.PasswordChangeResponse.valueOf(response.getResponse()) == PacketUserPasswordChangeResponse.PasswordChangeResponse.SUCCESS) {
            Dialogs.showInformationDialog(Main.getInstance().getStage(), "Password changed successfully.", "Success", "Success");
        } else {
            Dialogs.showInformationDialog(Main.getInstance().getStage(), "Could not change password.", "Failure", "Failure");
        }
    }

    public void displayNameChange() {
        user.setText("Username: " + ChatManager.getInstance().getLocalUser().getName() + ". Display Name: " + ChatManager.getInstance().getLocalUser().getDisplayName() + ".");
    }
}