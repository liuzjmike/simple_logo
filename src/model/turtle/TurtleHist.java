package model.turtle;

public class TurtleHist {
    
    private boolean penDown;
    private double myX, myY;
    
    public TurtleHist(boolean penDown, double x, double y) {
        this.penDown = penDown;
        myX = x;
        myY = y;
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
