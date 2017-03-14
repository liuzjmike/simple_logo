package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
	public static final String COMMAND = "Set%s %d";
	
	private String type;
	private ScrollView myColors;
	private PaletteInfo myPalette;
    
    public PaletteView(Function<String, Double> handler) {
    	super("Palette", new VBox(), handler);
    	myColors = new ScrollView("Colors", handler, false);
    	getRoot().getChildren().addAll(createChoice(), myColors.getRoot());
    	getRoot().setId("big-container");
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
    	RadioButton penRB = createRadioButton(PEN, group);
    	penRB.setSelected(true);
    	type = penRB.getText();
    	HBox hbox = new HBox(penRB, createRadioButton(BG, group));
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
    		Text text = new Text(i + ": ");
    		Rectangle rec = new Rectangle(100, 20);
    		rec.setFill(colors.get(i));
            HBox hbox = new HBox(text, rec);
            hbox.setId("big-container");
    		final int index = i;
    		hbox.setOnMouseClicked(e -> execute(String.format(COMMAND, type, index)));
    		hbox.setId("text-color");
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
