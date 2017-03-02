package view;

import java.util.Collection;

import controller.ControlHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.executable.command.Command;
import model.turtle.Turtle;
import util.SLogoObserver;

public class GUI implements SLogoObserver<String> {
	
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
	
	public GUI() {
		myPoolView = new PoolView();
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
    	stage.setTitle("SLogo IDE");
    	stage.setScene(myScene);
    	stage.show();
	}
    
    public void setHandler(ControlHandler handler) {
    	myHandler = handler;
    	myConsoleView.setHandler(handler);
    	myCommandView.setHandler(handler);
    	myVariableView.setHandler(handler);
    }
    
    private GridPane getGridPane() {
    	GridPane root = new GridPane();
    	
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
	   return myGridPane.getRowConstraints().get(row).getPercentHeight()*myGridPane.getHeight();
   }
   
   private double getViewWidth(int col) {
	   return myGridPane.getColumnConstraints().get(col).getPercentWidth()*myGridPane.getWidth();
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
    
    public SLogoObserver<Collection<Turtle>> getPoolObserver() {
		return myPoolView;
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
		userBar.getChildren().addAll(changeColorButton,showReferenceButton);
		return userBar;
	}
	
	private void promptForBackgroundColorChange() {
		
	}

	public Command getCurrentCommand() {
		return currentCommand;
	}

	public void setCurrentCommand(Command currentCommand) {
		this.currentCommand = currentCommand;
	}

	@Override
	public void update(String arg) {
		try {
			myHandler.execute(arg);
			addTextToConsole(arg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
