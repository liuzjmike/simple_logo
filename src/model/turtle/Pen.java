package model.turtle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.turtle.info.PenInfo;

public class Pen implements PenInfo {
    
    public static final Color DEFAULT_COLOR = Color.BLACK;
    public static final double DEFAULT_SIZE = 1;
	
	private Color myColor;
	private double mySize;
	private boolean isDown;
	
	public Pen() {
		myColor = DEFAULT_COLOR;
		mySize = DEFAULT_SIZE;
		isDown = true;
	}
	
	@Override
	public Color getColor() {
	    return myColor;
	}

    @Override
    public double getSize() {
        return mySize;
    }

    @Override
    public boolean isDown() {
        return isDown;
    }

    @Override
    public Line drawLine(double x1, double y1, double x2, double y2) {
        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStrokeWidth(mySize);
        line.setStroke(myColor);
        return line;
    }
	
	public void setColor(Color color) {
	    myColor = color;
	}
	
	public void setSize(double size) {
	    mySize = size;
	}
	
	public void setDown(boolean down) {
	    isDown = down;
	}
	
	public Pen clone() {
	    Pen ret = new Pen();
	    ret.myColor = myColor;
	    ret.mySize = mySize;
	    return ret;
	}
}
