package model.turtle;

import java.util.ArrayList;
import java.util.List;

public class Turtle {
    
    public static final double RADIAN_PER_DEGREE = Math.PI / 180;
    public static final double ROUND_ANGLE = 360;
    
    private double myX, myY, myHeading;
    private boolean penDown, isVisible;
    private int myID;
    private List<TurtleHist> myTrace;
    private List<TurtleHist> lastMove;
    
    public Turtle(int id) {
        myID = id;
        myX = 0;
        myY = 0;
        myHeading = 0;
        penDown = true;
        isVisible = true;
        myTrace = new ArrayList<TurtleHist>();
        lastMove = new ArrayList<TurtleHist>();
    }
    
    public int getID() {
        return myID;
    }

    public double getX() {
        return myX;
    }
    
    public double getY() {
        return myY;
    }
    
    public double getHeading() {
        return myHeading;
    }
    
    public boolean penDown() {
        return penDown;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    /*****Translational movement*****/
    
    double move(double dist) {
        return move(myX + dist * Math.cos(radianHeading()), myY + dist * Math.sin(radianHeading()));
    }
    
    double setXY(double x, double y) {
        return move(x, y);
    }
    
    double home() {
        myHeading = 0;
        return move(0, 0);
    }
    
    double reset() {
        double ret = home();
        lastMove.clear();
        myTrace.clear();
        return ret;
    }
    
    private double move(double x, double y) {
        double ret = Math.hypot(x - myX, y - myY);
        lastMove.clear();
        TurtleHist newHist = new TurtleHist(penDown(), x, y);
        lastMove.add(newHist);
        myTrace.add(newHist);
        myX = x;
        myY = y;
        return ret;
    }
    
    /*****Rotational movement*****/
    
    double turn(double degree) {
        myHeading = (myHeading + degree) % ROUND_ANGLE;
        return degree;
    }
    
    double setHeading(double heading) {
        double oldHeading = myHeading;
        myHeading = heading;
        return myHeading - oldHeading;
    }
    
    double towards(double x, double y) {
        double oldHeading = myHeading;
        myHeading = Math.atan2(y - myY, x - myX) / RADIAN_PER_DEGREE;
        return myHeading - oldHeading;
    }
    
    boolean setPen(boolean penDown) {
        this.penDown = penDown;
        return penDown;
    }
    
    boolean setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return isVisible;
    }
    
    private double radianHeading() {
        return myHeading * RADIAN_PER_DEGREE;
    }
}
