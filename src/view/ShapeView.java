package view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ShapeView extends ScrollView {
	
	public static final String[] TURTLE_IMAGES = {"TurtleImage.png", "turtle1.png", "turtle2.png", "turtle3.png", "turtle4.png"};

	private List<ImageView> myShapes;
	
	public ShapeView(Consumer<String> guiHandler) {
		super("Shapes", guiHandler);
		myShapes = new ArrayList<>();
		setupShapes();
	}
	
	public List<ImageView> getShapes() {
		return myShapes;
	}
	
	private void setupShapes() {
		for(int i=0; i<TURTLE_IMAGES.length; i++) {
			HBox hbox = new HBox();
			Text text = new Text(i + ": ");
			ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGES[i])));
			image.setId("shape-legend");
			image.setFitHeight(30);
			image.setFitWidth(30);
			myShapes.add(image);
			hbox.getChildren().add(text);
			hbox.getChildren().add(image);
			addElement(hbox);
		}
	}

}
