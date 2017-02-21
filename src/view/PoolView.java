package view;

import java.util.Collection;
import java.util.Observer;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.turtle.Turtle;

public interface PoolView extends Observer {

    public void setTurtle(Collection<Turtle> turtles);
    
    public void setTurtleImage(ImageView image);
    
    public void setBackgroundColor(Color color);
}
