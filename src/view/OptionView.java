package view;

import java.util.Arrays;
import java.util.List;

import controller.ControlHandler;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import util.SLogoException;

public class OptionView extends ScrollView {
    
    public static final List<String> LANGUAGES = Arrays.asList(
            "Chinese", "English", "French", "German", "Italian", "Portugese", "Russian", "Spanish");
    
    private ControlHandler myHandler;

    public OptionView(ControlHandler handler) {
        super("Options", "big-container", handler);
        myHandler = handler;
        populateButtons();
    }

    private void populateButtons() {
        addAllElements(createButton("Help", e -> showHelp()),
                       createButton("New Workspace", e -> myHandler.newWorkspace()),
                       createButton("Save Workspace", e -> myHandler.saveWorkspace()),
                       createButton("Load Workspace", e -> myHandler.loadWorkspace()),
                       createComboBox("Choose Language", LANGUAGES, (observable, oldValue, newValue) -> {
                           myHandler.setLanguage(newValue);
                       }));
    }

    private Button createButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        return button;
    }
    
    private ComboBox<String> createComboBox(String promptText, List<String> content,
            ChangeListener<String> listener) {
        ComboBox<String> cb = new ComboBox<>(FXCollections.observableArrayList(content));
        cb.setPromptText(promptText);
        cb.getSelectionModel().selectedItemProperty().addListener(listener);
        return cb;
    }
    
    private void showHelp() {
        HelpViewer myHelpViewer = new HelpViewer();
        try {
            myHelpViewer.start(new Stage());
        } catch (Exception e) {
            throw new SLogoException(SLogoException.HELP_VIEWER_FAILED);
        }
    }

}
