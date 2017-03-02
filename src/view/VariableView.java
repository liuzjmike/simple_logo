package view;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;	
import java.util.Observer;
import java.util.Map.Entry;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.Literal;
import model.executable.Variable;
import model.executable.command.Command;
import util.SLogoObserver;

public class VariableView extends Observable implements SLogoObserver<List<Entry<String, Literal>>> {
	
	VBox vBox;
	ScrollPane myPane;
	private Literal activeVariable;
	
	public VariableView() {
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Variables:\n");
		vBox.getChildren().add(text);
	}

	public Node getNode() {
		return myPane;
	}

	@Override
	public void update(List<Entry<String, Literal>> arg) {
	    Entry<String, Literal> entry = arg.get(arg.lastIndexOf(arg));
        vBox.getChildren().add(getVariableText(entry));
	}
	
	public Text getVariableText(Entry<String,Literal> entry) {
		Text variableText = new Text(entry.getKey());
		variableText.setOnMouseClicked(click -> variableClicked(entry.getValue()));
		return variableText;
	}
	
	private void variableClicked(Literal myLiteral) {
		activeVariable = myLiteral;
		notifyObservers();
	}
	
	public Literal getActiveVariable() {
		return activeVariable;
	}

	public void setActiveVariable(Literal activeVariable) {
		this.activeVariable = activeVariable;
	}
}
