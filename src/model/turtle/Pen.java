package model.turtle;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import model.info.PaletteInfo;
import model.info.PenInfo;

public class Pen implements PenInfo {
    
    public static final double DEFAULT_SIZE = 1;
    public static final Color DEFAULT_COLOR = Color.BLACK;
	
	private int myColor;
	private double mySize;
	private boolean isDown;
	
	public Pen() {
		myColor = 0;
		mySize = DEFAULT_SIZE;
		isDown = true;
	}
	
	@Override
	public int getColor() {
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
    public Line drawLine(PaletteInfo palette, double x1, double y1, double x2, double y2) {
        Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStrokeWidth(mySize);
        line.setStroke((palette != null) ? palette.getColor(myColor) : DEFAULT_COLOR);
        return line;
    }

	public void setColor(int color) {
	    myColor = color;
	}
	
	public void setSize(double size) {
	    mySize = size;
	}
	
	public void setDown(boolean down) {
	    isDown = down;
	}
	
	public Pen copy() {
	    Pen ret = new Pen();
	    ret.myColor = myColor;
	    ret.mySize = mySize;
	    return ret;
	}
}
