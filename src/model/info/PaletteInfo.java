package model.info;

import java.util.Map;

import javafx.scene.paint.Color;

public interface PaletteInfo {
    
    Color getColor(int index);
    
    Map<Integer, Color> listColor();

}
