package model.turtle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.info.PenInfo;
import util.Constants;

public abstract class AbstractTurtle implements Turtle {

    private double myX;
    private double myY;
    private double myHeading;
    private boolean isVisible, isReset;
    Pen myPen;
    private int myShape;
    private List<TurtleHist> lastMove;

    public AbstractTurtle() {
        this(new Pen());
    }
    
    public AbstractTurtle(Pen pen) {
        myX = 0;
        myY = 0;
        myHeading = 0;
        isVisible = true;
        isReset = false;
        myPen = pen;
        lastMove = new ArrayList<TurtleHist>();
        logHist();
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
    public boolean isVisible() {
        return isVisible;
    }
    
    @Override
    public boolean isReset() {
        return isReset;
    }

    @Override
    public PenInfo getPenInfo() {
        return myPen;
    }

    /*****Translational movement*****/
    @Override
    public double move(double dist, double width, double height) {
        clearHist();
        move(dist * Math.cos(radianHeading()), dist * Math.sin(radianHeading()), width/2, height/2);
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

    private double startMove(double x, double y, boolean penDown) {
        clearHist();
        return moveOn(x, y, penDown);
    }

    @Override
    public double setXY(double x, double y) {
        return startMove(x, y, myPen.isDown());
    }

    @Override
    public double home() {
        myHeading = 0;
        return startMove(0, 0, myPen.isDown());
    }

    @Override
    public double reset() {
        double ret = home();
        clearHist();
        isReset = true;
        return ret;
    }
    
    private void clearHist() {
        lastMove.clear();
        logHist();
    }
    
    private void logHist() {
        lastMove.add(new TurtleHist(myX, myY, myPen.isDown()));
    }

    /*****Rotational movement*****/
    @Override
    public double turn(double degree) {
        myHeading = (myHeading + degree) % Constants.ROUND_ANGLE;
        return degree;
    }

    @Override
    public double setHeading(double heading) {
        double oldHeading = myHeading;
        myHeading = heading;
        return myHeading - oldHeading;
    }

    @Override
    public double towards(double x, double y) {
        double oldHeading = myHeading;
        myHeading = Math.atan2(y - myY, x - myX) / Constants.RADIAN_PER_DEGREE;
        return myHeading - oldHeading;
    }
    
    protected double radianHeading() {
        return getHeading() * Constants.RADIAN_PER_DEGREE;
    }

    /*****Visual property*****/
    @Override
    public Pen getPen() {
        return myPen;
    }
    
    @Override
    public int getShape() {
    	return myShape;
    }
    
    @Override
    public int setShape(int shape) {
    	myShape = shape;
    	return shape;
    }

    @Override
    public boolean setVisible(boolean isVisible) {
        this.isVisible = isVisible;
        return isVisible;
    }

    @Override
    public void clearReset() {
        isReset = false;
    }

}