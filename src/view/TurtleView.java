package view;



import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import util.Constants;

public class TurtleView {
	public static final String TURTLE_IMAGE = "TurtleImge.png";
	public static final Color defaultColor = Color.BLACK;

	private ImageView myTurtle;
	private Pen myPen;
	
	public TurtleView(){
		myPen = new Pen(true, defaultColor);
		setHeading(0);
	}

    public void setImage(ImageView image) {
    	myTurtle = image;
	}
    
    public void setPen(boolean penDown){
    	myPen.setPen(penDown);
    }
    
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
