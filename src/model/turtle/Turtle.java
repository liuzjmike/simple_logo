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
        myTrace.add(new TurtleHist(penDown, myX, myY));
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
    
    public List<TurtleHist> getLastMove() {
        return lastMove;
    }
    
    public List<TurtleHist> getFullTrace() {
        return myTrace;
    }
    
    public boolean penDown() {
        return penDown;
    }
    
    public boolean isVisible() {
        return isVisible;
    }
    
    /*****Translational movement*****/
    
    double move(double dist, double wRadius, double hRadius) {
        lastMove.clear();
        move(dist * Math.cos(radianHeading()), dist * Math.sin(radianHeading()), wRadius, hRadius);
        return dist;
    }
    
    double setXY(double x, double y) {
        return move(x, y, penDown(), true);
    }
    
    double home() {
        myHeading = 0;
        return move(0, 0, penDown(), true);
    }
    
    double reset() {
        double ret = home();
        lastMove.clear();
        myTrace.clear();
        myTrace.add(new TurtleHist(penDown, myX, myY));
        return ret;
    }
    
    private void move(double dx, double dy, double wRadius, double hRadius) {
        double newX = myX + dx;
        double newY = myY + dy;
        if(inBounds(newX, newY, wRadius, hRadius)) {
            move(new TurtleHist(penDown(), newX, newY));
            return;
        }
        double oldX = myX;
        double oldY = myY;
        move(getInboundPos(myX, myY, wRadius, hRadius));
        dx -= myX - oldX;
        dy -= myY - oldY;
        move(switchSide(myX, dx, wRadius), switchSide(myY, dy, hRadius), penDown(), false);
        dx = updateDiff(oldX, myX, dx);
        dy = updateDiff(oldY, myY, dy);
        move(dx, dy, wRadius, hRadius);
    }

    private double move(double x, double y, boolean penDown, boolean clearLast) {
        double ret = Math.hypot(x - myX, y - myY);
        if(clearLast) {
            lastMove.clear();
            lastMove.add(new TurtleHist(penDown, myX, myY));
        }
        move(new TurtleHist(penDown, x, y));
        return ret;
    }
    
    private void move(TurtleHist hist) {
        lastMove.add(hist);
        myTrace.add(hist);
        myX = hist.getX();
        myY = hist.getY();
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
    
    private boolean inBounds(double x, double y, double wRadius, double hRadius) {
        return x >= -wRadius && x < wRadius && y >= -hRadius && y < hRadius;
    }
    
    private TurtleHist getInboundPos(double x, double y, double wRadius, double hRadius) {
        double yIntersect = myY + Math.copySign((wRadius - myX) * Math.tan(radianHeading()),
                Math.cos(radianHeading()));
        if(yIntersect < -hRadius || yIntersect >= hRadius) {
            y = y < 0 ? -hRadius : hRadius-1;
            return new TurtleHist(false, x + Math.copySign((y - myY) / Math.tan(radianHeading()),
                    Math.sin(radianHeading())), y);
        } else {
            return new TurtleHist(false, x < 0 ? -wRadius : wRadius-1, yIntersect);
        }
    }
    
    private double switchSide(double pos, double dpos, double radius) {
        if((pos == -radius || pos >= radius-1) && dpos != 0) {
            return - (pos + 1);
        }
        return pos;
    }
    
    private double updateDiff(double oldValue, double newValue, double totalDiff) {
        if(newValue != oldValue) {
            totalDiff -= Math.copySign(1, totalDiff);
        }
        return totalDiff;
    }
}
