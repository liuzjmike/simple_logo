package view;

import java.util.Arrays;
import java.util.List;

import controller.ControlHandler;
import javafx.stage.Stage;
import util.SLogoException;
import view.factory.ControlFactory;

public class OptionView extends ScrollView {
    
    public static final List<String> LANGUAGES = Arrays.asList(
            "Chinese", "English", "French", "German", "Italian", "Portugese", "Russian", "Spanish");
    
    private ControlHandler myHandler;

    public OptionView(ControlHandler handler) {
        super("Options", "big-container", handler, false);
        myHandler = handler;
        populateButtons();
    }

    private void populateButtons() {
    	ControlFactory cf = new ControlFactory();
        addAllElements(cf.createButton("Help", e -> showHelp()),
                       cf.createButton("New Workspace", e -> myHandler.newWorkspace()),
                       cf.createButton("Save Workspace", e -> myHandler.saveWorkspace()),
                       cf.createButton("Load Workspace", e -> myHandler.loadWorkspace()),
                       cf.createButton("Save Library", e -> myHandler.saveLibrary()),
                       cf.createButton("Load Library", e -> myHandler.loadLibrary()),
                       cf.createComboBox("Choose Language", LANGUAGES, (observable, oldValue, newValue) -> {
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
