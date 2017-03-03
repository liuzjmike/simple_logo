package controller;

import java.util.ArrayList;
import java.util.List;

import javafx.stage.Stage;
import model.SLogoModel;
import view.GUI;

//public class Controller {
//	
//	GUI myGUI;
//	SLogoModel mySLogoModel;
//	
//	public Controller() {
//		myGUI = new GUI();
//		mySLogoModel = new SLogoModel(myGUI.getPoolViewWidth(), myGUI.getPoolViewHeight());
//		myGUI.setViewHandler(new ControlHandler() {
//
//            @Override
//            public void execute(String command) {
//                mySLogoModel.interpret(command);
//            }
//
//            @Override
//            public void setLanguage(String language) {
//                mySLogoModel.setLanguage(language);
//            }
//		    
//		});
//		setUpObservers();
//	}
//	
//	public void start(Stage stage) {
//		myGUI.show(stage);
//	}
//    
//    private void setUpObservers() {
//    	mySLogoModel.addPoolObserver(myGUI.getPoolObserver());
//    	mySLogoModel.addVariableObserver(myGUI.getVariableObserver());
//    	mySLogoModel.addCommandObserver(myGUI.getCommandObserver());
//    }
//}

public class Controller {
	
	List<Workspace> workspaces;
	
	public Controller(Stage stage) {
		workspaces = new ArrayList<Workspace>();
		createNewWorkspace(stage);
	}
	
	public void createNewWorkspace() {
		Workspace newWorkspace = new Workspace();
		workspaces.add(newWorkspace);
		newWorkspace.start(new Stage());
		newWorkspace.getMyGUI().setWorkspaceHandler(new WorkspaceHandler() {
			@Override
			public void addWorkspace() {
				createNewWorkspace();
			}
			
		});
		
	}
	
	public void createNewWorkspace(Stage stage) {
		Workspace newWorkspace = new Workspace();
		workspaces.add(newWorkspace);
		newWorkspace.start(stage);
		newWorkspace.getMyGUI().setWorkspaceHandler(new WorkspaceHandler() {
			@Override
			public void addWorkspace() {
				createNewWorkspace();
			}
			
		});
		
	}
	
	
}

