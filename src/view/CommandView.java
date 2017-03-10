package view;

import java.util.Map;

import controller.StringProcessor;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.executable.command.Command;
import util.SLogoObserver;

public class CommandView implements SLogoObserver<Map<String, Command>> {
	
	private VBox vBox;
	private ScrollPane myPane;
	
	private StringProcessor myHandler;
	
	public CommandView() {
		vBox = new VBox();
		myPane = new ScrollPane(vBox);
		Text text = new Text("Commands:\n");
		vBox.getChildren().add(text);
	}
    
    public void setHandler(StringProcessor handler) {
        myHandler = handler;
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

	public Node getNode() {
		return myPane;
	}
	
    @Override
    public void update(Map<String, Command> arg) {
    	vBox.getChildren().clear();
    	arg.keySet().stream().sorted().forEach(s -> addCommandToScreen(s));
    }
}
