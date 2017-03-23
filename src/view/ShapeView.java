package view;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.info.ShapeInfo;

public class ShapeView extends ScrollView implements ShapeInfo {
	
	public static final int SIDE_LENGTH = 30;
	public static final String COMMAND = "SetShape ";
	public static final String[] TURTLE_IMAGES = {"TurtleImage.png", "turtle1.png", "turtle2.png", "turtle3.png", "turtle4.png"};

	private List<ImageView> myShapes;
	
	public ShapeView(Function<String, Double> guiHandler) {
		super("Shapes", "big-container", guiHandler, false);
		myShapes = new ArrayList<>();
		setupShapes();
	}
	
	@Override
	public ImageView getShape(int index) {
		return new ImageView(myShapes.get(index % myShapes.size()).getImage());
	}

	private void setupShapes() {
		for(int i=0; i<TURTLE_IMAGES.length; i++) {
			Text text = new Text(i + ": ");
			ImageView image = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGES[i])));
			image.setId("shape-legend");
			image.setFitHeight(SIDE_LENGTH);
			image.setFitWidth(SIDE_LENGTH);
			myShapes.add(image);
            HBox hbox = new HBox(text, image);
			int index = i;
			hbox.setOnMouseClicked(e -> execute(COMMAND + index));
			addElement(hbox);
		}
	}
}
