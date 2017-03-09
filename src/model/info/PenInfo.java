package model.info;

import javafx.scene.shape.Line;

public interface PenInfo {

    public int getColor();
    
    public double getSize();
    
    public boolean isDown();
    
    public Line drawLine(PaletteInfo palette, double x1, double y1, double x2, double y2);
}
