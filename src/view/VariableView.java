package view;

import java.util.Observable;	
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class VariableView implements Observer {

    public void addVariable(String variable) {
    	
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Node getNode() {
		// TODO Auto-generated method stub
		return new TextArea("This is VariableView");
	}
}
