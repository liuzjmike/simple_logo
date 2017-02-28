package view;

import javafx.scene.image.ImageView;
import util.Constants;

public class TurtleView {
	public static final String TURTLE_IMAGE = "TurtleImge.png";

	private boolean penDown, isVisible;
	private ImageView myTurtle;
	private int myID;
	
	public TurtleView(){
		penDown = true;
		isVisible = true;
		setHeading(0);
	}

    public void setImage(ImageView image) {
    	myTurtle = image;
	}
    
//    public void setPen(boolean penDown){
//    	this.penDown = penDown;
//    }
    //TODO: MAKE PEN CLASS
    
    public void setVisible(boolean isVisible){
    	myTurtle.setVisible(isVisible);
    }
    
    /*****Translational movement*****/
    public void setXY(double x, double y) {
    	myTurtle.setX(x);
    	myTurtle.setY(y);
	}
    
    /*****Rotational movement*****/

    public void setHeading(double heading){
    	myTurtle.setRotate(heading%Constants.ROUND_ANGLE);
    }
    
}
