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
		mySLogoModel = new SLogoModel();
		myGUI.addObserver(this);
		mySLogoModel.addObserver(this);
		setUpObservers();
	}
	
	public void start(Stage stage) {
		myGUI.show(stage);
	}
    
    private void setUpObservers() {
		mySLogoModel.addCommandObserver(myGUI.getCommandObserver());
		mySLogoModel.addVariableObserver(myGUI.getVariableObserver());
		mySLogoModel.addPoolObserver(myGUI.getPoolObserver());
    }

	@Override
	public void update(Observable o, Object arg) {
		if(o==myGUI) {
			try {
				String retToConsole = mySLogoModel.interpret(myGUI.getActiveConsoleText());
				if (!retToConsole.equals("")) {
					myGUI.addTextToConsole(retToConsole);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (o==mySLogoModel) {
		}
	}

	

}
