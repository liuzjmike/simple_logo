package model;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;

public class Palette {
    
    public static final double CHANNEL_RANGE = 255;

    private Map<Integer, Color> myPalette;
    
    public Palette() {
        myPalette = new HashMap<>();
    }
    
    public Color getColor(int index) {
        if(!myPalette.containsKey(index)) {
            throw new RuntimeException();
        }
        return myPalette.get(index);
    }
    
    public void setColor(int index, int red, int green, int blue) {
        myPalette.put(index, Color.color(red/CHANNEL_RANGE, green/CHANNEL_RANGE, blue/CHANNEL_RANGE));
    }
}
