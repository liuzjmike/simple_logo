package model.turtle;

import java.util.Collection;

public interface TurtlePool { //Observable
    
    Collection<Turtle> getTurtles();
    
    public void moveTurtle(double dist);
    
    public void turnTutle(double degree);
    
    public void setPen(boolean penDown);
    
    public void setVisible(boolean isVisible);
}
