package view;

import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ConsoleView extends View<GridPane> {
	
	VBox outputVBox;
	
	ScrollPane output;
	TextArea input;
	
	ArrayList<String> myStrings;
	String currentCommand;
	
	Button enterButton;
	
	String activeText;
	
	private Consumer<String> myHandler;
	
	public ConsoleView(Consumer<String> guiHandler) {
	    super("Console", new GridPane());
	    myHandler = guiHandler;
		
		outputVBox = new VBox();
		output = new ScrollPane(outputVBox);
		input = new TextArea();
		
		ColumnConstraints cons1 = new ColumnConstraints();
        cons1.setHgrow(Priority.NEVER);
        cons1.setPercentWidth(95);
        
        ColumnConstraints cons2 = new ColumnConstraints();
        cons2.setHgrow(Priority.NEVER);
        cons2.setPercentWidth(5);
        
        getRoot().getColumnConstraints().addAll(cons1, cons2);
        
        RowConstraints rcons1 = new RowConstraints();
        rcons1.setVgrow(Priority.NEVER);
        rcons1.setPercentHeight(80);
        
        RowConstraints rcons2 = new RowConstraints();
        rcons2.setVgrow(Priority.NEVER); 
        rcons2.setPercentHeight(20);

        getRoot().getRowConstraints().addAll(rcons1, rcons2);
        
        enterButton = new Button("Run");
        installEnterButtonHandler();

        getRoot().add(output,0,0,2,1);
        getRoot().add(input,0,1,1,1);
        getRoot().add(enterButton, 1, 1,1,1);
		
		GridPane.setValignment(enterButton,VPos.CENTER);
    	GridPane.setHalignment(enterButton,HPos.CENTER);
	}

    public String getActiveText() {
		return input.getText();
    }
   
    public void addText(String retToConsole) {
    	outputVBox.getChildren().add(new Text(retToConsole));
    }
    
    public void addCommandToScreen(String myCommand) {
    	Text myCommandText = new Text(myCommand);
    	installHandler(myCommandText);
    	outputVBox.getChildren().add(myCommandText);
    }
    
    private void installHandler(Text myText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	try {
						myHandler.accept(myText.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		});
	}
	
	private void installEnterButtonHandler() {
		enterButton.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		            myHandler.accept(getActiveText());
		        	input.clear();
		        }
		});
	}

	
}
