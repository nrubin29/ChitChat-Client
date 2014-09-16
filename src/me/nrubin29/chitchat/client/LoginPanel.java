package me.nrubin29.chitchat.client;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import me.nrubin29.chitchat.common.packet.PacketLoginRequest;
import me.nrubin29.chitchat.common.packet.PacketRegisterRequest;

import static me.nrubin29.chitchat.client.JFXUtils.region;
import static me.nrubin29.chitchat.client.JFXUtils.spacer;

public class LoginPanel extends HBox {

    LoginPanel() {
        javafx.scene.image.ImageView logo = new ImageView(ProgramImage.LOGO.getURL().toString());
        final TextField username = new TextField();
        final PasswordField password = new PasswordField();
        final TextField ip = new TextField();

        HBox buttonPanel = new HBox();
        buttonPanel.setAlignment(Pos.CENTER);

        Button login = new Button("Login");
        login.setOnAction(e -> {
            ServerConnector.getInstance().setIP(ip.getText());
            ServerConnector.getInstance().sendPacket(new PacketLoginRequest(username.getText(), password.getText()));
        });

        Button register = new Button("Register");
        register.setOnAction(e -> {
            ServerConnector.getInstance().setIP(ip.getText());
            ServerConnector.getInstance().sendPacket(new PacketRegisterRequest(username.getText(), password.getText()));
        });

        buttonPanel.getChildren().addAll(login, region(5, 0), register);

        VBox panel = new VBox();
        panel.setMaxSize(400, 400);
        panel.setAlignment(Pos.CENTER);
        panel.getChildren().addAll(region(0, 50), logo, region(0, 5), username, region(0, 5), password, region(0, 5), ip, region(0, 5), buttonPanel);

        getChildren().addAll(spacer(true), panel, spacer(true));
    }
}