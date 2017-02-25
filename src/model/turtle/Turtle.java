package model.turtle;

public class Turtle {
    
    private double myX, myY, myHeading;
    private boolean penDown, isVisible;
    private int myID;
    
    public Turtle(int id) {
        myID = id;
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
    
    void setX(double x) {
        myX = x;
    }
    
    void setY(double y) {
        myY = y;
    }
    
    void setHeading(double heading) {
        myHeading = heading;
    }
    
    void setPen(boolean penDown) {
        this.penDown = penDown;
    }
    
    void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
