package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import controller.ControlHandler;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import model.executable.command.Command;

public class ConsoleView extends Observable {
	
	VBox outputVBox;
	
	ScrollPane output;
	TextArea input;
	
	GridPane root;
	
	ArrayList<String> myStrings;
	String currentCommand;
	
	Button enterButton;
	
	ControlHandler myControlHandler;
	
	String activeText;
	
	public ConsoleView() {
		
		outputVBox = new VBox();
		output = new ScrollPane(outputVBox);
		input = new TextArea();
		
		root = new GridPane();
		
		ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.NEVER);
        cons1.setPercentWidth(95);
        
        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.NEVER);
        cons2.setPercentWidth(5);
        
        root.getColumnConstraints().addAll(cons1, cons2);
        
        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        rcons1.setPercentHeight(80);
        
        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.NEVER); 
        rcons2.setPercentHeight(20);

        root.getRowConstraints().addAll(rcons1, rcons2);
        
        enterButton = new Button("Run");
        installEnterButtonHandler();

		root.add(output,0,0,2,1);
		root.add(input,0,1,1,1);
		root.add(enterButton, 1, 1,1,1);
		
		GridPane.setValignment(enterButton,VPos.CENTER);
    	GridPane.setHalignment(enterButton,HPos.CENTER);
	}

    public String getActiveText() {
		return activeText;
    }
    
    private void setActiveText() {
    	activeText = input.getText();
    }
    
    public void addText(String retToConsole) {
    	output.getChildren().add(new Text(retToConsole));
    }
    
    public void addText(CommandTextView myCommandTextView) {
    	output.getChildren().add(myCommandTextView.getText());
    }
	
	public Node getNode() {
		return root;
	}
	
	private void installEnterButtonHandler() {
		DropShadow shadow = new DropShadow();
		enterButton.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	enterButton.setEffect(shadow);
		        	setActiveText();
		        	input.clear();
		        	notifyObservers();
		        }
		});
		
		enterButton.addEventHandler(MouseEvent.MOUSE_EXITED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	enterButton.setEffect(null);
		        }
		});
	}
	
}
