package me.nrubin29.chitchat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserStatusChange;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static me.nrubin29.chitchat.client.JFXUtils.runAndWait;

public class Window extends Application {

    private static Window instance;

    public static Window getInstance() {
        return instance;
    }

    private Stage stage;
    private Scene scene;

    private LoginPanel loginPanel;
    private MainPanel mainPanel;
    private boolean login;

    private TrayIcon trayIcon;

    public void start(final Stage stage) {
        instance = this;

        this.stage = stage;

        stage.setTitle("ChitChat - Login");

        showLoginPanel();

        stage.setWidth(640);
        stage.setMinWidth(640);
        stage.setHeight(480);
        stage.setMinHeight(480);
        stage.show();

        stage.setOnHiding(windowEvent -> {
            if (!login) {
                ServerConnector.getInstance().sendPacket(new PacketUserStatusChange(ChatManager.getInstance().getLocalUser(), AbstractUser.UserStatus.AWAY));
            }
        });

        stage.setOnShowing(windowEvent -> {
            if (!login) {
                ServerConnector.getInstance().sendPacket(new PacketUserStatusChange(ChatManager.getInstance().getLocalUser(), AbstractUser.UserStatus.ONLINE));
            }
        });

        trayIcon = new TrayIcon(ProgramImage.TRAYLOGO.getImage(), "ChitChat");
        trayIcon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (!login && !stage.isShowing()) {
                    stage.show();
                }
            }
        });
    }

    public void showLoginPanel() {
        Platform.runLater(() -> {
            if (loginPanel == null) {
                loginPanel = new LoginPanel();
            }

            replaceSceneContent(loginPanel);

            login = true;
            stage.setResizable(false);
            stage.setOnCloseRequest(e -> System.exit(0));
            SystemTray.getSystemTray().remove(trayIcon);

            stage.show();
        });
    }

    public void showMainPanel() {
        runAndWait(() -> {
            if (mainPanel == null) {
                mainPanel = new MainPanel();
            }

            replaceSceneContent(mainPanel);

            login = false;
            stage.setResizable(true);
            stage.setOnCloseRequest(e -> stage.hide());

            try {
                SystemTray.getSystemTray().add(trayIcon);
            } catch (Exception e) {
                e.printStackTrace();
            }

            stage.show();
        });
    }

    private void replaceSceneContent(Parent parent) {
        runAndWait(() -> {
            if (scene == null) {
                scene = new Scene(parent);
            } else {
                scene.setRoot(parent);
            }

            stage.setScene(scene);
        });
    }

    public LoginPanel getLoginPanel() {
        return loginPanel;
    }

    public MainPanel getMainPanel() {
        return mainPanel;
    }

    public Stage getStage() {
        return stage;
    }

    public boolean isVisible() {
        return stage.isShowing();
    }

    public void setTitle(final String title) {
        runAndWait(() -> stage.setTitle(title));
    }

    public static void main(String[] args) {
        launch(args);
    }
}