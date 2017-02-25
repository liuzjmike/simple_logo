package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.TextArea;

public class CommandView implements Observer{

    public void addCommand(String command) {
    	
    }

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	public Node getNode() {
		// TODO Auto-generated method stub
		return new TextArea("This is CommandView");
	}
}
