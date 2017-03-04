package model.turtle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import util.SLogoObservable;

public class TurtlePool extends SLogoObservable<Collection<TurtleInfo>> {
    
    private List<Turtle> activeTurtles, otherTurtles;
    private int turtleID;
    private double myWidth, myHeight;
    private TurtleInfo myActiveTurtle; //TODO
    
    public TurtlePool(double width, double height) {
        super();
        activeTurtles = new ArrayList<>();
        otherTurtles = new ArrayList<>();
        turtleID = 1;
        myWidth = width;
        myHeight = height;
        tell();
        myActiveTurtle = activeTurtles.get(0);
    }
    
    public double apply(Function<TurtleInfo, Double> function) {
        return function.apply(myActiveTurtle);
    }
    
    public int numActive() {
        return activeTurtles.size();
    }
    
    public void setSize(double width, double height) {
        myWidth = width;
        myHeight = height;
    }
    
    public Collection<TurtleInfo> getTurtles() {
        return Collections.unmodifiableCollection(activeTurtles);
    }
    
    public void tell() {
        activeTurtles.add(new ToroidalTurtle(turtleID++));
        //TODO
        notifyObservers();
    }
    
    public double moveTurtle(double dist) {
        operateOnTurtles(turtle -> {
             return turtle.move(dist, myWidth/2, myHeight/2);
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
        double ret = operateOnTurtles(turtle -> {
            return turtle.reset();
        });
        for(Turtle turtle: activeTurtles) {
            turtle.clearReset();
        }
        return ret;
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
        for(Turtle turtle: activeTurtles) {
            ret = operation.execute(turtle);
        }
        notifyObservers();
        return ret;
    }
    
    private <T> T getTurtleProperty(TurtleOperation<T> operation) {
        validateTurtles();
        return operation.execute(activeTurtles.get(activeTurtles.size() - 1));
    }
    
    private void validateTurtles() {
        if(activeTurtles.isEmpty()) {
            throw new RuntimeException();
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Collection<TurtleInfo> notification() {
        return (Collection<TurtleInfo>)(Collection<?>)Collections.unmodifiableCollection(activeTurtles);
    }
}
