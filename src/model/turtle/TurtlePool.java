package model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<Collection<Turtle>> {
    
    private Collection<Turtle> myTurtles;
    private int turtleID;
    private double myWidth, myHeight;
    
    public TurtlePool(double width, double height) {
        super();
        myTurtles = new ArrayList<>();
        turtleID = 0;
        addTurtle();
    }
    
    public void setSize(double width, double height) {
        myWidth = width;
        myHeight = height;
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
            turtle.move(dist);
        });
    }
    
    public double setTurtleXY(double x, double y) {
        operateOnTurtles(turtle -> {
            turtle.setXY(x % myWidth, y % myHeight);
        });
        //TODO
        return 0;
    }
    
    public double home() {
        //TODO
        return 0;
    }
    
    public void turnTutle(double degree) {
        operateOnTurtles(turtle -> {
            turtle.turn(degree);
        });
    }
    
    public double setTurtleHeading(double heading) {
        operateOnTurtles(turtle -> {
            turtle.setHeading(heading);
        });
        //TODO
        return 0;
    }
    
    public double turtleTowards(double x, double y) {
        operateOnTurtles(turtle -> {
            turtle.towards(x, y);
        });
        //TODO
        return 0;
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
    
    public double reset() {
        //TODO
        return 0;
    }
    
    public double getHeading() {
        //TODO
        return 0;
    }
    
    public double xCor() {
        //TODO
        return 0;
    }
    
    public double yCor() {
        //TODO
        return 0;
    }
    
    public boolean penDown() {
        //TODO
        return true;
    }
    
    public boolean isVisible() {
        //TODO
        return true;
    }
    
    private void operateOnTurtles(TurtleOperation operation) {
        for(Turtle turtle: myTurtles) {
            operation.execute(turtle);
        }
        notifyObservers(myTurtles);
    }
}
