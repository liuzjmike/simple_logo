package view;

import java.util.List;
import java.util.Observable;	
import java.util.Observer;
import java.util.Map.Entry;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import model.executable.Literal;
import util.SLogoObserver;

public class VariableView implements Observer,SLogoObserver<List<Entry<String, Literal>>> {

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

	@Override
	public void update(List<Entry<String, Literal>> arg) {
		// TODO Auto-generated method stub
		
	}
}
