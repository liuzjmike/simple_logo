package controller;

import javafx.stage.Stage;
import model.SLogoModel;
import view.GUI;

public class Workspace {
	
	GUI myGUI;
	SLogoModel mySLogoModel;
	
	public Workspace() {
	
	myGUI = new GUI();
	mySLogoModel = new SLogoModel(myGUI.getPoolViewWidth(), myGUI.getPoolViewHeight());
	myGUI.setHandlers(new ControlHandler() {

        @Override
        public void execute(String command) {
            mySLogoModel.interpret(command);
        }

        @Override
        public void setLanguage(String language) {
            mySLogoModel.setLanguage(language);
        }
        
        public String getLanguage() {
        	return mySLogoModel.getLanguage();
        }
	    
	});
	setUpObservers();
	}
	
	public void start(Stage stage) {
		myGUI.show(stage);
		stage.setResizable(false);
	}
	
    private void setUpObservers() {
    	mySLogoModel.addPoolObserver(myGUI.getPoolObserver());
    	mySLogoModel.addVariableObserver(myGUI.getVariableObserver());
    	mySLogoModel.addCommandObserver(myGUI.getCommandObserver());
    }
    
    public GUI getMyGUI() {
		return myGUI;
	}

	public void setMyGUI(GUI myGUI) {
		this.myGUI = myGUI;
	}

	public SLogoModel getMySLogoModel() {
		return mySLogoModel;
	}

	public void setMySLogoModel(SLogoModel mySLogoModel) {
		this.mySLogoModel = mySLogoModel;
	}

   

}
