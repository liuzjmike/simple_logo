package model;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;
import model.info.PaletteInfo;
import util.SLogoException;
import util.SLogoObservable;

/**
 * Back-end entity that stores colors and their corresponding indices.
 * Index 0 and 1 should be WHITE and BLACK by default
 * @author Mike Liu
 *
 */
public class Palette extends SLogoObservable<PaletteInfo> implements PaletteInfo {
    
    public static final double CHANNEL_RANGE = 255;
    public static final List<Color> INITIAL_COLOR = Arrays.asList(Color.WHITE, Color.AZURE, Color.BURLYWOOD, Color.CORAL,
    		Color.MEDIUMSPRINGGREEN, Color.THISTLE, Color.PLUM, Color.PALEVIOLETRED);

    private Map<Integer, Color> myPalette;
    
    public Palette() {
        myPalette = initPalette();
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
    
    private Map<Integer, Color> initPalette() {
    	Map<Integer, Color> ret = new HashMap<>();
    	for(int i = 0; i < INITIAL_COLOR.size(); i++) {
    		ret.put(i, INITIAL_COLOR.get(i));
    	}
    	return ret;
    }
    
}
