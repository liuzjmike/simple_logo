package view;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.xml.transform.TransformerException;

import controller.ControlHandler;
import controller.StringProcessor;
import controller.WorkspaceHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;
import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.info.PoolInfo;
import util.SLogoException;
import util.SLogoObserver;
import util.XMLParserWriter;

public class GUI {
	
	private PoolView myPoolView;
	private ConsoleView myConsoleView;
	private VariableView myVariableView;
	private CommandView myCommandView;
	
	private GridPane myGridPane;
	
	private int poolViewRow;
	private int poolViewCol;
	
	private Color backgroundColor;
	
	private ControlHandler myHandler;
	
	private StringProcessor myGUIHandler;
	private WorkspaceHandler myWorkspaceHandler;
	
	private Stage myStage;
	
	public enum FileType {COMMANDS,SAVED_STATE}
	
	public GUI() {
		myPoolView = new PoolView(1050, 600);
		myConsoleView = new ConsoleView();
		myVariableView = new VariableView();
		myCommandView = new CommandView();
		backgroundColor = Color.WHITE;
    	poolViewRow = 0;
    	poolViewCol = 0;
    	myGridPane = getGridPane();
	}
    public void show(Stage stage) {
    	Scene myScene = new Scene(getGridPane(),backgroundColor);
    	myStage = stage;
    	stage.setTitle("SLogo IDE");
    	stage.setScene(myScene);
    	stage.show();
	}
    
