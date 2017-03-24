package model.turtle;

/**
 * Records a piece of positional and pen information
 * @author Mike Liu
 *
 */
public class TurtleHist {
    
    private double myX, myY;
    private boolean penDown;
    
    public TurtleHist(double x, double y, boolean penDown) {
        myX = x;
        myY = y;
        this.penDown = penDown;
    }

    public boolean penDown() {
        return penDown;
    }
    
    public double getX() {
        return myX;
    }
    
    public double getY() {
        return myY;
    }
}
