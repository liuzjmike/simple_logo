package model.turtle;

public interface Turtle extends TurtleInfo {
    double move(double dist, double wRadius, double hRadius);

    double setXY(double x, double y);

    double home();
    
    double reset();

    /*****Rotational movement*****/
    double turn(double degree);

    double setHeading(double heading);

    double towards(double x, double y);

    /*****Visual property*****/
    boolean setPen(boolean penDown);

    boolean setVisible(boolean isVisible);
    
    void clearReset();
}
