package me.nrubin29.chitchat.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.nrubin29.chitchat.common.AbstractUser;
import me.nrubin29.chitchat.common.ChatManager;
import me.nrubin29.chitchat.common.packet.PacketUserStatusChange;

import static me.nrubin29.chitchat.client.JFXUtils.runAndWait;

public class Main extends Application {

    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    private Stage stage;
    private Scene scene;

    private LoginPanel loginPanel;
    private MainPanel mainPanel;
    private boolean login;

    public void start(final Stage stage) {
        instance = this;

        this.stage = stage;

        stage.setTitle("ChitChat - Login");

        showLoginPanel();

        stage.setWidth(640);
        stage.setMinWidth(640);
        stage.setHeight(480);
        stage.setMinHeight(480);
        stage.getIcons().add(ProgramImage.TRAYLOGO.getImage());
        stage.show();

        stage.setOnHiding(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                if (!login) {
                    ServerConnector.getInstance().sendPacket(new PacketUserStatusChange(ChatManager.getInstance().getLocalUser(), AbstractUser.UserStatus.AWAY));
                }
            }
        });

        stage.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                if (!login) {
                    ServerConnector.getInstance().sendPacket(new PacketUserStatusChange(ChatManager.getInstance().getLocalUser(), AbstractUser.UserStatus.ONLINE));
                }
            }
        });
    }

    public void showLoginPanel() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (loginPanel == null) {
                    loginPanel = new LoginPanel();
                }

                Main.this.replaceSceneContent(loginPanel);

                login = true;
                stage.setResizable(false);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        System.exit(0);
                    }
                });

                stage.show();
            }
        });
    }

    public void showMainPanel() {
        runAndWait(new Runnable() {
            @Override
            public void run() {
                if (mainPanel == null) {
                    mainPanel = new MainPanel();
                }

                Main.this.replaceSceneContent(mainPanel);

                login = false;
                stage.setResizable(true);
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    @Override
                    public void handle(WindowEvent e) {
                        stage.hide();
                    }
                });

                stage.show();
            }
        });
    }

    private void replaceSceneContent(final Parent parent) {
        runAndWait(new Runnable() {
            @Override
            public void run() {
                if (scene == null) {
                    scene = new Scene(parent);
                } else {
                    scene.setRoot(parent);
                }

                stage.setScene(scene);
            }
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
        runAndWait(new Runnable() {
            @Override
            public void run() {
                stage.setTitle(title);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}