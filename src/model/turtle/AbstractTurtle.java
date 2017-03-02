package model.turtle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Constants;

public abstract class AbstractTurtle implements Turtle {

    private double myX;
    private double myY;
    private double myHeading;
    private boolean penDown;
    private boolean isVisible, isReset;
    private int myID;
    private List<TurtleHist> lastMove;

    public AbstractTurtle(int id) {
        myID = id;
        myX = 0;
        myY = 0;
        myHeading = 0;
        penDown = true;
        isVisible = true;
        lastMove = new ArrayList<TurtleHist>();
        lastMove.add(new TurtleHist(myX, myY, penDown));
    }

    @Override
    public int getID() {
        return myID;
    }

    @Override
    public double getX() {
        return myX;
    }

    @Override
    public double getY() {
        return myY;
    }

    @Override
    public double getHeading() {
        return myHeading;
    }

    @Override
    public List<TurtleHist> getLastMove() {
        return Collections.unmodifiableList(lastMove);
    }

    @Override
    public boolean penDown() {
        return penDown;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }
    
    @Override
    public boolean isReset() {
        return isReset;
    }

    /*****Translational movement*****/
    double move(double dist, double wRadius, double hRadius) {
        clearHist();
        move(dist * Math.cos(radianHeading()), dist * Math.sin(radianHeading()), wRadius, hRadius);
        return dist;
    }
    
    protected abstract void move(double dx, double dy, double wRadius, double hRadius);
    
    protected double moveOn(double x, double y, boolean penDown) {
        double ret = Math.hypot(x - myX, y - myY);
        moveOn(new TurtleHist(x, y, penDown));
        return ret;
    }
    
    protected void moveOn(TurtleHist hist) {
        myX = hist.getX();
        myY = hist.getY();
        lastMove.add(hist);
    }

    private double newMove(double x, double y, boolean penDown) {
        clearHist();
        return moveOn(x, y, penDown);
    }

    double setXY(double x, double y) {
        return newMove(x, y, penDown());
    }

    double home() {
        myHeading = 0;
        return newMove(0, 0, penDown());
    }
    
    void clearHist() {
        lastMove.clear();
        lastMove.add(new TurtleHist(myX, myY, penDown));
    }

    /*****Rotational movement*****/
    double turn(double degree) {
        myHeading = (myHeading + degree) % Constants.ROUND_ANGLE;
        return degree;
    }

    double setHeading(double heading) {
        double oldHeading = myHeading;
        myHeading = heading;
        return myHeading - oldHeading;
    }

    double towards(double x, double y) {
        double oldHeading = myHeading;
        myHeading = Math.atan2(y - myY, x - myX) / Constants.RADIAN_PER_DEGREE;
        return myHeading - oldHeading;
    }
    
    protected double radianHeading() {
        return getHeading() * Constants.RADIAN_PER_DEGREE;
    }

    /*****Visual property*****/
    boolean setPen(boolean penDown) {
        this.penDown = penDown;
        return penDown;
    }

    boolean setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return isVisible;
    }
    
    /*****Handle reset*****/
    void setReset() {
        isReset = true;
    }
    
    void clearReset() {
        isReset = false;
    }

}