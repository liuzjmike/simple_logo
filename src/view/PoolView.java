package view;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
import javafx.stage.Stage;
import model.info.PoolInfo;
import model.info.TurtleInfo;
import util.SLogoObserver;

public class PoolView extends View<Pane> implements SLogoObserver<PoolInfo> {

	public static final String TURTLE_IMAGE = "TurtleImage.png";
	public static final String ASK_MOVECOMMAND = "ask [ %d ] [ %s %d ]";
	public static final String ASK_SETXYCOMMAND = "ask [ %d ] [ SetPosition %f %f ]";
	public static final String FORWARD_COMMAND = "Forward";
	public static final String BACKWARD_COMMAND = "Backward";
	public static final String TURNRIGHT_COMMAND = "Right";
	public static final String TURNLEFT_COMMAND = "Left";
	public static final int DEFALUT_STEP = 10;

	private Map<Integer, TurtleView> myTurtles;
	private LineDrawer lineDrawer;

	private ViewSupplier myViewSupplier;
	
    private int activeTurtleID;

    public PoolView(double width, double height, Function<String, Double> guiHandler, ViewSupplier viewSupplier) {
        super("Pool", new Pane(), guiHandler);
    	myViewSupplier = viewSupplier;
        myTurtles = new HashMap<Integer,TurtleView>();
        setBackgroundColor(Color.WHITE);
        activeTurtleID = 1;
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
		for (Integer id : myTurtles.keySet()) {
			myTurtles.get(id).getImageView().setOnMouseClicked(e -> handleClick(e, id));
		}
	}

	private void handleClick(MouseEvent event, Integer id) {
		activeTurtleID = id;
		myTurtles.get(id).getImageView().setScaleX(1.5);
		myTurtles.get(id).getImageView().setScaleY(1.5);
		for (Integer Id : myTurtles.keySet()) {
			if (Id != activeTurtleID) {
				myTurtles.get(Id).getImageView().setScaleX(1);
				myTurtles.get(Id).getImageView().setScaleY(1);
			}
		}
		myTurtles.get(id).getImageView().setOnMouseDragged(e -> handleMouseInput(e));
	}

	public void handleMouseInput(MouseEvent t) {
		((ImageView) (t.getSource())).setX(t.getSceneX() - TurtleView.DEFAULT_WIDTH / 2);
		((ImageView) (t.getSource())).setY(t.getSceneY() - TurtleView.DEFAULT_HEIGHT / 2);
		execute(String.format(ASK_SETXYCOMMAND, activeTurtleID, (t.getSceneX() - getRoot().getPrefWidth() / 2),
				(-t.getSceneY() + getRoot().getPrefHeight() / 2)));
	}

	public void handleKeyInput(KeyCode code, Stage stage) {
		String command = new String();
		if (code == KeyCode.W) {
			command = FORWARD_COMMAND;
		} else if (code == KeyCode.S) {
			command = BACKWARD_COMMAND;
		} else if (code == KeyCode.D) {
			command = TURNRIGHT_COMMAND;
		} else if (code == KeyCode.A) {
			command = TURNLEFT_COMMAND;
		} else if (code == KeyCode.I) {
			myTurtles.get(activeTurtleID).setPopUp(activeTurtleID,stage);		
			return;
		} else {
		    return;
		}
		execute(String.format(ASK_MOVECOMMAND, activeTurtleID, command, DEFALUT_STEP));
	}
	
	public void handleRelease(KeyCode code, Stage stage){
		if(code == KeyCode.I){
			myTurtles.get(activeTurtleID).hidePopUp(stage);
		}
	}

	public void setTurtle(Map<Integer, TurtleInfo> turtles) {
		for (int key : turtles.keySet()) {
			if (!myTurtles.containsKey(key)) {
				ImageView turtleImage = new ImageView(
						new Image(getClass().getClassLoader().getResourceAsStream(TURTLE_IMAGE)));
				TurtleView turtle = new TurtleView(turtleImage, turtles.get(key), lineDrawer,
						getRoot().getPrefWidth() / 2, getRoot().getPrefHeight() / 2);
				myTurtles.put(key, turtle);
				getRoot().getChildren().add(turtleImage);
			}
		}
	}

	public void setBackgroundColor(Color color) {
		getRoot().setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
	}
    
    public void drawTurtle(){
        for(Integer id: myTurtles.keySet()){
            myTurtles.get(id).update(myViewSupplier.getPalette(), myViewSupplier.getShapes());
        }
    }

    @Override
    public void update(PoolInfo arg) {
        setBackgroundColor(myViewSupplier.getPalette().getColor(arg.getBackground()));
        setTurtle(arg.getTurtles());
        drawTurtle();
        moveActiveTurtle();
    }


    public Color getBackgroundColor() {
        return (Color) getRoot().getBackground().getFills().get(0).getFill();
    }
}