package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.turtle.info.PoolInfo;
import model.turtle.info.TurtleInfo;
import util.SLogoObserver;

public class PoolView implements SLogoObserver<PoolInfo> {
	public static final String TURTLE_IMAGE = "TurtleImage.png";
	//public static final String DEFAULT_BACKGROUND_COLOR = "white";
	public static final String CSS_FILE = "resources/PaneColor.css";

	private Pane myPane;
	private Map<Integer, TurtleView> myTurtles;
	private LineDrawer lineDrawer;
	
	private Consumer<String> myHandler;	
	private int activeTurtleID = 1;
	
	public PoolView(double width, double height){
		myTurtles = new HashMap<Integer,TurtleView>();
		myPane = new Pane();
		setBackgroundColor(Color.WHITE);
		lineDrawer = new LineDrawer() {

            @Override
            public void addLine(Line line) {
                myPane.getChildren().add(line);
            }

            @Override
            public void removeLines(Collection<Line> lines) {
                myPane.getChildren().removeAll(lines);
            }
		};
		myPane.setPrefWidth(width);
		myPane.setPrefHeight(height);
	}
	
	private void moveActiveTurtle() {		
		for(Integer id: myTurtles.keySet()){
			EventHandler<MouseEvent> onMouseClickedHandler = 
	                new EventHandler<MouseEvent>() {
						@Override
						public void handle(MouseEvent event) {
							activeTurtleID = id;
							myTurtles.get(id).getImageView().setScaleX(1.5);
							myTurtles.get(id).getImageView().setScaleY(1.5);
						}		
			};
			myTurtles.get(id).getImageView().setOnMouseClicked(onMouseClickedHandler);
		}
		for(Integer id: myTurtles.keySet()){
			if(id != activeTurtleID){
				myTurtles.get(id).getImageView().setScaleX(1);
				myTurtles.get(id).getImageView().setScaleY(1);
			}
			else{
				addDragAndDropHandler(myTurtles.get(id).getImageView());
			}			
		}		
	}
	
	private void addDragAndDropHandler(ImageView imageView) {
        EventHandler<MouseEvent> onMouseDraggedHandler = 
                new EventHandler<MouseEvent>() {
        	 
            @Override
            public void handle(MouseEvent t) {
                ((ImageView)(t.getSource())).setX(t.getSceneX()-TurtleView.DEFAULT_WIDTH/2);
                ((ImageView)(t.getSource())).setY(t.getSceneY()-TurtleView.DEFAULT_HEIGHT/2);
                myHandler.accept("ask "+"[ "+activeTurtleID + " ] [ "+"setxy "+(t.getSceneX()-myPane.getPrefWidth()/2)+" "+(-t.getSceneY()+myPane.getPrefHeight()/2)+" ]");
            }
        };
        imageView.setOnMouseDragged(onMouseDraggedHandler);
    }
	
	public void handleKeyInput(KeyCode code){
		 if(code == KeyCode.W){
			 myHandler.accept("ask [ "+activeTurtleID+" ] "+"[ fd "+ 10 + " ]");
		 }
		 else if(code == KeyCode.S){
			 myHandler.accept("ask [ "+activeTurtleID+" ] "+"[ bk "+ 10 + " ]");
		 }
		 else if(code == KeyCode.D){
			 myHandler.accept("ask [ "+activeTurtleID+" ] "+"[ right " + 10 + " ]");
		 }
		 else if(code == KeyCode.A){
			 myHandler.accept("ask [ "+activeTurtleID+" ] "+"[ left "+ 10 + " ]");
		 }
	 }

	public void setHandler(Consumer<String> handler) {
		myHandler = handler;
	}
	
    public void setTurtle(Map<Integer, TurtleInfo> turtles) {
    	for(int key: turtles.keySet()){
    		if(!myTurtles.containsKey(key)){
    			ImageView turtleImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
    			TurtleView turtle = new TurtleView(turtleImage, turtles.get(key),
    			        lineDrawer, myPane.getPrefWidth()/2, myPane.getPrefHeight()/2);
    			myTurtles.put(key, turtle);
        		myPane.getChildren().add(turtleImage);
    		}
    	}
	}
    
    
    public void drawTurtle(){
    	for(Integer id: myTurtles.keySet()){
    		myTurtles.get(id).update();
    	}
    }
    
    public void setBackgroundColor(Color color) {
    	myPane.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}
	
	public Pane getRoot() {
		return myPane;
	}

	@Override
	public void update(PoolInfo arg) {
		setTurtle(arg.getTurtles());
		drawTurtle();
		moveActiveTurtle();
	}
	
	
	public Color getBackgroundColor() {
		return (Color) myPane.getBackground().getFills().get(0).getFill();
	}
}