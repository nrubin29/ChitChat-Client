package me.nrubin29.chitchat.client;

import javafx.scene.image.Image;

public enum ProgramImage {

    LOGO,
    TRAYLOGO;

    private Image image;

    private ProgramImage() {
        this.image = new Image(getClass().getResourceAsStream("/res/" + name().toLowerCase() + ".png"));
    }

    public Image getImage() {
        return image;
    }
}