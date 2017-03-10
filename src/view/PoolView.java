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
import model.info.PoolInfo;
import model.info.TurtleInfo;
import util.SLogoObserver;

public class PoolView extends View<Pane> implements SLogoObserver<PoolInfo> {
    public static final String TURTLE_IMAGE = "TurtleImage.png";
    //public static final String DEFAULT_BACKGROUND_COLOR = "white";
    public static final String CSS_FILE = "resources/PaneColor.css";

    private Map<Integer, TurtleView> myTurtles;
    private LineDrawer lineDrawer;

    private Consumer<String> myHandler;

    private int activeTurtleID;

    public PoolView(double width, double height, Consumer<String> guiHandler){
        super("Pool", new Pane());
        myHandler = guiHandler;
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

    public void setTurtle(Map<Integer, TurtleInfo> turtles) {
        for(int key: turtles.keySet()){
            if(!myTurtles.containsKey(key)){
                ImageView turtleImage = new ImageView(new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
                TurtleView turtle = new TurtleView(turtleImage, turtles.get(key),
                        lineDrawer, getRoot().getPrefWidth()/2, getRoot().getPrefHeight()/2, myHandler);
                myTurtles.put(key, turtle);
                getRoot().getChildren().add(turtleImage);
            }
        }
    }


    public void drawTurtle(){
        for(Integer id: myTurtles.keySet()){
            myTurtles.get(id).update();
        }
    }

    public void setBackgroundColor(Color color) {
        getRoot().setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    @Override
    public void update(PoolInfo arg) {
        setTurtle(arg.getTurtles());
        drawTurtle();
        moveActiveTurtle();
    }


    public Color getBackgroundColor() {
        return (Color) getRoot().getBackground().getFills().get(0).getFill();
    }
}