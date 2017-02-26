package model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<Collection<Turtle>> {
    
    private List<Turtle> myTurtles;
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
    
    public double moveTurtle(double dist) {
        operateOnTurtles(turtle -> {
            return turtle.move(dist);
        });
        return dist;
    }
    
    public double setTurtleXY(double x, double y) {
        return operateOnTurtles(turtle -> {
            return turtle.setXY(x % myWidth, y % myHeight);
        });
    }
    
    public double home() {
        return operateOnTurtles(turtle -> {
            return turtle.home();
        });
    }
    
    public double turnTutle(double degree) {
        return operateOnTurtles(turtle -> {
            return turtle.turn(degree);
        });
    }
    
    public double setTurtleHeading(double heading) {
        return operateOnTurtles(turtle -> {
            return turtle.setHeading(heading);
        });
    }
    
    public double turtleTowards(double x, double y) {
        return operateOnTurtles(turtle -> {
            return turtle.towards(x, y);
        });
    }
    
    public void setPen(boolean penDown) {
        operateOnTurtles(turtle -> {
            return turtle.setPen(penDown);
        });
    }

    public void setVisible(boolean isVisible) {
        operateOnTurtles(turtle -> {
            return turtle.setVisible(isVisible);
        });
    }
    
    public double reset() {
        return operateOnTurtles(turtle -> {
            return turtle.reset();
        });
    }
    
    public double getHeading() {
        return getTurtleProperty(turtle -> {
            return turtle.getHeading();
        });
    }
    
    public double xCor() {
        return getTurtleProperty(turtle -> {
            return turtle.getX();
        });
    }
    
    public double yCor() {
        return getTurtleProperty(turtle -> {
            return turtle.getY();
        });
    }
    
    public boolean penDown() {
        return getTurtleProperty(turtle -> {
            return turtle.penDown();
        });
    }
    
    public boolean isVisible() {
        return getTurtleProperty(turtle -> {
            return turtle.isVisible();
        });
    }
    
    private <T> T operateOnTurtles(TurtleOperation<T> operation) {
        validateTurtles();
        T ret = null;
        for(Turtle turtle: myTurtles) {
            ret = operation.execute(turtle);
        }
        notifyObservers(myTurtles);
        return ret;
    }
    
    private <T> T getTurtleProperty(TurtleOperation<T> operation) {
        validateTurtles();
        return operation.execute(myTurtles.get(myTurtles.size() - 1));
    }
    
    private void validateTurtles() {
        if(myTurtles.isEmpty()) {
            throw new RuntimeException();
        }
    }
}