    public void setHandlers(ControlHandler handler) {
        myGUIHandler = command -> {
        	if (!command.isEmpty()) {
            try {
                handler.execute(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
            myConsoleView.addCommandToScreen(command);
        	}
        };
    	myHandler = handler;
    	myConsoleView.setHandler(myGUIHandler);
    	myCommandView.setHandler(myGUIHandler);
    	myVariableView.setHandler(myGUIHandler);
    	myPoolView.setHandler(myGUIHandler);
    }
    
    private GridPane getGridPane() {
    	GridPane root = new GridPane();
    	root.setPrefHeight(1000);
    	root.setPrefWidth(1400);
    	
    	ColumnConstraints cons1 = new ColumnConstraints();
    	cons1.setHgrow(Priority.NEVER);
    	cons1.setPercentWidth(75);
    	
    	ColumnConstraints cons2 = new ColumnConstraints();
    	cons2.setHgrow(Priority.NEVER);
    	cons2.setPercentWidth(25);
    	
    	root.getColumnConstraints().addAll(cons1, cons2);
    	
    	RowConstraints rcons1 = new RowConstraints();
    	rcons1.setVgrow(Priority.NEVER);
    	rcons1.setPercentHeight(60);
    	RowConstraints rcons2 = new RowConstraints();
    	rcons2.setVgrow(Priority.NEVER); 
    	rcons2.setPercentHeight(30);
    	RowConstraints rcons3 = new RowConstraints();
    	rcons3.setVgrow(Priority.NEVER);  
    	rcons3.setPercentHeight(5);
    	RowConstraints rcons4 = new RowConstraints();
    	rcons4.setVgrow(Priority.NEVER);  
    	rcons4.setPercentHeight(5);
    	root.getRowConstraints().addAll(rcons1, rcons2, rcons3,rcons4);
    	
    	root.add(getPoolViewNode(), poolViewCol, poolViewRow,1,1);
    	
    	root.add(getConsoleViewNode(), 0, 1,1,1);
    	
    	root.add(getCommandViewNode(), 1, 0,1,1);
    	
    	root.add(getVariableViewNode(),1, 1,1,2);
    	
    	root.add(getUserBar1(), 0, 2,1,1);
    	root.add(getUserBar2(), 0, 3,1,1);
    	return root;
    }
    
   private double getViewHeight(int row) {
	   return myGridPane.getRowConstraints().get(row).getPercentHeight()/100*myGridPane.getPrefHeight();
   }
   
   private double getViewWidth(int col) {
	   return myGridPane.getColumnConstraints().get(col).getPercentWidth()/100*myGridPane.getPrefWidth();
   }
   
   public double getPoolViewHeight() {
	   return getViewHeight(poolViewCol); 
   }
   
   public double getPoolViewWidth() {
	   return getViewWidth(poolViewRow);
   }
    
    private Node getPoolViewNode() {
    	return myPoolView.getRoot();
    }
    
    private Node getConsoleViewNode() {
    	return myConsoleView.getNode();
    }
    
    private Node getVariableViewNode() {
    	return myVariableView.getNode();
    }
    
    private Node getCommandViewNode() {
    	return myCommandView.getNode();
    }
    
    public SLogoObserver<PoolInfo> getPoolObserver() {
		return myPoolView;
	}
    
    public SLogoObserver<List<Entry<String, Literal>>> getVariableObserver() {
        return myVariableView;
    }
    
    public SLogoObserver<Map<String, Command>> getCommandObserver() {
        return myCommandView;
    }
    
    public void addTextToConsole(String retToConsole) {
    	myConsoleView.addText(retToConsole);
    }
    
    public String getActiveConsoleText() {
    	return myConsoleView.getActiveText();
    }
	
	public HBox getUserBar1() {
		HBox userBar = new HBox();
		userBar.setAlignment(Pos.CENTER_LEFT);
		Button changeColorButton = new Button("Change Background Color");
		changeColorButton.setOnMouseClicked(onClick -> promptForBackgroundColorChange());
		Button showReferenceButton = new Button("SLogo Reference");
		showReferenceButton.setOnMouseClicked(onClick -> promptForReference());
		Button changeLanguageButton = new Button("Change Language");
		changeLanguageButton.setOnMouseClicked(onClick -> promptLanguage());
		ObservableList<String> options = FXCollections.observableArrayList(
		        "Thin",
		        "Moderate",
		        "Thick"
		    );
		ComboBox penSettingBox = new ComboBox(options);
		penSettingBox.setPromptText("Pen Thickness");
		//penSettingButton.setOnMouseClicked(onClick -> setPenSetting());
		
		
		userBar.getChildren().addAll(changeColorButton,showReferenceButton,changeLanguageButton,penSettingBox);
		return userBar;
	}
	
//	private void setPenSetting() {
//		Popup myPop = new Popup();
//		ObservableList<String> options = FXCollections.observableArrayList(
//		        "Thin",
//		        "Moderate",
//		        "Thick"
//		    );
//		myPop.getContent().add(new ComboBox(options));
//		myPop.show(myStage);
//
//		
//	}
	public HBox getUserBar2() {
		HBox userBar = new HBox();
		userBar.setAlignment(Pos.CENTER_LEFT);
		Button newWorkspace = new Button("Create New Workspace");
		newWorkspace.setOnMouseClicked(onClick -> createNewWorkspace());
		Button saveState = new Button("Save Workspace Settings");
		saveState.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					saveState();
				} catch (TransformerException | IOException e) {
					e.printStackTrace();
				}
			}
		});
		Button extractState = new Button("Get Saved Workspace Settings");
		extractState.setOnMouseClicked(onClick -> extractState());
		Button readCommandsFromFile = new Button("Read Commands From File");
		readCommandsFromFile.setOnAction(onClick -> executeCommandsFromFile());
		userBar.getChildren().addAll(newWorkspace,saveState,extractState,readCommandsFromFile);
		return userBar;
	}
	
	private void createNewWorkspace() {
		myWorkspaceHandler.addWorkspace();
	}
	
	public void setWorkspaceHandler(WorkspaceHandler handler) {
		myWorkspaceHandler = handler;
	}
	
	private void promptForReference() {
		HelpViewer myHelpViewer = new HelpViewer("reference.html");
		try {
			myHelpViewer.start(new Stage());
		} catch (Exception e) {
			throw new SLogoException(SLogoException.HELP_VIEWER_FAILED);
		}
	}
	
	private void addButtons(Alert alert, String[] strings) {
		for (String str : strings) {
			ButtonType button = new ButtonType(str);
			alert.getButtonTypes().add(button);
		}
	}
	
	private void addCancelButton(Alert alert) {
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().add(buttonTypeCancel);
	}
	
	private void promptLanguage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Change Language");
		alert.setHeaderText(null);
		alert.setContentText("Choose a language");
		
		String[] buttons = new String[] {"Chinese","English","French","German","Italian","Portugese","Russian","Spanish"};
		
		addButtons(alert,buttons);
		addCancelButton(alert);
		
		ButtonType buttonSelected = alert.showAndWait().get();
		myHandler.setLanguage(buttonSelected.getText());
		myStage.toFront();
	}
	
	private void promptForBackgroundColorChange() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Change Background Color");
		alert.setHeaderText(null);
		alert.setContentText("Choose a color");
		
		String[] buttons = new String[] {"Blue","Black","Red"};
		
		addButtons(alert,buttons);
		addCancelButton(alert);
		
		ButtonType result = alert.showAndWait().get();
		
		if (result.getText().equals("Blue")){
		    myPoolView.setBackgroundColor(Color.BLUE);
		} else if (result.getText().equals("Black")) {
			myPoolView.setBackgroundColor(Color.BLACK);
		} else if (result.getText().equals("Red")) {
			myPoolView.setBackgroundColor(Color.RED);
		} else {
		    throw new SLogoException(SLogoException.INVALID_COLOR_BUTTON);
		}
		myStage.toFront();
	}
	
	private void saveState() throws TransformerException, IOException {
		String fileName = promptUserForFileName();
		Map<String,String> parameters = new HashMap<String,String>();
		parameters.put("color", myPoolView.getBackgroundColor().toString());
		parameters.put("language", myHandler.getLanguage());
		XMLParserWriter.saveState(fileName, "workspace",parameters,"SavedStates");
	}
	
	private File promptUserForFile() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open file");
		File file = fileChooser.showOpenDialog(new Stage());
		return file;
	}
	
	private String promptUserForFileName() {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Select File Name");
		dialog.setHeaderText(null);
		dialog.setContentText("Type in the name of the file: ");
		Optional<String> result = dialog.showAndWait();
		if (result.isPresent()) {
			return result.get();
		}
		return null;
	}
	
	private Map<String,String> getFileContents(boolean orderMatters) {
		File file = promptUserForFile();
		return XMLParserWriter.extractContent(file,orderMatters);
	}
	
	private void extractState() {
		Map<String,String> parameters = getFileContents(false);
		myPoolView.setBackgroundColor(Color.web(parameters.get("color")));
		myHandler.setLanguage(parameters.get("language"));
	}
	
	public void executeCommandsFromFile() {
		Map<String,String> commands = getFileContents(true);
		for (String command : commands.keySet()) {
			myConsoleView.addCommandToScreen(commands.get(command));
			myHandler.execute(commands.get(command));
		}
		}
}