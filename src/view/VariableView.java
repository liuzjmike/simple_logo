package view;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import util.SLogoObserver;

public class VariableView implements SLogoObserver<List<Entry<String, Literal>>> {
	
	VBox vBox;
	ScrollPane myPane;
	
	Consumer<String> myHandler;
	
	public VariableView() {
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Variables:\n");
		vBox.getChildren().add(text);
	}
    
    public void setHandler(Consumer<String> handler) {
        myHandler = handler;
    }

	public Node getNode() {
		return myPane;
	}
	private void addVariableToScreen(Entry<String,Literal> entry) {
		HBox myText = getVariableText(entry);
		vBox.getChildren().add(myText);
	}
	
	private HBox getVariableText(Entry<String,Literal> entry) {
		HBox myHBox = new HBox();
		Text variableText = new Text(entry.getKey()+" = ");
		TextField editableText = new TextField(Double.toString(entry.getValue().getValue()));
		myHBox.getChildren().addAll(variableText,editableText);
		installHandler(entry,variableText,editableText);
		return myHBox;
	}
	
	private void installHandler(Entry<String,Literal> entry, Text myText, TextField newValueText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	try {
		        		myHandler.accept(getExecuteString(newValueText.getText(),entry));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		});
	}
	
	private String getExecuteString(String newValue, Entry<String,Literal> entry) {
		return "MAKE "+entry.getKey()+" "+newValue;
	}

    @Override
    public void update(List<Entry<String, Literal>> arg) {
    	vBox.getChildren().clear();
        for (Entry<String,Literal> entry : arg) {
            addVariableToScreen(entry);
        }
    }

}
