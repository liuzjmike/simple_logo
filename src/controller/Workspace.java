package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.TransformerException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
        public Double apply(String command) {
            return myModel.interpret(command);
        }

        @Override
        public void setLanguage(String language) {
            myModel.setLanguage(language);
        }

        public void newWorkspace() {
            newInstance().show();
        }

        public void saveWorkspace() {
            File dataFile = mySelector.saveTo(myStage);
            if(dataFile != null) {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put("Color", myGUI.getBackgroundColor().toString());
                parameters.put("Language", myModel.getLanguage());
                try {
                    XMLParserWriter.saveState(dataFile, "Workspace", parameters);
                } catch (TransformerException | IOException e) {
                    new Alert(AlertType.ERROR, "Save failed").show();
                }
            }
        }
        
        public void loadWorkspace() {
            File dataFile = mySelector.open(myStage);
            if(dataFile != null) {
                Map<String,String> parameters = XMLParserWriter.extractContent(dataFile, false);
                myGUI.setBackgroundColor(Color.web(parameters.get("color")));
                myModel.setLanguage(parameters.get("Language"));
            }
        }
    }
    
}
