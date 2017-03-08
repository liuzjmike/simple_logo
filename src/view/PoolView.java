package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import controller.StringProcessor;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
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
	public static final String DEFAULT_BACKGROUND_COLOR = "white";
	public static final String CSS_FILE = "resources/PaneColor.css";

	private Pane myPane;
	private Map<Integer, TurtleView> myTurtles;
	private LineDrawer lineDrawer;
	
	private StringProcessor myHandler;
	
	double orgSceneX,orgSceneY;
	double orgTranslateX, orgTranslateY;
	
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
	
	public void setHandler(StringProcessor handler) {
		myHandler = handler;
	}
	
    public void setTurtle(Map<Integer, TurtleInfo> turtles) {
    	for(int key: turtles.keySet()){
    		if(!myTurtles.containsKey(key)){
    			ImageView turtleImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
    			TurtleView turtle = new TurtleView(turtleImage, turtles.get(key),
    			        lineDrawer, myPane.getPrefWidth()/2, myPane.getPrefHeight()/2,myHandler);
    			addDragAndDropHandler(turtle.getImageView());
    			myTurtles.put(key, turtle);
        		myPane.getChildren().add(turtleImage);
    		}
    	}
	}
    
    private void addDragAndDropHandler(ImageView imageView) {
    	
    	EventHandler<MouseEvent> onMousePressedHandler = new EventHandler<MouseEvent>() {
    		 
            @Override
            public void handle(MouseEvent t) {
                orgSceneX = t.getSceneX();
                orgSceneY = t.getSceneY();
                orgTranslateX = ((ImageView)(t.getSource())).getTranslateX();
                orgTranslateY = ((ImageView)(t.getSource())).getTranslateY();
            }
        };
        
        EventHandler<MouseEvent> onMouseDraggedHandler = 
                new EventHandler<MouseEvent>() {
        	 
            @Override
            public void handle(MouseEvent t) {
                double offsetX = t.getSceneX() - orgSceneX;
                double offsetY = t.getSceneY() - orgSceneY;
                double newTranslateX = orgTranslateX + offsetX;
                double newTranslateY = orgTranslateY + offsetY;
                 
                ((ImageView)(t.getSource())).setTranslateX(newTranslateX);
                ((ImageView)(t.getSource())).setTranslateY(newTranslateY);
                
                
            }
        };
        
        EventHandler<MouseEvent> onMouseReleasedHandler = 
        		new EventHandler<MouseEvent>() {

					@Override
					public void handle(MouseEvent t) {
						double x = imageView.getX();
						double y = imageView.getY();
						myHandler.execute("setxy "+x+" "+y);
						
					}
        	
        };
        
        imageView.setOnMousePressed(onMousePressedHandler);
        imageView.setOnMouseDragged(onMouseDraggedHandler);
        imageView.setOnMouseReleased(onMouseReleasedHandler);
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
	}
	
	public Color getBackgroundColor() {
		return (Color) myPane.getBackground().getFills().get(0).getFill();
	}
	

}