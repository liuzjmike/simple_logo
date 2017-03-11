package view;

import java.util.List;
import java.util.function.Function;

import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import util.SLogoObserver;

public class CommandView extends ScrollView implements SLogoObserver<List<String>> {
	
	public CommandView(Function<String, Double> guiHandler) {
	    super("Command", guiHandler);
	}
	
	private void installHandler(Text myText) {
		myText.addEventHandler(MouseEvent.MOUSE_PRESSED, e -> execute(myText.getText()));
	}

    public void addCommandToScreen(String myCommand) {
    	Text myCommandText = new Text(myCommand);
    	installHandler(myCommandText);
    	addElement(myCommandText);
    }
	
    @Override
    public void update(List<String> arg) {
    	clear();
    	arg.forEach(s -> addCommandToScreen(s));
    }
}
