package model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<Collection<Turtle>> {
    
    private Collection<Turtle> myTurtles;
    
    public TurtlePool() {
        myTurtles = new ArrayList<>();
    }
    
    Collection<Turtle> getTurtles() {
        return Collections.unmodifiableCollection(myTurtles);
    }
    
    public void moveTurtle(double dist) {
        
    }
    
    public void turnTutle(double degree) {
        
    }
    
    public void setPen(boolean penDown) {
        
    }
    
    public void setVisible(boolean isVisible) {
        
    }
}
