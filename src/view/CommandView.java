package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import controller.ControlHandler;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.command.Command;
import util.SLogoObservable;
import util.SLogoObserver;

public class CommandView {
	
	private VBox vBox;
	private ScrollPane myPane;
	private SLogoObserver<List<Entry<String, Command>>> myCommandObserver;
	
	private ControlHandler myHandler;
	
	public CommandView() {
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Commands:\n");
		vBox.getChildren().add(text);

		myCommandObserver = arg -> {
			//body of update()
			for (Entry<String,Command> entry : arg) {
				addCommandToScreen(entry.getKey());
			}
		};
	}
	
	public void setHandler(ControlHandler myHandler) {
		this.myHandler = myHandler;
	}
	
	private void installHandler(Text myText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	try {
						myHandler.execute(myText.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        }
		});
	}

    public void addCommandToScreen(String myCommand) {
    	Text myCommandText = new Text(myCommand);
    	installHandler(myCommandText);
    	vBox.getChildren().add(myCommandText);
    }
    
    public SLogoObserver<List<Entry<String, Command>>> getCommandObserver() {
    	return myCommandObserver;
    }

	public Node getNode() {
		return myPane;
	}
}
