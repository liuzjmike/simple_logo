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
import util.GUIUtils;
import util.SLogoException;

public class OptionView extends ScrollView {
    
    public static final List<String> LANGUAGES = Arrays.asList(
            "Chinese", "English", "French", "German", "Italian", "Portugese", "Russian", "Spanish");
    
    private ControlHandler myHandler;

    public OptionView(ControlHandler handler) {
        super("Options");
        myHandler = handler;
        populateButtons();
    }

    private void populateButtons() {
        addAllElements(GUIUtils.createButton("Help", e -> showHelp()),
        		GUIUtils.createButton("New Workspace", e -> myHandler.newWorkspace()),
        		GUIUtils.createButton("Save Workspace", e -> myHandler.saveWorkspace()),
        		GUIUtils.createButton("Load Workspace", e -> myHandler.loadWorkspace()),
        		GUIUtils.createComboBox("Choose Language", LANGUAGES, (observable, oldValue, newValue) -> {
                           myHandler.setLanguage(newValue);
                       }));
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
