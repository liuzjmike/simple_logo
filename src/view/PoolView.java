package view;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.turtle.Turtle;
import util.SLogoObserver;

public class PoolView implements Observer,SLogoObserver<Collection<Turtle>>  {

    public void setTurtle(Collection<Turtle> turtles) {
	}
    
    public void setTurtleImage(ImageView image) {
	}
    
    public void setBackgroundColor(Color color) {
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	public Node getNode() {
		return new Rectangle();
	}

	@Override
	public void update(Collection<Turtle> arg) {
		// TODO Auto-generated method stub
		
	}
}
