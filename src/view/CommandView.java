package view;

import java.util.Map;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import model.executable.command.Command;
import util.SLogoObserver;

public class CommandView extends ScrollView implements SLogoObserver<Map<String, Command>> {
	
	public CommandView(Consumer<String> guiHandler) {
	    super("Command", guiHandler);
	}
	
	private void installHandler(Text myText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, 
		    new EventHandler<MouseEvent>() {
		        public void handle(MouseEvent e) {
		        	try {
		        	    execute(myText.getText());
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
    	addElement(myCommandText);
    }
	
    @Override
    public void update(Map<String, Command> arg) {
    	clear();
    	arg.keySet().stream().sorted().forEach(s -> addCommandToScreen(s));
    }
}
