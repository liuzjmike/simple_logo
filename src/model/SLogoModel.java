package model;

import java.util.Collection;
import java.util.List;
import java.util.Observer;

import model.turtle.Turtle;

public interface SLogoModel {
    
    public List<String> interpret(List<String> command) throws Exception;
    
    public void addPoolObserver(Observer o);
    
    public void removePoolObserver(Observer o);
    
    public void addCommandObserver(Observer o);
    
    public void removeCommandObserver(Observer o);
    
    public void addVariableObserver(Observer o);
    
    public void removeVariableObserver(Observer o);
    
    public Collection<Turtle> getTurtles();
    
    public List<String> getCommands();
    
    public List<String> getVariables();
}
