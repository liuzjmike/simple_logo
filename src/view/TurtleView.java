package view;

import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.turtle.Turtle;
import model.turtle.TurtleHist;
import util.Constants;

public class TurtleView {
	public static final Color defaultColor = Color.BLACK;

	private ImageView myTurtle;
	private Pen myPen;
	private List<TurtleHist> lastMove;

	public TurtleView(ImageView image, Turtle turtle, Pane pane){
		myPen = new Pen(true, defaultColor, pane);
		setImage(image);
		setHeading(turtle.getHeading());
		setScale(0.1);
		setVisible(turtle.isVisible());
		lastMove = turtle.getLastMove();	
	}

    private void setImage(ImageView image) {
    	myTurtle = image;
	}
    
    private void setVisible(boolean isVisible){
    	myTurtle.setVisible(isVisible);
    }
    
    private void setScale(double scale){
    	myTurtle.setScaleX(scale);
    	myTurtle.setScaleY(scale);
    }
    
    public void drawLines(){
    	for(int i=0; i<lastMove.size();i++){
    		if(lastMove.get(i).penDown()){
    			setXY(lastMove.get(i).getX(),lastMove.get(i).getY());
    			if(i+1<lastMove.size()){
    				draw(lastMove.get(i).getX(),lastMove.get(i+1).getX(), lastMove.get(i).getY(),lastMove.get(i+1).getY());  
    			}		 			
    		}
    	}
    	
    }
    
    private void draw(double x1, double x2, double y1, double y2){
    	myPen.drawLine(x1, x2, y1, y2);
    }
    
    /*****Translational movement*****/
    private void setXY(double x, double y) {
    	myTurtle.setX(x);
    	myTurtle.setY(y);
	}
    
    /*****Rotational movement*****/
    private void setHeading(double heading){
    	myTurtle.setRotate((heading+90)%Constants.ROUND_ANGLE);
    }
    
}
