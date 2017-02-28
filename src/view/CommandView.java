package view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import model.executable.command.Command;
import util.SLogoObserver;

public class CommandView extends Observable implements SLogoObserver<List<Entry<String, Command>>>, Observer{
	
	private Region myNode;
	private Command activeCommand;
	
	
	public CommandView() {
		
	}

    public void addCommand(String command) {
    	
    }

	public Node getNode() {
		// TODO Auto-generated method stub
		return myNode;
	}

	@Override
	public void update(List<Entry<String, Command>> arg) {
		
		
	}
	
	public Command getActiveCommand() {
		return activeCommand;
	}

	@Override
	public void update(Observable o, Object arg) {
		Command command = ar
		notifyAll();
		
	}
}
