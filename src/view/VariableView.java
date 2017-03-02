package view;
import java.util.List;
import java.util.Map.Entry;

import controller.ControlHandler;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import util.SLogoObserver;

public class VariableView{
	
	VBox vBox;
	ScrollPane myPane;
	
	ControlHandler myHandler;
	
	private SLogoObserver<List<Entry<String, Literal>>> myVariableObserver;
	
	public VariableView() {
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Variables:\n");
		vBox.getChildren().add(text);
		
		myVariableObserver = arg -> {
			//body of update()
			for (Entry<String,Literal> entry : arg) {
				addVariableToScreen(entry);
			}
		};
	}
	
	public void setHandler(ControlHandler myHandler) {
		this.myHandler = myHandler;
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

}
