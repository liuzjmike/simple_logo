package model.turtle.info;

import java.util.Map;

import javafx.scene.paint.Color;

public interface PoolInfo {

    public Color getBackground();
    
    public Map<Integer, TurtleInfo> getTurtles();
}
