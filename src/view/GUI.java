package view;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import controller.ControlHandler;
import controller.StringProcessor;
import controller.WorkspaceHandler;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.executable.Literal;
import model.executable.command.Command;
import model.turtle.TurtleInfo;
import util.SLogoObserver;

public class GUI {
	
	private PoolView myPoolView;
	private ConsoleView myConsoleView;
	private VariableView myVariableView;
	private CommandView myCommandView;
	
	private GridPane myGridPane;
	
	private int poolViewRow;
	private int poolViewCol;
	
	private Color backgroundColor;
	
	private Command currentCommand;
	private ControlHandler myHandler;
	
	private StringProcessor myGUIHandler;
	private WorkspaceHandler myWorkspaceHandler;
	
	private Stage myStage;
	
	public GUI() {
		myPoolView = new PoolView(900, 480);
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
    
    public void setViewHandler(ControlHandler handler) {
        myGUIHandler = command -> {
            try {
                handler.execute(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
            myConsoleView.addCommandToScreen(command);
        };
    	myHandler = handler;
    	myConsoleView.setHandler(myGUIHandler);
    	myCommandView.setHandler(myGUIHandler);
    	myVariableView.setHandler(myGUIHandler);
    }
    
    private GridPane getGridPane() {
    	GridPane root = new GridPane();
    	root.setPrefHeight(800);
    	root.setPrefWidth(1200);
    	
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
    	rcons2.setPercentHeight(35);
    	
    	RowConstraints rcons3 = new RowConstraints();
    	rcons3.setVgrow(Priority.NEVER);  
    	rcons3.setPercentHeight(5);

    	root.getRowConstraints().addAll(rcons1, rcons2, rcons3);
    	
    	root.add(getPoolViewNode(), poolViewCol, poolViewRow,1,1);
    	
    	root.add(getConsoleViewNode(), 0, 1,1,1);
    	
    	root.add(getCommandViewNode(), 1, 0,1,1);
    	
    	root.add(getVariableViewNode(),1, 1,1,2);
    	
    	root.add(getUserBar(), 0, 2,1,1);

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
    
    public SLogoObserver<Collection<TurtleInfo>> getPoolObserver() {
		return myPoolView;
	}
    
    public SLogoObserver<List<Entry<String, Literal>>> getVariableObserver() {
        return myVariableView;
    }
    
    public SLogoObserver<List<Entry<String, Command>>> getCommandObserver() {
        return myCommandView;
    }
    
    public void addTextToConsole(String retToConsole) {
    	myConsoleView.addText(retToConsole);
    }
    
    public String getActiveConsoleText() {
    	return myConsoleView.getActiveText();
    }
	
	public HBox getUserBar() {
		HBox userBar = new HBox();
		userBar.setAlignment(Pos.CENTER_LEFT);
		Button changeColorButton = new Button("Change Background Color");
		changeColorButton.setOnMouseClicked(onClick -> promptForBackgroundColorChange());
		Button showReferenceButton = new Button("SLogo Reference");
		showReferenceButton.setOnMouseClicked(onClick -> promptForReference());
		Button changeLanguageButton = new Button("Change Language");
		changeLanguageButton.setOnMouseClicked(onClick -> promptLanguage());
		Button newWorkspace = new Button("Create New Workspace");
		newWorkspace.setOnMouseClicked(onClick -> createNewWorkspace());
		userBar.getChildren().addAll(changeColorButton,showReferenceButton,changeLanguageButton,newWorkspace);
		return userBar;
	}
	
	private void createNewWorkspace() {
//		Workspace workspace = new Workspace();
//		workspace.start((new Stage()));
		myWorkspaceHandler.addWorkspace();
	}
	
	public void setWorkspaceHandler(WorkspaceHandler handler) {
		myWorkspaceHandler = handler;
	}
	
	private void promptForReference() {
		class HelpViewer extends Application {
			   private WebEngine webEngine;
			   private WebView   webView;
			@Override
			public void start(Stage primaryStage) throws Exception {
				// TODO Auto-generated method stub
				 webView = new WebView();
			      webView.setVisible(true);
			      webEngine = webView.getEngine();
			      webEngine.setJavaScriptEnabled(true);
			      webEngine.load(getClass().getClassLoader().getResource("reference.html").toExternalForm());

			      Scene scene = new Scene(webView, 500, 300);

			      primaryStage.setTitle("Commands Reference");
			      primaryStage.setScene(scene);
			      primaryStage.show();
			}
		}
		
		HelpViewer myHelpViewer = new HelpViewer();
		try {
			myHelpViewer.start(new Stage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void promptLanguage() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Change Language");
		alert.setHeaderText(null);
		alert.setContentText("Choose a language");
		
		ButtonType buttonTypeChinese = new ButtonType("Chinese");
		ButtonType buttonTypeEnglish = new ButtonType("English");
		ButtonType buttonTypeFrench= new ButtonType("French");
		ButtonType buttonTypeGerman = new ButtonType("German");
		ButtonType buttonTypeItalian = new ButtonType("Italian");
		ButtonType buttonTypePortugese = new ButtonType("Portugese");
		ButtonType buttonTypeRussian = new ButtonType("Russian");
		ButtonType buttonTypeSpanish = new ButtonType("Spanish");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		alert.getButtonTypes().setAll(buttonTypeChinese, buttonTypeEnglish, buttonTypeFrench, buttonTypeGerman,buttonTypeItalian,buttonTypePortugese,buttonTypeRussian,buttonTypeSpanish,buttonTypeCancel);
		
		ButtonType buttonSelected = alert.showAndWait().get();
		myHandler.setLanguage(buttonSelected.getText());
		myStage.toFront();
	}

	
	private void promptForBackgroundColorChange() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Change Background Color");
		alert.setHeaderText(null);
		alert.setContentText("Choose a color");
		
		ButtonType buttonTypeBlue = new ButtonType("Blue");
		ButtonType buttonTypeBlack = new ButtonType("Black");
		ButtonType buttonTypeRed = new ButtonType("Red");
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(buttonTypeBlue, buttonTypeBlack, buttonTypeRed, buttonTypeCancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if (result.get() == buttonTypeBlue){
		    myPoolView.setBackgroundColor(Color.BLUE);
		} else if (result.get() == buttonTypeBlack) {
			myPoolView.setBackgroundColor(Color.BLACK);
		} else if (result.get() == buttonTypeRed) {
			myPoolView.setBackgroundColor(Color.RED);
		} else {
		    return;
		}
		myStage.toFront();

	}

	public Command getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(Command currentCommand) {
		this.currentCommand = currentCommand;
	}
}
