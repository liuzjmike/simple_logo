package view;

import java.util.Collection;

import model.turtle.Turtle;

public interface GUI {

    public void show();
    
    public void addVariable(String variable);
    
    public void addCommand(String command);
    
    public void setTurtle(Collection<Turtle> turtles);
}
