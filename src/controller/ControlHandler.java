package controller;

import java.util.Observable;
import java.util.Observer;

import javafx.stage.Stage;
import model.SLogoModel;
import view.GUI;

public class ControlHandler implements Observer {
	
	GUI myGUI;
	SLogoModel mySLogoModel;
	
	public ControlHandler() {
		myGUI = new GUI();
		mySLogoModel = new SLogoModel(myGUI.getPoolViewWidth(), myGUI.getPoolViewHeight());
		myGUI.addObserver(this);
		setUpObservers();
	}
	
	public void start(Stage stage) {
		myGUI.show(stage);
	}
    
    private void setUpObservers() {
    	mySLogoModel.addCommandObserver(myGUI.getCommandObserver());
    	mySLogoModel.addPoolObserver(myGUI.getPoolObserver());
    	mySLogoModel.addVariableObserver(myGUI.getVariableObserver());
    }

	@Override
	public void update(Observable o, Object arg) {
		if(o==myGUI) {
			try {
				//what is returned when command returns nothing?
				double retToConsole = mySLogoModel.interpret(myGUI.getActiveConsoleText());
				myGUI.addTextToConsole(Double.toString(retToConsole));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	

}
