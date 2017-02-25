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

public class PoolView implements Observer {

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
		return new TextArea("This is PoolView");
	}
}
