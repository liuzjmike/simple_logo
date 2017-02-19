package model.turtle;

import java.util.Collection;

public interface TurtlePool { //Observable
    
    Collection<Turtle> getTurtles();
    
    void moveTurtle(double dist);
    
    void turnTutle(double degree);
    
    void setPen(boolean penDown);
    
    void setVisible(boolean isVisible);
}
