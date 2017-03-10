package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Palette;
import model.info.PaletteInfo;
import util.SLogoObserver;

public class PaletteView extends View<VBox> implements SLogoObserver<PaletteInfo> {
	
	public static final String PEN = "PenColor";
	public static final String BG = "Background";
	
	private Consumer<String> myHandler;
	private String type;
	private ScrollView myColors;
	private PaletteInfo myPalette;
    
    public PaletteView(Consumer<String> handler) {
    	super("Palette", new VBox());
    	myColors = new ScrollView("Colors");
    	getRoot().getChildren().addAll(createChoice(), myColors.getRoot());
    	getRoot().setId("big-container");
    	myHandler = handler;
    	myPalette = new Palette();
    }
    
    @Override
    public void update(PaletteInfo palette) {
    	myPalette = palette;
        setupChoices(populateList(palette));
    }
    
    public PaletteInfo getPalette() {
    	return myPalette;
    }
    
    private HBox createChoice() {
    	ToggleGroup group = new ToggleGroup();
    	HBox hbox = new HBox(createRadioButton(PEN, group), createRadioButton(BG, group));
    	hbox.setId("radio-button");
    	return hbox;
    }
    
    private RadioButton createRadioButton(String text, ToggleGroup group) {
     	RadioButton rb = new RadioButton(text);
    	rb.setOnMouseClicked(e -> type = rb.getText());
    	rb.setToggleGroup(group);
    	return rb;
    }
    
    private void setupChoices(List<Color> colors) {
    	myColors.clear();
    	for(int i=0; i<colors.size(); i++) {
    		HBox hbox = new HBox();
    		Text text = new Text(i + ": ");
    		Rectangle rec = new Rectangle(20, 20);
    		rec.setFill(colors.get(i));
    		hbox.getChildren().add(text);
    		hbox.getChildren().add(rec);
    		int index = i;
    		hbox.setOnMouseClicked(e -> myHandler.accept("Set" + type + " " + index));
    		myColors.addElement(hbox);
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
