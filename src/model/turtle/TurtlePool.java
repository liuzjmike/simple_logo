package model.turtle;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;


public class TurtlePool implements Observer { 
    
    Collection<Turtle> getTurtles() {
		return null;
	}
    
    public void moveTurtle(double dist) {
	}
    
    public void turnTutle(double degree) {
	}
    
    public void setPen(boolean penDown) {
	}
    
    public void setVisible(boolean isVisible) {
	}

	@Override
	public void update(Observable TurtleView, Object arg) {
		// TODO Auto-generated method stub
		
	}


}
