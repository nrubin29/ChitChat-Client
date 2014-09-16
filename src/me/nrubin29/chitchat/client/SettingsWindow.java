package me.nrubin29.chitchat.client;

import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.util.Collections;
import java.util.HashMap;

public class SettingsWindow extends HBox {

    private final HashMap<String, Pane> settingsPanels;
    private Pane currentPanel;

    SettingsWindow() {
        settingsPanels = new HashMap<>();
        settingsPanels.put("General", new GeneralSettingsPanel());
        settingsPanels.put("Account", new AccountSettingsPanel(this));

        ListView<String> list = new ListView<>(FXCollections.observableArrayList(settingsPanels.keySet()));
        list.getItems().sort(Collections.reverseOrder());
        list.setPrefSize(150, 480);
        list.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            if (currentPanel != null) {
                getChildren().remove(currentPanel);
            }

            getChildren().add(currentPanel = settingsPanels.get(newValue));
        });

        getChildren().addAll(list, JFXUtils.region(5, 0));

        setPrefSize(480, 480);
    }

    public <E extends Pane> E getPanel(String name) {
        return (E) settingsPanels.get(name);
    }
}