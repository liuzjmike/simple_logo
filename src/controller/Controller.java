package controller;

import javafx.stage.Stage;
import model.SLogoModel;
import view.GUI;

public class Controller {
	
	GUI myGUI;
	SLogoModel mySLogoModel;
	
	public Controller() {
		myGUI = new GUI();
		mySLogoModel = new SLogoModel(myGUI.getPoolViewWidth(), myGUI.getPoolViewHeight());
		myGUI.setViewHandler(command -> {
			myGUI.addTextToConsole(Double.toString(mySLogoModel.interpret(command)));
		});
		myGUI.setGUIHandler(new GUIControlHandler(mySLogoModel));
		setUpObservers();
	}
	
	public void start(Stage stage) {
		myGUI.show(stage);
	}
    
    private void setUpObservers() {
    	mySLogoModel.addPoolObserver(myGUI.getPoolObserver());
    }
}
