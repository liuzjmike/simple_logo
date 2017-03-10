package controller;

import javafx.stage.Stage;
import model.SLogoModel;
import view.GUI;

public class Workspace {

    GUI myGUI;
    SLogoModel myModel;

    public Workspace(Stage stage) {
        myModel = new SLogoModel();
        myGUI = new GUI(stage, new ControlHandler() {

            @Override
            public void accept(String command) {
                myModel.interpret(command);
            }

            @Override
            public void setLanguage(String language) {
                myModel.setLanguage(language);
            }

            public String getLanguage() {
                return myModel.getLanguage();
            }
            
            public void newWorkspace() {
                newInstance().show();
            }

        });
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
    
}
