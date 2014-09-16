package me.nrubin29.chitchat.client;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class Settings {

    private static Settings instance = new Settings();

    public static Settings getInstance() {
        return instance;
    }

    private File file;
    private final HashMap<String, String> contents = new HashMap<>();

    private Settings() {
        try {
            file = getFile("settings.config", false);

            if (!file.exists()) {
                file.createNewFile();

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                    writer.write("notifications: true");
                    writer.newLine();
                    writer.write("sound: true");
                }
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while (reader.ready()) {
                    String currentLine = reader.readLine();
                    contents.put(currentLine.substring(0, currentLine.indexOf(":")), currentLine.substring(currentLine.indexOf(":") + 2).replaceAll("%20", " "));
                }
            }
        } catch (Exception ignored) {
        }
    }

    public final void save() {
        try {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Entry<String, String> entry : contents.entrySet()) {
                    writer.write(entry.getKey() + ": " + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (Exception ignored) {
        }
    }

    public final String get(String key) {
        return contents.get(key);
    }

    public final void set(String key, Object value) {
        contents.put(key, String.valueOf(value));
        save();
    }

    public File getRootFolder() {
        String homedir = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath();
        String osname = System.getProperty("os.name").toLowerCase();
        File rootFolder;

        if (osname.startsWith("mac")) rootFolder = new File(homedir + "/Library/Application Support/ChitChat");
        else if (osname.startsWith("linux")) rootFolder = new File(homedir + "/.ChitChat/");
        else if (osname.startsWith("win")) rootFolder = new File(System.getenv("APPDATA") + "\\.ChitChat\\");
        else throw new RuntimeException("Unsupported OS: " + osname);

        if (!rootFolder.exists()) {

            boolean success = false;

            try {
                success = rootFolder.mkdir();
            } catch (Exception e) {
                System.out.println("Could not create folder.");
            }

            if (!success) System.out.println("Could not create folder.");
        }

        return rootFolder;
    }

    public File getFile(String name, boolean createIfNotExists) {
        File f = new File(getRootFolder(), name);

        if (!f.exists() && createIfNotExists) {
            boolean s = false;

            try {
                s = f.createNewFile();
            } catch (Exception e) {
                System.out.println("Could not create file.");
            }

            if (!s) System.out.println("Could not create file.");
        }

        return f;
    }
}