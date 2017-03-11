package model.turtle;

import model.info.TurtleInfo;

public interface Turtle extends TurtleInfo {
	
    double move(double dist, double width, double height);

    double setXY(double x, double y);

    double home();
    
    double reset();

    /*****Rotational movement*****/
    double turn(double degree);

    double setHeading(double heading);

    double towards(double x, double y);

    /*****Visual property*****/
    Pen getPen();
    
    int setShape(int shape);

    boolean setVisible(boolean isVisible);
    
    void clearReset();
}
