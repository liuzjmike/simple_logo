package view;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.turtle.Turtle;
import util.SLogoObservable;
import util.SLogoObserver;

public class PoolView implements SLogoObserver<Collection<Turtle>> {

    public void setTurtle(Collection<Turtle> turtles) {
	}
    
    public void setTurtleImage(ImageView image) {
	}
    
    public void setBackgroundColor(Color color) {
	}
	
	public Node getNode() {
		return new TextArea("This is PoolView");
	}

	@Override
	public void update(Collection<Turtle> arg) {
		// TODO Auto-generated method stub
		
	}
}
