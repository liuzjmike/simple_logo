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
		myGUI.setHandler(command -> {
			myGUI.addTextToConsole(Double.toString(mySLogoModel.interpret(command)));
		});
		setUpObservers();
	}
	
	public void start(Stage stage) {
		myGUI.show(stage);
	}
    
    private void setUpObservers() {
    	mySLogoModel.addPoolObserver(myGUI.getPoolObserver());
    }
}
