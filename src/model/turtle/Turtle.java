package model.turtle;

public class Turtle {
    
    private double myX, myY, myHeading;
    boolean penDown, isVisible;

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
    
    protected void setX(double x) {
        myX = x;
    }
    
    protected void setY(double y) {
        myY = y;
    }
    
    protected void setHeading(double heading) {
        myHeading = heading;
    }
    
    protected void setPen(boolean penDown) {
        this.penDown = penDown;
    }
    
    protected void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
