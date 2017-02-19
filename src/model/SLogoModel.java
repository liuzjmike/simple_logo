package model;

import java.util.Collection;
import java.util.Observer;

import model.turtle.Turtle;

public interface SLogoModel {
    
    public String interpret(String command);
    
    public void addPoolObserver(Observer o);
    
    public void removePoolObserver(Observer o);
    
    public Collection<Turtle> getTurtles();
}
