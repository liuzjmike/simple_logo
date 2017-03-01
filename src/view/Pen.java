package view;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Pen {
	
	private boolean penDown;
	private Color color;
	private Pane myPane;
	
	public Pen(boolean penDown, Color defaultcolor, Pane pane){
		this.penDown = penDown;
		this.color = defaultcolor;
		myPane = pane;
	}
	
	public void setPen(boolean penDown){
		this.penDown = penDown;
	}
	
	public void drawLine(double x1, double x2, double y1, double y2){
		if(penDown){
			Line line = new Line();
			line.setStartX(x1);
			line.setStartY(y1);
			line.setEndX(x2);
			line.setEndY(y2);
			setColor(line);
			myPane.getChildren().add(line);
		}		
	}
	
	private void setColor(Line line){
		line.setStroke(color);
	}

}
