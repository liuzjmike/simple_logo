package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

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
import model.info.PaletteInfo;
import model.info.PoolInfo;
import model.info.TurtleInfo;
import util.SLogoObserver;

public class PoolView extends View<Pane> implements SLogoObserver<PoolInfo> {
    public static final String TURTLE_IMAGE = "TurtleImage.png";
    public static final String ASK_MOVECOMMAND = "ask [ %d ] [ %s %d ]";
    public static final String ASK_SETXYCOMMAND = "ask [ %d ] [ setxy %f %f ]";
    public static final String FORWARD_COMMAND = "fd";
    public static final String BACKWARD_COMMAND = "bk";
    public static final String TURNRIGHT_COMMAND = "right";
    public static final String TURNLEFT_COMMAND = "left";
    public static final int DEFALUT_STEP = 10;
    
    private Map<Integer, TurtleView> myTurtles;
    private LineDrawer lineDrawer;

    private Consumer<String> myHandler;
	private Supplier<PaletteInfo> myPaletteSupplier;

    private int activeTurtleID;

    public PoolView(double width, double height, Consumer<String> guiHandler, Supplier<PaletteInfo> paletteSupplier) {
        super("Pool", new Pane());
        myHandler = guiHandler;
    	myPaletteSupplier = paletteSupplier;
        myTurtles = new HashMap<Integer,TurtleView>();
        setBackgroundColor(Color.WHITE);
        lineDrawer = new LineDrawer() {

            @Override
            public void addLine(Line line) {
                getRoot().getChildren().add(line);
            }

            @Override
            public void removeLines(Collection<Line> lines) {
                getRoot().getChildren().removeAll(lines);
            }
        };
        getRoot().setPrefWidth(width);
        getRoot().setPrefHeight(height);
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
							addDragAndDropHandler(myTurtles.get(id).getImageView());
						}		
			};
			myTurtles.get(id).getImageView().setOnMouseClicked(onMouseClickedHandler);
			
		}
		for(Integer id: myTurtles.keySet()){
			if(id != activeTurtleID){
				myTurtles.get(id).getImageView().setScaleX(1);
				myTurtles.get(id).getImageView().setScaleY(1);
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
                myHandler.accept(String.format(ASK_SETXYCOMMAND,activeTurtleID,(t.getSceneX()-getRoot().getPrefWidth()/2),(-t.getSceneY()+getRoot().getPrefHeight()/2)));
            }
        };
        imageView.setOnMouseDragged(onMouseDraggedHandler);
    }
	
	public void handleKeyInput(KeyCode code){
		 if(code == KeyCode.W){
			 myHandler.accept(String.format(ASK_MOVECOMMAND,activeTurtleID,FORWARD_COMMAND,DEFALUT_STEP));
		 }
		 else if(code == KeyCode.S){
			 myHandler.accept(String.format(ASK_MOVECOMMAND,activeTurtleID,BACKWARD_COMMAND,DEFALUT_STEP));
		 }
		 else if(code == KeyCode.D){
			 myHandler.accept(String.format(ASK_MOVECOMMAND,activeTurtleID,TURNRIGHT_COMMAND,DEFALUT_STEP));
		 }
		 else if(code == KeyCode.A){
			 myHandler.accept(String.format(ASK_MOVECOMMAND,activeTurtleID,TURNLEFT_COMMAND,DEFALUT_STEP));
		 }
	 }
	
    public void setTurtle(Map<Integer, TurtleInfo> turtles) {
    	for(int key: turtles.keySet()){
    		if(!myTurtles.containsKey(key)){
    			ImageView turtleImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
    			TurtleView turtle = new TurtleView(turtleImage, turtles.get(key),
    			        lineDrawer, getRoot().getPrefWidth()/2, getRoot().getPrefHeight()/2);
    			myTurtles.put(key, turtle);
        		getRoot().getChildren().add(turtleImage);
    		}
    	}
	}
    
    public void drawTurtle(){
        for(Integer id: myTurtles.keySet()){
            myTurtles.get(id).update(myPaletteSupplier.get());
        }
    }

    public void setBackgroundColor(Color color) {
        getRoot().setBackground(new Background(
                new BackgroundFill(
                        color,
                        CornerRadii.EMPTY,
                        Insets.EMPTY)));
    }

    @Override
    public void update(PoolInfo arg) {
        setBackgroundColor(myPaletteSupplier.get().getColor(arg.getBackground()));
        setTurtle(arg.getTurtles());
        drawTurtle();
        moveActiveTurtle();
    }


    public Color getBackgroundColor() {
        return (Color) getRoot().getBackground().getFills().get(0).getFill();
    }
}