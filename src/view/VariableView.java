package view;
import java.util.List;
import java.util.Map.Entry;
import java.util.function.Function;

import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import util.SLogoObserver;

public class VariableView extends ScrollView implements SLogoObserver<List<Entry<String, Double>>> {
    
    public static final String MAKE = "Make %s %s";
	
	public VariableView(Function<String, Double> guiHandler) {
	    super("Variable", guiHandler, false);
	}

    @Override
    public void update(List<Entry<String, Double>> arg) {
        clear();
        for (Entry<String,Double> entry : arg) {
            addElement(getVariableText(entry));
        }
    }
	
	private HBox getVariableText(Entry<String, Double> entry) {
		Text text = new Text(entry.getKey());
		TextField tf = new TextField(Double.toString(entry.getValue()));
		tf.setOnAction(e -> execute(String.format(MAKE, text.getText(), tf.getText())));
		HBox ret = new HBox(text, tf);
		ret.setId("element");
		return ret;
	}

}
