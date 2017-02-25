package model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<Collection<Turtle>> {
    
    private Collection<Turtle> myTurtles;
    private int turtleID;
    
    public TurtlePool() {
        super();
        myTurtles = new ArrayList<>();
        turtleID = 0;
        addTurtle();
    }
    
    public Collection<Turtle> getTurtles() {
        return Collections.unmodifiableCollection(myTurtles);
    }
    
    public void addTurtle() {
        myTurtles.add(new Turtle(turtleID++));
        notifyObservers(myTurtles);
    }
    
    public void moveTurtle(double dist) {
        operateOnTurtles(turtle -> {
            turtle.setX(turtle.getX() + dist * Math.cos(turtle.getHeading()));
            turtle.setY(turtle.getY() - dist * Math.sin(turtle.getHeading()));
        });
    }
    
    public void turnTutle(double degree) {
        operateOnTurtles(turtle -> {
            turtle.setHeading((turtle.getHeading() + degree) % (2 * Math.PI));
        });
    }
    
    public void setPen(boolean penDown) {
        operateOnTurtles(turtle -> {
            turtle.setPen(penDown);
        });
    }
    
    public void setVisible(boolean isVisible) {
        operateOnTurtles(turtle -> {
            turtle.setVisible(isVisible);
        });
    }
    
    private void operateOnTurtles(TurtleOperation operation) {
        for(Turtle turtle: myTurtles) {
            operation.execute(turtle);
        }
        notifyObservers(myTurtles);
    }
}
