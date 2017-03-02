package view;
import java.util.List;
import java.util.Map.Entry;

import controller.StringProcessor;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import util.SLogoObserver;

public class VariableView implements SLogoObserver<List<Entry<String, Literal>>> {
	
	VBox vBox;
	ScrollPane myPane;
	
	StringProcessor myHandler;
	
	public VariableView() {
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Variables:\n");
		vBox.getChildren().add(text);
	}
    
    public void setHandler(StringProcessor handler) {
        myHandler = handler;
    }

	public Node getNode() {
		return myPane;
	}
	private void addVariableToScreen(Entry<String,Literal> entry) {
		Text myText = getVariableText(entry);
		vBox.getChildren().add(myText);
	}
	
	private Text getVariableText(Entry<String,Literal> entry) {
		Text variableText = new Text(entry.getKey()+" = " +Double.toString(entry.getValue().getValue()));
		installHandler(entry,variableText);
		return variableText;
	}
	
	private void installHandler(Entry<String,Literal> entry, Text myText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	try {
		        		myHandler.execute(getExecuteString(entry));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		});
	}
	
	private String getExecuteString(Entry<String,Literal> entry) {
		return "MAKE "+Double.toString(entry.getValue().getValue())+" "+entry.getKey();
	}

    @Override
    public void update(List<Entry<String, Literal>> arg) {
        for (Entry<String,Literal> entry : arg) {
            addVariableToScreen(entry);
        }
    }

}
