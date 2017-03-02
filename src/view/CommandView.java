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

public class CommandView extends Observable {
	
	private VBox vBox;
	private ScrollPane myPane;
	private Command activeCommand;
	private SLogoObserver<List<Entry<String, Command>>> myCommandObserver;
	private SLogoObserver<CommandTextView> myCommandTextObserver;
	
	private List<CommandTextView> myCommandTextViews;
	
	public CommandView() {
		myCommandTextViews = new ArrayList<CommandTextView>();
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Commands:\n");
		vBox.getChildren().add(text);
		myCommandTextObserver = new SLogoObserver<CommandTextView>() {

			@Override
			public void update(CommandTextView arg) {
				arg.getCommand();
				notifyObservers();
			}
			
		};
		
		myCommandObserver = arg ->  {
				if(!arg.isEmpty()) {
					Entry<String,Command> entry = arg.get(arg.lastIndexOf(arg));
					CommandTextView myCommandTextView = new CommandTextView(entry.getKey(),entry.getValue());
					myCommandTextViews.add(myCommandTextView);
					addCommandToScreen(myCommandTextView);
					myCommandTextView.addObserver(myCommandTextObserver);
				}
		};
	}
	
	public SLogoObserver<List<Entry<String, Command>>> getCommandObserver() {
		return myCommandObserver;
	}

    private void addCommandToScreen(CommandTextView myCommandTextView) {
    	vBox.getChildren().add(myCommandTextView.getText());
    }

	public Node getNode() {
		return myPane;
	}
	
	public Command getActiveCommand() {
		return activeCommand;
	}
}
