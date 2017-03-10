package model.info;

import java.util.Map;

public interface PoolInfo {

    public int getBackground();
    
    public Map<Integer, TurtleInfo> getTurtles();
}
