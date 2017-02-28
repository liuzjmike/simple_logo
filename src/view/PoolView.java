package view;

import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.turtle.Turtle;
import util.SLogoObserver;

public class PoolView implements SLogoObserver<Collection<Turtle>> {

    public void setTurtle(Collection<Turtle> turtles) {
	}
    
    public void setTurtleImage(ImageView image) {
	}
    
    public void setBackgroundColor(Color color) {
	}
	
	public Node getNode() {
		return new Rectangle();
	}

	@Override
	public void update(Collection<Turtle> arg) {
		// TODO Auto-generated method stub
		
	}
}
