package model.info;

import java.util.Map;

import javafx.scene.paint.Color;

public interface PaletteInfo {
    
    public Color getColor(int index);
    
    public Map<Integer, Color> listColor();

}
