package model.turtle.info;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public interface PenInfo {

    public Color getColor();
    
    public double getSize();
    
    public boolean isDown();
    
    public Line drawLine(double x1, double y1, double x2, double y2);
}
