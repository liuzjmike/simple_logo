package model.info;

import java.util.List;

import model.turtle.TurtleHist;

public interface TurtleInfo {

    double getHeading();
    
    double getX();
    
    double getY();

    List<TurtleHist> getLastMove();

    boolean isVisible();
    
    boolean isReset();
    
    PenInfo getPenInfo();
    
    int getShape();

}