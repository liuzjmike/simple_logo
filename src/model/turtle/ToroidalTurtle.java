package model.turtle;

public class ToroidalTurtle extends AbstractTurtle {
    
    public ToroidalTurtle(int id) {
        super(id);
    }
    
    @Override
    protected void move(double dx, double dy, double wRadius, double hRadius) {
        double newX = getX() + dx;
        double newY = getY() + dy;
        if(inBounds(newX, newY, wRadius, hRadius)) {
            moveOn(newX, newY, penDown());
            return;
        }
        double oldX = getX();
        double oldY = getY();
        moveOn(getInboundPos(getX(), getY(), wRadius, hRadius));
        dx -= getX() - oldX;
        dy -= getY() - oldY;
        moveOn(switchSide(getX(), dx, wRadius), switchSide(getY(), dy, hRadius), penDown());
        dx = updateDiff(oldX, getX(), dx);
        dy = updateDiff(oldY, getY(), dy);
        move(dx, dy, wRadius, hRadius);
    }
    
    private boolean inBounds(double x, double y, double wRadius, double hRadius) {
        return x >= -wRadius && x < wRadius && y >= -hRadius && y < hRadius;
    }
    
    private TurtleHist getInboundPos(double x, double y, double wRadius, double hRadius) {
        double yIntersect = getY() + Math.copySign((wRadius - getX()) * Math.tan(radianHeading()),
                Math.cos(radianHeading()));
        if(yIntersect <= -hRadius || yIntersect >= hRadius) {
            y = y < 0 ? -hRadius : hRadius-1;
            return new TurtleHist(x + Math.copySign((y - getY()) / Math.tan(radianHeading()),
                    Math.sin(radianHeading())), y, false);
        } else {
            return new TurtleHist(x < 0 ? -wRadius : wRadius-1, yIntersect, false);
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
