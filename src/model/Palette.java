package model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.paint.Color;
import model.info.PaletteInfo;
import util.SLogoException;
import util.SLogoObservable;

public class Palette extends SLogoObservable<PaletteInfo> implements PaletteInfo {
    
    public static final double CHANNEL_RANGE = 255;

    private Map<Integer, Color> myPalette;
    
    public Palette() {
        myPalette = new HashMap<>();
    }
    
    @Override
    public Color getColor(int index) {
        if(!myPalette.containsKey(index)) {
            throw new SLogoException(SLogoException.INVALID_COLOR_INDEX);
        }
        return myPalette.get(index);
    }

    @Override
    public Map<Integer, Color> listColor() {
        return Collections.unmodifiableMap(myPalette);
    }
    
    public void setColor(int index, int red, int green, int blue) {
        myPalette.put(index, Color.color(red/CHANNEL_RANGE, green/CHANNEL_RANGE, blue/CHANNEL_RANGE));
        notifyObservers();
    }

    @Override
    protected PaletteInfo notification() {
        return this;
    }
    
}
