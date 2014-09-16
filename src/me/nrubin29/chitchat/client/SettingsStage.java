package me.nrubin29.chitchat.client;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.HashMap;

public class SettingsStage extends Stage {

    private final HashMap<String, Pane> settingsPanels;
    private Pane currentPanel;

    SettingsStage() {
        final HBox box = new HBox();

        settingsPanels = new HashMap<>();
        settingsPanels.put("General", new GeneralSettingsPanel());
        settingsPanels.put("Account", new AccountSettingsPanel(this));

        ListView<String> list = new ListView<>(FXCollections.observableArrayList(settingsPanels.keySet()));
        Collections.sort(list.getItems(), Collections.reverseOrder());
        list.setPrefSize(150, 480);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (currentPanel != null) {
                    box.getChildren().remove(currentPanel);
                }

                box.getChildren().add(currentPanel = settingsPanels.get(newValue));
            }
        });

        box.getChildren().addAll(list, JFXUtils.region(5, 0));
        box.setPrefSize(480, 480);

        setTitle("Settings");
    }

    public <E extends Pane> E getPanel(String name) {
        return (E) settingsPanels.get(name);
    }
}