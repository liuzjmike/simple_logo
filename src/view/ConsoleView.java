package view;

import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import view.factory.ConstraintsFactory;
import view.factory.ControlFactory;

public class ConsoleView extends View<GridPane> {

    public static final double LEFT_CONSTRAINT = 95;
    public static final double TOP_CONSTRAINT = 70;
    
	ScrollView output;
	TextArea input;
	
	ArrayList<String> myStrings;
	String currentCommand;
	
	String activeText;
	
	public ConsoleView(Consumer<String> guiHandler) {
	    super("Console", new GridPane(), guiHandler);
		output = new ScrollView("Output", guiHandler);
		input = new TextArea();
        
		ConstraintsFactory constraintsFact = new ConstraintsFactory();
        getRoot().getColumnConstraints().addAll(constraintsFact.getColumnConstraints(LEFT_CONSTRAINT),
                                                constraintsFact.getColumnConstraints(1 - LEFT_CONSTRAINT));

        getRoot().getRowConstraints().addAll(constraintsFact.getRowConstraints(TOP_CONSTRAINT),
                                             constraintsFact.getRowConstraints(1 - TOP_CONSTRAINT));
        
        ControlFactory controlFact = new ControlFactory();
        VBox inputBox = new VBox(controlFact.createButton("Run", e -> {
                                     execute(input.getText());
                                     input.clear();
                                 }),
                                 controlFact.createButton("Load", e -> {})); //TODO

        getRoot().add(output.getRoot(),0,0,2,1);
        getRoot().add(input,0,1,1,1);
        getRoot().add(inputBox, 1, 1,1,1);
		
		GridPane.setValignment(inputBox,VPos.CENTER);
    	GridPane.setHalignment(inputBox,HPos.CENTER);
	}
    
    public void addCommandToScreen(String command) {
    	Text text = new Text(command);
    	text.setOnMouseClicked(e -> execute(text.getText()));
    	output.addElement(text);
    }
}
