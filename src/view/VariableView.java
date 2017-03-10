package view;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import util.SLogoObserver;

public class VariableView extends ScrollView implements SLogoObserver<List<Entry<String, Literal>>> {
	
	public VariableView(Consumer<String> guiHandler) {
	    super("Variable", guiHandler);
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
		        	    execute(getExecuteString(newValueText.getText(),entry));
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
    	clear();
        for (Entry<String,Literal> entry : arg) {
            addElement(getVariableText(entry));
        }
    }

}
