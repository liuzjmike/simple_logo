package view;

import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.event.ActionEvent;
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
import util.GUIUtils;

public class ConsoleView extends View<GridPane> {
	
	VBox outputVBox;
	
	ScrollPane output;
	TextArea input;
	
	ArrayList<String> myStrings;
	String currentCommand;
	
	String activeText;
	
	public ConsoleView(Consumer<String> guiHandler) {
	    super("Console", new GridPane(), guiHandler);
		
		outputVBox = new VBox();
		output = new ScrollPane(outputVBox);
		input = new TextArea();
		
		ColumnConstraints cons1 = GUIUtils.getColumnConstraints(95);
        
        ColumnConstraints cons2 = GUIUtils.getColumnConstraints(5);
        
        getRoot().getColumnConstraints().addAll(cons1, cons2);
        
        RowConstraints rcons1 = GUIUtils.getRowConstraints(80);
        
        RowConstraints rcons2 = GUIUtils.getRowConstraints(20);
        getRoot().getRowConstraints().addAll(rcons1, rcons2);
        
        Button enterButton = GUIUtils.createButton("Run",e ->executeAndRefresh());

        getRoot().add(output,0,0,2,1);
        getRoot().add(input,0,1,1,1);
        getRoot().add(enterButton, 1, 1,1,1);
		
		GridPane.setValignment(enterButton,VPos.CENTER);
    	GridPane.setHalignment(enterButton,HPos.CENTER);
	}
   
    public void addText(String retToConsole) {
    	outputVBox.getChildren().add(new Text(retToConsole));
    }
    
    public void addCommandToScreen(String myCommand) {
    	Text commandText = GUIUtils.createText(myCommand, e -> {
    		try {
    			execute(myCommand);
    			} 
    		catch(Exception e1) {
    			e1.printStackTrace();}
    		});
    	outputVBox.getChildren().add(commandText);
    }
    
	private void executeAndRefresh() {
		execute(input.getText());
     	input.clear();
	}

	
}
