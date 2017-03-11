package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.SLogoModel;
import util.FileSelector;
import util.XMLParserWriter;
import view.GUI;

public class Workspace {

    public static final String DATA_FILE_EXTENSION = "*.xml";

    private Stage myStage;
    private FileSelector mySelector;
    private GUI myGUI;
    private SLogoModel myModel;

    public Workspace(Stage stage) {
        myStage = stage;
        mySelector = new FileSelector(DATA_FILE_EXTENSION);
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
            File dataFile = mySelector.chooseFile("Save to", myStage);
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
            File dataFile = mySelector.chooseFile("Choose file", myStage);
            if(dataFile != null) {
                Map<String,String> parameters = XMLParserWriter.extractContent(dataFile, false);
                myGUI.setBackgroundColor(Color.web(parameters.get("color")));
                myModel.setLanguage(parameters.get("language"));
            }
        }
    }
    
}
