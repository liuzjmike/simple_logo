package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Map.Entry;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.command.Command;
import util.SLogoObserver;

public class CommandView extends Observable implements SLogoObserver<List<Entry<String, Command>>>, Observer{
	
	private VBox vBox;
	private ScrollPane myPane;
	private Command activeCommand;
	
	private List<CommandTextView> myCommandTextViews;
	
	public CommandView() {
		myCommandTextViews = new ArrayList<CommandTextView>();
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Commands:\n");
		vBox.getChildren().add(text);
	}

    private void addCommandToScreen(CommandTextView myCommandTextView) {
    	vBox.getChildren().add(myCommandTextView.getText());
    }

	public Node getNode() {
		return myPane;
	}

	/* (non-Javadoc)
	 * @see util.SLogoObserver#update(java.lang.Object)
	 * Observer update for when command is added to CommandView
	 */
	@Override
	public void update(List<Entry<String, Command>> arg) {
		if(!arg.isEmpty()){
			Entry<String,Command> entry = arg.get(arg.lastIndexOf(arg));
			CommandTextView myCommandTextView = new CommandTextView(entry.getKey(),entry.getValue());
			myCommandTextViews.add(myCommandTextView);
			addCommandToScreen(myCommandTextView);
			myCommandTextView.addObserver(this);
		}	
	}
	
	public Command getActiveCommand() {
		return activeCommand;
	}

	/**
	 * Observer update for when textViews get clicked
	 * @param o
	 * @param arg
	 */
	@Override
	public void update(Observable o, Object arg) {
		CommandTextView myCommandTextView = (CommandTextView) o;
		myCommandTextView.getCommand();
		notifyObservers();
	}
}
