package model.info;

import java.util.List;

import model.turtle.TurtleHist;

/**
 * An object that contains the states of a turtle in SLogo
 * @author Mike Liu
 *
 */
public interface TurtleInfo {

    double getHeading();
    
    double getX();
    
    double getY();

    List<TurtleHist> getLastMove();

    boolean isVisible();
    
    boolean isReset();
    
    /**
     * 
     * @return <code>PenInfo</code> of the turtle represented
     */
    PenInfo getPenInfo();
    
    int getShape();

}