package model;

import model.turtle.TurtlePool;

/**
 * Contains the data of the IDE.
 * @author Mike Liu
 * 
 */
public class Environment {
    
    private TurtlePool turtlePool;
    private CommandPool commandPool;
    private VariablePool variablePool;
    private Palette myPalette;
    private Library myLibrary;
    private double myWidth, myHeight;
    
    public Environment(double width, double height) {
        turtlePool = new TurtlePool();
        commandPool = new CommandPool();
        variablePool = new VariablePool();
        myLibrary = new Library(() -> variablePool.getGlobal());
        myPalette = new Palette();
        myWidth = width;
        myHeight = height;
    }

    /**
     * Returns the TurtlePool.
     * @return
     */
    public TurtlePool getTurtlePool() {
        return turtlePool;
    }
    
    /**
     * Returns the CommandPool.
     * @return
     */
    public CommandPool getCommandPool() {
        return commandPool;
    }
    
    /**
     * Returns the VariablePool.
     * @return
     */
    public VariablePool getVariablePool() {
        return variablePool;
    }
    
    /**
     * Returns the library of global variables and
     * user defined commands.
     * @return
     */
    public Library getLibrary() {
        return myLibrary;
    }

    /**
     * Returns the palette.
     * @return
     */
    public Palette getPalette() {
        return myPalette;
    }
    
    /**
     * Sets the width of the TurtlePool.
     * @param w
     */
    public void setPoolWidth(double w) {
    	myWidth = w;
    }
    
    /**
     * Sets the height of the TurtlePool.
     * @param h
     */
    public void setPoolHeight(double h) {
    	myHeight = h;
    }
    
    /**
     * Gets the width of the TurtlePool.
     * @return
     */
    public double getPoolWidth() {
    	return myWidth;
    }
    
    /**
     * Gets the height of the TurtlePool.
     * @return
     */
    public double getPoolHeight() {
    	return myHeight;
    }
}
