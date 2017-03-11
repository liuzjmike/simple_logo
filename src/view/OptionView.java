package view;

import java.util.function.Consumer;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import util.SLogoException;

public class OptionView extends ScrollView {

    public OptionView(Consumer<String> guiHandler) {
        super("Options", guiHandler);
        populateButtons();
    }

    private void populateButtons() {
        addAllElements(createButton("Help", e -> showHelp()));
    }

    private Button createButton(String text, EventHandler<ActionEvent> handler) {
        Button button = new Button(text);
        button.setOnAction(handler);
        return button;
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
