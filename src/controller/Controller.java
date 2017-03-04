package controller;

import javafx.stage.Stage;

public class Controller {
	
	public Controller(Stage stage) {
		createNewWorkspace(stage);
	}
	
	public void createNewWorkspace() {
		Workspace newWorkspace = new Workspace();
		newWorkspace.start(new Stage());
		setWorkspaceHander(newWorkspace);
	}
	
	public void createNewWorkspace(Stage stage) {
		Workspace newWorkspace = new Workspace();
		newWorkspace.start(stage);
		setWorkspaceHander(newWorkspace);
		
	}
	
	private void setWorkspaceHander(Workspace newWorkspace) {
		newWorkspace.getMyGUI().setWorkspaceHandler(new WorkspaceHandler() {
			@Override
			public void addWorkspace() {
				createNewWorkspace();
			}
			
		});
	}
	
	
}

