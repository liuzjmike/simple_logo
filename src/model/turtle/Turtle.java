package model.turtle;

public class Turtle {
    
    public static final double RADIAN_PER_DEGREE = Math.PI / 180;
    public static final double ROUND_ANGLE = 360;
    
    private double myX, myY, myHeading;
    private boolean penDown, isVisible;
    private int myID;
    
    public Turtle(int id) {
        myID = id;
        myX = 0;
        myY = 0;
        myHeading = 0;
        penDown = true;
        isVisible = true;
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
    
    void move(double dist) {
        myX += dist * Math.cos(radianHeading());
        myY += dist * Math.sin(radianHeading());
    }
    
    void setXY(double x, double y) {
        myX = x;
        myY = y;
    }
    
    void turn(double degree) {
        myHeading = (myHeading + degree) % ROUND_ANGLE;
    }
    
    void setHeading(double heading) {
        myHeading = heading % ROUND_ANGLE;
    }
    
    void towards(double x, double y) {
        myHeading = Math.atan2(y - myY, x - myX) / RADIAN_PER_DEGREE;
    }
    
    void setPen(boolean penDown) {
        this.penDown = penDown;
    }
    
    void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
    
    private double radianHeading() {
        return myHeading * RADIAN_PER_DEGREE;
    }
}
