package view;

import javafx.scene.text.Text;
import model.executable.command.Command;

import java.util.Observable;

public class CommandTextView extends Observable{
	
	private Text text;
	private Command command;
	
	public CommandTextView(String text, Command command) {
		this.text = new Text(text);
		this.setCommand(command);
		installHandlers();
	}
	
	private void installHandlers() {
		text.setOnMouseClicked(click -> notifyAll());
	}

	public Command getCommand() {
		return command;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	

}
