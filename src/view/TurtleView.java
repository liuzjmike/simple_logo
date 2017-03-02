package view;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.turtle.Turtle;
import model.turtle.TurtleHist;
import util.Constants;

public class TurtleView {
    
	public static final Color DEFAULT_COLOR = Color.BLACK;

	private ImageView myImage;
	private Turtle myTurtle;
	private Pen myPen;
	private List<Line> myLines;
	private LineDrawer lineDrawer; 

	public TurtleView(ImageView image, Turtle turtle, LineDrawer lineDrawer){
		myPen = new Pen(DEFAULT_COLOR);
		setImage(image);
		setHeading(turtle.getHeading());
		setSize(36, 40);
		setVisible(turtle.isVisible());
		myTurtle = turtle;
		myLines = new ArrayList<Line>();
		this.lineDrawer = lineDrawer;
	}
    
    public void update(){
        setVisible(myTurtle.isVisible());
        setHeading(myTurtle.getHeading());
        List<TurtleHist> lastMove = myTurtle.getLastMove();
        TurtleHist dest = new TurtleHist(myImage.getX(), myImage.getY(), false);
        for(int i = 0; i < lastMove.size() - 1; i++) {
            myLines.clear();
            TurtleHist oldHist = lastMove.get(i);
            dest = lastMove.get(i+1);
            if(oldHist.penDown()) {
                Line line = myPen.drawLine(oldHist.getX(), oldHist.getY(), dest.getX(), dest.getY());
                lineDrawer.addLine(line);
                myLines.add(line);
            }
        }
        setXY(dest.getX(), dest.getY());
        if(myTurtle.isReset()) {
            lineDrawer.removeLines(myLines);
            myLines.clear();
        }
    }

    private void setImage(ImageView image) {
    	myImage = image;
	}
    
    private void setVisible(boolean isVisible){
    	myImage.setVisible(isVisible);
    }
    
    private void setSize(double width, double height){
    	myImage.setFitWidth(width);
    	myImage.setFitHeight(height);
    }
    
    /*****Translational movement*****/
    private void setXY(double x, double y) {
    	myImage.setX(x);
    	myImage.setY(y);
	}
    
    /*****Rotational movement*****/
    private void setHeading(double heading){
    	myImage.setRotate((heading+90)%Constants.ROUND_ANGLE);
    }
    
}
