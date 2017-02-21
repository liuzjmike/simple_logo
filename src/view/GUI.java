package view;

import java.util.Collection;
import java.util.Observer;

import model.turtle.Turtle;

public interface GUI {

    public void show();
    
    public Observer getPoolObserver();
    
    public Observer getCommandObserver();
    
    public Observer getVariableObserver();
    
    public void addCommand(String command);
    
    public void addVariable(String variable);
    
    public void setTurtles(Collection<Turtle> turtles);
}
