package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.SLogoModel;
import util.XMLParserWriter;
import view.GUI;

public class Workspace {

    public static final String DATA_FILE_EXTENSION = "*.xml";

    private Stage myStage;
    private FileChooser myChooser;
    private GUI myGUI;
    private SLogoModel myModel;

    public Workspace(Stage stage) {
        myStage = stage;
        myChooser = makeFileChooser(DATA_FILE_EXTENSION);
        myModel = new SLogoModel();
        myGUI = new GUI(stage, new MyControlHandler());
        myModel.setSize(myGUI.getPoolWidth(), myGUI.getPoolHeight());
        setUpObservers();
    }

    public void show() {
        myGUI.show();
    }

    private void setUpObservers() {
        myModel.addPoolObserver(myGUI.getPoolObserver());
        myModel.addVariableObserver(myGUI.getVariableObserver());
        myModel.addCommandObserver(myGUI.getCommandObserver());
        myModel.addPaletteObserver(myGUI.getPaletteObserver());
    }
    
    private Workspace newInstance() {
        return new Workspace(new Stage());
    }
    
    private FileChooser makeFileChooser(String extensionAccepted) {
        FileChooser result = new FileChooser();
        result.setInitialDirectory(new File(System.getProperty("user.dir")));
        result.getExtensionFilters().setAll(new ExtensionFilter("Text Files", extensionAccepted));
        return result;
    }
    
    private File chooseFile(String text) {
        myChooser.setTitle(text);
        File file = myChooser.showOpenDialog(myStage);
        return file;
    }
    
    private class MyControlHandler implements ControlHandler {
        
        @Override
        public void accept(String command) {
            myModel.interpret(command);
        }

        @Override
        public void setLanguage(String language) {
            myModel.setLanguage(language);
        }

        @Override
        public void newWorkspace() {
            newInstance().show();
        }

        @Override
        public void saveWorkspace() {
            File dataFile = chooseFile("Save to");
            if(dataFile != null) {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("Background color", myGUI.getBackgroundColor().toString());
                parameters.put("Language", myModel.getLanguage());
                //XMLParserWriter.saveState(dataFile, "Workspace", parameters, "SavedStates");
                //TODO
            }
        }
        
        @Override
        public void loadWorkspace() {
            File dataFile = chooseFile("Choose file");
            if(dataFile != null) {
                Map<String,String> parameters = XMLParserWriter.extractContent(dataFile, false);
                myGUI.setBackgroundColor(Color.web(parameters.get("color")));
                myModel.setLanguage(parameters.get("language"));
            }
        }
    }
    
}
