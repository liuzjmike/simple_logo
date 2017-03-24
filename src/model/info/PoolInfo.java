package model.info;

import java.util.Map;

/**
 * An object that contains the states of a turtle pool
 * @author Mike Liu
 *
 */
public interface PoolInfo {

    public int getBackground();
    
    public Map<Integer, TurtleInfo> getTurtles();
}
