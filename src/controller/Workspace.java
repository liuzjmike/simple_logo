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
import util.TextFileParser;
import util.XMLParserWriter;
import view.GUI;

/**
 * Coordinates the visual representation and back-end
 * functionalities of the SLogo IDE.
 */
public class Workspace {

    public static final String TITLE = "SLogo";
    public static final String CONFIG_EXTENSION = "*.xml";
    public static final String LIBRARY_EXTENSION = "*.lib";
    public static final String ROOT_TAG = "Workspace";
    public static final String COLOR_TAG = "Color";
    public static final String LANGUAGE_TAG = "Language";

    private Stage myStage;
    private FileSelector mySelector;
    private TextFileParser myParser;
    private GUI myGUI;
    private SLogoModel myModel;

    public Workspace(Stage stage) {
        stage.setTitle(TITLE);
        myStage = stage;
        mySelector = new FileSelector(CONFIG_EXTENSION);
        myParser = new TextFileParser(LIBRARY_EXTENSION);
        myModel = new SLogoModel();
        myGUI = new GUI(stage, new MyControlHandler());
        myModel.setSize(myGUI.getPoolWidth(), myGUI.getPoolHeight());
        setUpObservers();
    }

    /**
     * Display the graphical user interface to the stage.
     */
    public void show() {
        myGUI.show();
    }

    // Links front-end observers to observable in the backend model
    private void setUpObservers() {
        myModel.addPoolObserver(myGUI.getPoolObserver());
        myModel.addVariableObserver(myGUI.getVariableObserver());
        myModel.addCommandObserver(myGUI.getCommandObserver());
        myModel.addPaletteObserver(myGUI.getPaletteObserver());
    }
    
    private Workspace newInstance() {
        return new Workspace(new Stage());
    }
    
    // Defines a specific type of control handler for our purpose
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
            mySelector.setExtension(CONFIG_EXTENSION);
            File dataFile = mySelector.saveTo(myStage);
            if(dataFile != null) {
                Map<String,String> parameters = new HashMap<String,String>();
                parameters.put(COLOR_TAG, myGUI.getBackgroundColor().toString());
                parameters.put(LANGUAGE_TAG, myModel.getLanguage());
                try {
                    XMLParserWriter.saveState(dataFile, ROOT_TAG, parameters);
                } catch (TransformerException | IOException e) {
                    new Alert(AlertType.ERROR, "Save failed").show();
                }
            }
        }
        
        public void loadWorkspace() {
            mySelector.setExtension(CONFIG_EXTENSION);
            File dataFile = mySelector.open(myStage);
            if(dataFile != null) {
                Map<String,String> parameters = XMLParserWriter.extractContent(dataFile, false);
                myGUI.setBackgroundColor(Color.web(parameters.get(COLOR_TAG)));
                myModel.setLanguage(parameters.get(LANGUAGE_TAG));
            }
        }

		@Override
		public void saveLibrary() {
		    myParser.save(myModel.getLibrary(), myStage);
		}

		@Override
		public void loadLibrary() {
		    myModel.interpret(myParser.load());
		}
    }
    
}
