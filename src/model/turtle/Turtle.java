package model.turtle;

import model.info.TurtleInfo;

/**
 * An object that represents a turtle in SLogo
 * @author Mike Liu
 *
 */
public interface Turtle extends TurtleInfo {
	
    /**
     * Moves turtle forward in its current heading by dist
     * The area turtle can move in has width <code>width</code>
     * and height <code>height</code>
     * @param dist
     * @param width
     * @param height
     * @return dist
     */
    double move(double dist, double width, double height);

    /**
     * Moves turtle to absolute position (x, y), where
     * (0, 0) is the center of the screen
     * @param x
     * @param y
     * @return the distance turtle moved
     */
    double setXY(double x, double y);

    /**
     * Moves turtle to the center of the screen (0, 0)
     * @return the distance turtle moved
     */
    double home();
    
    /**
     * Erases turtle's trails, sends it to the home position
     * and marks turtle has having been reset
     * @return the distance turtle moved
     */
    double reset();

    /*****Rotational movement*****/
    
    /**
     * Turns turtle counter-clockwise by the given degree
     * @param degree
     * @return the number of degrees turned
     */
    double turn(double degree);

    /**
     * Turns turtle to the given absolute heading
     * @param heading
     * @return the number of degrees turned
     */
    double setHeading(double heading);

    /**
     * Turns turtle to face the point (x, y), where (0, 0) is the center of the screen
     * @param x
     * @param y
     * @return the number of degrees turned
     */
    double towards(double x, double y);

    /*****Visual property*****/
    
    /**
     * 
     * @return <code>Pen</code> of this turtle
     */
    Pen getPen();
    
    /**
     * Sets shape of turtle to that represented by shape
     * @param shape
     * @return shape
     */
    int setShape(int shape);

    /**
     * Sets the visibility of turtle to isVisible
     * @param isVisible
     * @return isVisible
     */
    boolean setVisible(boolean isVisible);
    
    /**
     * Unmark turtle has having been reset
     */
    void clearReset();
}
