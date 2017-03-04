package model.turtle;

public class ToroidalTurtle extends AbstractTurtle {
    
    public ToroidalTurtle(Pen pen) {
        super(pen);
    }
    
    @Override
    protected void move(double dx, double dy, double wRadius, double hRadius) {
        double newX = getX() + dx;
        double newY = getY() + Math.copySign(dx * Math.tan(radianHeading()),
                Math.sin(radianHeading()));
        if(inBounds(newX, newY, wRadius, hRadius)) {
            moveOn(newX, newY, getPen().isDown());
            return;
        }
        double oldX = getX();
        double oldY = getY();
        moveOn(getInboundPos(newX, newY, wRadius, hRadius));
        dx -= getX() - oldX;
        dy -= getY() - oldY;
        moveOn(switchSide(getX(), dx, wRadius), switchSide(getY(), dy, hRadius), getPen().isDown());
        move(dx, dy, wRadius, hRadius);
    }
    
    private boolean inBounds(double x, double y, double wRadius, double hRadius) {
        return x >= -wRadius && x < wRadius && y >= -hRadius && y < hRadius;
    }
    
    private TurtleHist getInboundPos(double x, double y, double wRadius, double hRadius) {
        double dx = Math.cos(radianHeading()) > 0 ? wRadius - getX() : wRadius + getX();
        double yIntersect = getY() + Math.copySign(dx * Math.tan(radianHeading()),
                Math.sin(radianHeading()));
        if(yIntersect < -hRadius || yIntersect > hRadius) {
            double dy = Math.sin(radianHeading()) > 0 ? hRadius - getY() : hRadius + getY();
            return new TurtleHist(getX() + Math.copySign(dy / Math.tan(radianHeading()),
                    Math.cos(radianHeading())), y < 0 ? -hRadius : hRadius-1, false);
        } else {
            return new TurtleHist(x < 0 ? -wRadius : wRadius-1, yIntersect, false);
        }
    }
    
    private double switchSide(double pos, double dpos, double radius) {
        if(dpos != 0) {
            if(pos <= -radius) {
                return radius - 1.1;
            }
            else if(pos >= radius-1) {
                return -pos + 0.1;
            }
        }
        return pos;
    }
}
