package model.turtle;

import java.util.List;

public interface Turtle {

    int getID();

    double getHeading();

    List<TurtleHist> getLastMove();

    boolean isVisible();
    
    boolean isReset();

}