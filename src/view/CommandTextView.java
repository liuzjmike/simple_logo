package view;

import javafx.scene.text.Text;
import model.executable.command.Command;
import util.SLogoObservable;

public class CommandTextView extends SLogoObservable<Command> {
	
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

	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public void setCommand(Command command) {
		this.command = command;
	}

	

}
