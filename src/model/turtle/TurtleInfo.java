package model.turtle;

import java.util.List;

public interface TurtleInfo {

    int getID();

    double getHeading();
    
    double getX();
    
    double getY();

    List<TurtleHist> getLastMove();

    boolean isVisible();
    
    boolean isReset();
    
    boolean penDown();

}