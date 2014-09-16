package me.nrubin29.chitchat.client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

public enum ProgramImage {

    LOGO,
    TRAYLOGO;

    private URL url;
    private BufferedImage image;

    private ProgramImage() {
        this.url = getClass().getResource("/res/" + name().toLowerCase() + ".png");

        try {
            this.image = ImageIO.read(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public URL getURL() {
        return url;
    }

    public BufferedImage getImage() {
        return image;
    }
}