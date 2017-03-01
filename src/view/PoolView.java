package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.turtle.Turtle;
import util.SLogoObserver;

public class PoolView implements SLogoObserver<Collection<Turtle>> {
	public static final String TURTLE_IMAGE = "TurtleImage.png";
	public static final String DEFAULT_BACKGROUND_COLOR = "white";

	private Pane myPane;
	private Map<Integer, TurtleView> myTurtles;
	
	public PoolView(){
		myTurtles = new HashMap<Integer,TurtleView>();
		myPane = new Pane();
		setBackgroundColor(Color.WHITE);			
	}
	
    public void setTurtle(Collection<Turtle> turtles) {
    	for(Turtle turtle: turtles){
    		if(!myTurtles.containsKey(turtle.getID())){
    			ImageView turtleImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
    			myTurtles.put(turtle.getID(), new TurtleView(turtleImage,turtle, myPane));
        		myPane.getChildren().add(turtleImage);
    		}
    		
    	}
	}
    
    public void drawTurtle(){
    	for(Integer id: myTurtles.keySet()){
    		myTurtles.get(id).drawLines();
    	}
    }
    
    //TODO: Create CSS sheet
    public void setBackgroundColor(Color color) {
    	myPane.setStyle("-fx-background-color: white");
    	//myPane.getStylesheets().add(PoolView.class.getResource(""));
	}
	
	public Node getNode() {
		return myPane;
	}

	@Override
	public void update(Collection<Turtle> arg) {
		setTurtle(arg);
		drawTurtle();
	}
}
