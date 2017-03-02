package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pen {
	
	private Color color;
	
	public Pen(Color color){
		this.color = color;
	}
	
	public Line drawLine(double x1, double y1, double x2, double y2){
	    Line line = new Line();
        line.setStartX(x1);
        line.setStartY(y1);
        line.setEndX(x2);
        line.setEndY(y2);
        line.setStrokeWidth(1);
        line.setStroke(color);
        return line;
	}

}
