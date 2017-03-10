package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.info.PaletteInfo;
import util.SLogoObserver;

public class PaletteView implements SLogoObserver<PaletteInfo> {
	
	public static final String[] COLORS = {"White", "Red", "Blue", "Green", "Yellow", "Gray", "Orange", "Indigo"};
	public static final String PEN = "Pen";
	public static final String BG = "Background";
	
    private ScrollPane scrollpane;
    private VBox myBox;
	private Consumer<String> myHandler;
	private String type;
    
    public PaletteView(Consumer<String> handler) {
    	Pane pane = new Pane();
    	RadioButton rb1 = new RadioButton();
    	rb1.setText(PEN);
    	rb1.setOnMouseClicked(e -> type = rb1.getText());
    	RadioButton rb2 = new RadioButton();
    	rb2.setText(BG);
    	rb2.setOnMouseClicked(e -> type = rb2.getText());
    	pane.getChildren().addAll(rb1, rb2);
    	scrollpane = new ScrollPane(pane);
    	myHandler = handler;
    }
    
    @Override
    public void update(PaletteInfo arg) {
        List<Color> colors = populateList(arg);
        setupChoices(colors);
    }
    
    public Node getRoot() {
    	return scrollpane;
    }
    
    private void setupChoices(List<Color> colors) {
    	for(int i=0; i<colors.size(); i++) {
    		HBox hbox = new HBox();
    		Text text = new Text(i + ": ");
    		Rectangle rec = new Rectangle();
    		rec.setFill(colors.get(i));
    		hbox.getChildren().add(text);
    		hbox.getChildren().add(rec);
    		hbox.setOnMouseClicked(e -> myHandler.accept(type));
    		myBox.getChildren().add(hbox);
    	}
    }
    
    private List<Color> populateList(PaletteInfo arg) {
    	List<Color> colors = new ArrayList<>();
    	Map<Integer, Color> map = arg.listColor();
    	for(int i=0; i<map.size(); i++) {
    		colors.add(map.get(i));
    	}
    	return colors;
    }
}
