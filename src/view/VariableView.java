package view;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Consumer;

import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import util.SLogoObserver;

public class VariableView extends ScrollView implements SLogoObserver<List<Entry<String, Double>>> {
	
	public VariableView(Consumer<String> guiHandler) {
	    super("Variable", guiHandler);
	}

    @Override
    public void update(List<Entry<String, Double>> arg) {
        clear();
        for (Entry<String,Double> entry : arg) {
            addElement(getVariableText(entry));
        }
    }
	
	private HBox getVariableText(Entry<String, Double> entry) {
		HBox myHBox = new HBox();
		Text text = new Text(entry.getKey()+" = ");
		TextField tf = new TextField(Double.toString(entry.getValue()));
		myHBox.getChildren().addAll(text,tf);
		installHandler(entry,text,tf);
		return myHBox;
	}
	
	private void installHandler(Entry<String,Double> entry, Text myText, TextField newValueText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> execute(myText.getText()));
	}
	
	private String getExecuteString(String newValue, Entry<String,Literal> entry) {
		return "MAKE "+entry.getKey()+" "+newValue;
	}

}
