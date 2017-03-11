package view.factory;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControlFactory {

    public Button createButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        return button;
    }
    
    public ComboBox<String> createComboBox(String promptText, List<String> content,
            ChangeListener<String> listener) {
        ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(content));
        cb.setPromptText(promptText);
        cb.getSelectionModel().selectedItemProperty().addListener(listener);
        return cb;
    }
}
