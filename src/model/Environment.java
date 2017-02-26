package model;

import model.turtle.TurtlePool;

public class Environment {
    
    private TurtlePool turtlePool;
    private CommandPool commandPool;
    private VariablePool variablePool;
    
    public Environment(double width, double height) {
        turtlePool = new TurtlePool(width, height);
        commandPool = new CommandPool();
        variablePool = new VariablePool();
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
}
