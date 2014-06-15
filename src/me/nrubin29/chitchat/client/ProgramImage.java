package me.nrubin29.chitchat.client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public enum ProgramImage {

    LOGO,
    TRAYLOGO;

    private BufferedImage image;

    private ProgramImage() {
        try {
            this.image = ImageIO.read(getClass().getResource("/res/" + name().toLowerCase() + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }
}