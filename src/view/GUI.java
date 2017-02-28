package view;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

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
import model.turtle.Turtle;

public class GUI extends Observable implements Observer {
	
	private PoolView myPoolView;
	private ConsoleView myConsoleView;
	private VariableView myVariableView;
	private CommandView myCommandView;
	
	private Color backgroundColor;
	
	public GUI() {
		myPoolView = new PoolView();
		myConsoleView = new ConsoleView();
		myVariableView = new VariableView();
		myCommandView = new CommandView();
		backgroundColor = Color.WHITE;
	}

    public void show(Stage stage) {
    	Scene myScene = new Scene(getGridPane(),backgroundColor);
    	stage.setTitle("SLogo IDE");
    	stage.setScene(myScene);
    	stage.show();
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
    	
    	root.add(getPoolViewNode(), 0,0,1,1);
    	
    	root.add(getConsoleViewNode(), 0, 1,1,1);
    	
    	root.add(getCommandViewNode(), 1, 0,1,1);
    	
    	root.add(getVariableViewNode(),1, 1,1,2);
    	
    	root.add(getUserBar(), 0, 2,1,1);
    	
    	return root;
    }
    
    private Node getPoolViewNode() {
    	return myPoolView.getNode();
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
    
    public Observer getPoolObserver() {
		return myPoolView;
	}
    
    public Observer getCommandObserver() {
    	return myCommandView;
	}
    
    public Observer getVariableObserver() {
		return myVariableView;
	}
    
    public void addCommand(String command) {
	}
    
    public void addVariable(String variable) {
    	myVariableView.addVariable(variable);
	}
    
    public void setTurtles(Collection<Turtle> turtles) {
    	myPoolView.setTurtle(turtles);
	}
    
    public void addTextToConsole(String retToConsole) {
    	myConsoleView.addText(retToConsole);
    }
    
    public String getActiveConsoleText() {
    	return myConsoleView.getActiveText();
    }

	@Override
	public void update(Observable o, Object arg) {
		if(o==myConsoleView) {
			notifyAll();
		}
	}
	
	public HBox getUserBar() {
		HBox userBar = new HBox();
		userBar.setAlignment(Pos.CENTER_LEFT);
		Button changeColorButton = new Button("Change Background Color");
		Button showReferenceButton = new Button("SLogo Reference");
		userBar.getChildren().addAll(changeColorButton,showReferenceButton);
		return userBar;
	}

}
