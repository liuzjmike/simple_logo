package model.info;

import javafx.scene.shape.Line;

/**
 * An object that contains the states of a pen
 * @author Mike Liu
 *
 */
public interface PenInfo {

    public int getColor();
    
    public double getSize();
    
    public boolean isDown();
    
    public Line drawLine(PaletteInfo palette, double x1, double y1, double x2, double y2);
}
