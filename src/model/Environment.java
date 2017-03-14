package model;

import model.turtle.TurtlePool;

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

    public TurtlePool getTurtlePool() {
        return turtlePool;
    }
    
    public CommandPool getCommandPool() {
        return commandPool;
    }
    
    public VariablePool getVariablePool() {
        return variablePool;
    }
    
    public Library getLibrary() {
        return myLibrary;
    }

    public Palette getPalette() {
        return myPalette;
    }
    
    public void setWidth(double w) {
    	myWidth = w;
    }
    
    public void setHeight(double h) {
    	myHeight = h;
    }
    
    public double getWidth() {
    	return myWidth;
    }
    
    public double getHeight() {
    	return myHeight;
    }
}
